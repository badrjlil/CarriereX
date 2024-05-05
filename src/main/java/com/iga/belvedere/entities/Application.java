package com.iga.belvedere.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date dateApplication;
	@Lob
	@Column(columnDefinition = "mediumblob")
    private byte[] cv;
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] lettreMotivation;
	@ManyToOne
	@JoinColumn(name = "ingenieur_id")
	private Ingenieur ingenieur;
	@ManyToOne
	@JoinColumn(name = "emploi_id")
	private Emploi emploi;
	public Application() {
		super();
	}
	public Application(int id, Date dateApplication, byte[] cv, byte[] lettreMotivation, Ingenieur ingenieur,
			Emploi emploi) {
		super();
		this.id = id;
		this.dateApplication = dateApplication;
		this.cv = cv;
		this.lettreMotivation = lettreMotivation;
		this.ingenieur = ingenieur;
		this.emploi = emploi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateApplication() {
		return dateApplication;
	}
	public void setDateApplication(Date dateApplication) {
		this.dateApplication = dateApplication;
	}
	public byte[] getCv() {
		return cv;
	}
	public void setCv(byte[] cv) {
		this.cv = cv;
	}
	public byte[] getLettreMotivation() {
		return lettreMotivation;
	}
	public void setLettreMotivation(byte[] lettreMotivation) {
		this.lettreMotivation = lettreMotivation;
	}
	public Ingenieur getIngenieur() {
		return ingenieur;
	}
	public void setIngenieur(Ingenieur ingenieur) {
		this.ingenieur = ingenieur;
	}
	public Emploi getEmploi() {
		return emploi;
	}
	public void setEmploi(Emploi emploi) {
		this.emploi = emploi;
	}
	
	
	
}
