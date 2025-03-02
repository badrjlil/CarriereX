package com.iga.belvedere.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="langue")
public class Langue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@OneToMany(mappedBy = "langue")
	private List<Emploi> emplois;
	
	@ManyToOne
	@JoinColumn(name = "id_profil")
	private Profil profil;
	
	public Langue() {
		super();
	}

	

	public Langue(int id, String nom, List<Emploi> emplois, Profil profil) {
		super();
		this.id = id;
		this.nom = nom;
		this.emplois = emplois;
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



	public List<Emploi> getEmplois() {
		return emplois;
	}



	public void setEmplois(List<Emploi> emplois) {
		this.emplois = emplois;
	}



	public Profil getProfil() {
		return profil;
	}



	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
	
}
