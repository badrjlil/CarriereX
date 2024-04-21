package com.iga.belvedere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Keyword {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "emploi_id")
    private Emploi emploi;

	public Keyword(int id, String keyword, Emploi emploi) {
		super();
		this.id = id;
		this.nom = keyword;
		this.emploi = emploi;
	}
	

	public Keyword() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Emploi getEmploi() {
		return emploi;
	}

	public void setEmploi(Emploi emploi) {
		this.emploi = emploi;
	}

	
    
    
}
