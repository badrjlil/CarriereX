package com.iga.belvedere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Compétence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	
	@ManyToOne
	@JoinColumn(name = "id_profil")
	private Profil profil;
	
	public Compétence() {
		super();
	}

	public Compétence(int id, String nom,Profil profil) {
		super();
		this.id = id;
		this.nom = nom;
		
		this.profil = profil;
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

	

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
	
}
