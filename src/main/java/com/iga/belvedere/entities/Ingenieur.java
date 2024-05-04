package com.iga.belvedere.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;

@Entity
public class Ingenieur extends Utilisateur {

	private int age;
	private String sexe;

	@OneToOne
	private Profil profil;

	public Ingenieur() {
		super();
	}

	public Ingenieur(int age, String sexe, Profil profil) {
		super();
		this.age = age;
		this.sexe = sexe;
		this.profil = profil;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

}
