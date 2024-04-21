package com.iga.belvedere.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Catégorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	
	@OneToMany(mappedBy = "catégorie")
	private List<Emploi> emploi;
		
	public Catégorie() {
		super();
	}
	
	public Catégorie(int id, String nom, List<Emploi> emploi) {
		super();
		this.id = id;
		this.nom = nom;
		this.emploi = emploi;
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
	public List<Emploi> getEmploi() {
		return emploi;
	}
	public void setEmploi(List<Emploi> emploi) {
		this.emploi = emploi;
	}
	
	

}
