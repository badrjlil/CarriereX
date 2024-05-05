package com.iga.belvedere.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ingenieur extends Utilisateur {

	private int age;
	private String sexe;

	@OneToOne
	private Profil profil;

	@OneToMany(mappedBy = "ingenieur")
	private List<Application> applications;

	public Ingenieur() {
		super();
	}

	public Ingenieur(int age, String sexe, Profil profil, List<Application> applications) {
		super();
		this.age = age;
		this.sexe = sexe;
		this.profil = profil;
		this.applications = applications;
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

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

}
