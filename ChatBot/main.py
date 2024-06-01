from langchain_community.utilities import SQLDatabase
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnablePassthrough
from langchain_openai import ChatOpenAI
from flask import Flask, request, jsonify
import tiktoken

from flask_cors import CORS

app = Flask(__name__)
CORS(app)


# MySQL connection URI
mysql_uri = 'mysql+mysqlconnector://root:@localhost:3306/carrierex'

# Initialize the SQLDatabase
db = SQLDatabase.from_uri(mysql_uri)

# Define the template for generating the SQL query
query_template = """Based on the table schema below, write a SQL query that would answer the user's question:
{schema}

Question: {question}
SQL Query:"""
prompt = ChatPromptTemplate.from_template(query_template)

# Define the template for generating the natural language response
response_template = """Based on the table schema below, question, sql query, and sql response, write a natural language response in the language of the question:
{schema}

Question: {question}
SQL Query: {query}
SQL Response: {response}"""
prompt_response = ChatPromptTemplate.from_template(response_template)

def get_schema():
    return """
    Schema:
    - Table: emploi
    Columns:
        - id (INT, PRIMARY KEY)
        - titre (VARCHAR)
        - id_categorie (INT, FOREIGN KEY REFERENCES categorie(id))
        - id_ville (INT, FOREIGN KEY REFERENCES ville(id))
        - salaire_min (double)
        - salaire_max (double)
        
    - Table: ville
    Columns:
        - id (INT, PRIMARY KEY)
        - nom (VARCHAR)
        
    - Table: categorie
    Columns:
        - id (INT, PRIMARY KEY)
        - nom (VARCHAR)
    """

def run_query(db, query):
    # Run the query on the database
    return db.run(query)

def count_tokens(text, model='gpt-4'):
    encoding = tiktoken.encoding_for_model(model)
    return len(encoding.encode(text))

llm = ChatOpenAI(api_key='sk-proj-JYNq7ESs9KKeAovDltnyT3BlbkFJMzvgqfj12AQaqN7CKRBe')


sql_chain = (
    RunnablePassthrough.assign(schema=lambda vars: get_schema())
    | prompt
    | llm.bind(stop=["\nSQLResult:"])
    | StrOutputParser()
)

def limited_run_query(db, query, max_tokens=60000):
    result = run_query(db, query)
    if count_tokens(result) > max_tokens:
        raise ValueError("Query result exceeds the maximum allowed tokens.")
    return result

full_chain = (
    RunnablePassthrough.assign(query=sql_chain).assign(
        schema=lambda vars: get_schema(),
        response=lambda vars: limited_run_query(db, vars["query"]),
    )
    | prompt_response
    | llm  # Use the language model instance here
)

@app.route('/ask', methods=['POST'])
def ask():
    question = request.json.get("question", None)
    try:
        result = full_chain.invoke({"question": question})
        # Ensure the result is in a JSON serializable format
        response = {"response": result.content if hasattr(result, 'content') else str(result)}
    except Exception as e:
        response = {"error": str(e)}
    return jsonify(response)


if __name__ =='__main__':
    app.run(debug=True)
