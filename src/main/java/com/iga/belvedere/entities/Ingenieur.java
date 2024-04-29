package com.iga.belvedere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Ingenieur extends Utilisateur{
	
	@OneToOne
	private Profil profil;


	public Ingenieur(Profil profil) {
		super();
		this.profil = profil;
	}


	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	

	
}
