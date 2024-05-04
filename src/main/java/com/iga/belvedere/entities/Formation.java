package com.iga.belvedere.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String niveau;
	private String spécialité;
	private String institution;
	private Date debut;
	private Date fin;
	@Column(length = 300)
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_profil")
	private Profil profil;

	public Formation() {
		super();
	}

	public Formation(int id, String niveau, String spécialité, String institution, Date debut, Date fin,
			String description, Profil profil) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.spécialité = spécialité;
		this.institution = institution;
		this.debut = debut;
		this.fin = fin;
		this.description = description;
		this.profil = profil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getSpécialité() {
		return spécialité;
	}

	public void setSpécialité(String spécialité) {
		this.spécialité = spécialité;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
