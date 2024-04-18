package com.iga.belvedere.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expérience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String entreprise;
	private Date debut;
	private Date din;
	String description;
	
	@ManyToOne
	@JoinColumn(name = "id_profil")
	private Profil profil;
	
	public Expérience() {
		super();
	}

	public Expérience(int id, String titre, String entreprise, Date debut, Date din, String description,
			Profil profil) {
		super();
		this.id = id;
		this.titre = titre;
		this.entreprise = entreprise;
		this.debut = debut;
		this.din = din;
		this.description = description;
		this.profil = profil;
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

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getDin() {
		return din;
	}

	public void setDin(Date din) {
		this.din = din;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
	
	
}
