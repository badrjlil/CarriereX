package com.iga.belvedere.entities;

import java.sql.Date;
import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cour")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	@Column(length = 5000)
	private String contenu;
	private Date date;
	private int vues;
	
	@Column(columnDefinition = "mediumblob")
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name = "id_employeur")
	private Employeur employeur;
	
	public Cours() {
	}
	

	public Cours(int id, String titre, String contenu, Date date, int vues, byte[] image, Employeur employeur) {
		super();
		this.id = id;
		this.titre = titre;
		this.contenu = contenu;
		this.date = date;
		this.vues = vues;
		this.image = image;
		this.employeur = employeur;
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
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Employeur getEmployeur() {
		return employeur;
	}
	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}
	public String getImage() {
		return Base64.getEncoder().encodeToString(this.image);
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getVues() {
		return vues;
	}
	public void setVues(int vues) {
		this.vues = vues;
	}
	
	

	
}
