package com.iga.belvedere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Ingenieur extends Utilisateur{
	
	@OneToOne
	private Profil profil;


	public Ingenieur(int id, String nom, String email, String password, Profil profil) {
		super(id, nom, email, password);
		this.profil = profil;
	}


	public Ingenieur(int id, String nom, String email, String password) {
		super(id, nom, email, password);
	}


	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	

	
}
