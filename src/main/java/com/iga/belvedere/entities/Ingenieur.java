package com.iga.belvedere.entities;



import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;

@Entity
public class Ingenieur extends Utilisateur{
	
	private String titre;
	private String pays;
	private String ville;
	private String region;
	private int age;
	
	@OneToOne
	private Profil profil;

	
	

	public Ingenieur() {
		super();
	}

	

	public Ingenieur(String titre, String pays, String ville, String region, int age, Profil profil) {
		super();
		this.titre = titre;
		this.pays = pays;
		this.ville = ville;
		this.region = region;
		this.age = age;
		this.profil = profil;
	}



	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}
	
	
}
