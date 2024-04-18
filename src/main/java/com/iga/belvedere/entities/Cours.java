package com.iga.belvedere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cour")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String description;
	private String fournisseur;
	private String niveaau;
	public Cours(int id, String titre, String description, String fournisseur, String niveaau) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.fournisseur = fournisseur;
		this.niveaau = niveaau;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getNiveaau() {
		return niveaau;
	}
	public void setNiveaau(String niveaau) {
		this.niveaau = niveaau;
	}
	

	
}
