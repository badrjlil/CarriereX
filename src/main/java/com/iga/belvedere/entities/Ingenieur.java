package com.iga.belvedere.entities;

import java.util.Base64;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Ingenieur extends Utilisateur {

	private int age;
	private String sexe;
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] image;

	@OneToOne (mappedBy = "ingenieur")
    private Profil profil;

	@OneToMany(mappedBy = "ingenieur")
	private List<Application> applications;

	public Ingenieur() {
		super();
	}
	
	public Ingenieur(int age, String sexe, byte[] image, Profil profil, List<Application> applications) {
		super();
		this.age = age;
		this.sexe = sexe;
		this.image = image;
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

	public String getImage() {
		return Base64.getEncoder().encodeToString(this.image);
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
