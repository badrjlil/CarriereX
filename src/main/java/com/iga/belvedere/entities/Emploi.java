package com.iga.belvedere.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "emploi")
public class Emploi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String nomEntreprise;
	private String emailEntreprise;
	private String localisation;
	private String type;
	private double salaire;
	private int experience;
	private String description;
	@ManyToOne
	@JoinColumn(name="id_employeur")
	private Employeur employeur;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Catégorie catégorie;

	
	
	public Emploi(int id, String titre, String nomEntreprise, String emailEntreprise, String localisation, String type,
			double salaire, int experience, String description, Employeur employeur, Catégorie catégorie) {
		super();
		this.id = id;
		this.titre = titre;
		this.nomEntreprise = nomEntreprise;
		this.emailEntreprise = emailEntreprise;
		this.localisation = localisation;
		this.type = type;
		this.salaire = salaire;
		this.experience = experience;
		this.description = description;
		this.employeur = employeur;
		this.catégorie = catégorie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	public String getEmailEntreprise() {
		return emailEntreprise;
	}
	public void setEmailEntreprise(String emailEntreprise) {
		this.emailEntreprise = emailEntreprise;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSalaire() {
		return salaire;
	}
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employeur getEmployeur() {
		return employeur;
	}
	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}
	public Emploi() {
		super();
	}
	public Catégorie getCatégorie() {
		return catégorie;
	}
	public void setCatégorie(Catégorie catégorie) {
		this.catégorie = catégorie;
	}
	
	
	
}
