package com.iga.belvedere.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "emploi")
public class Emploi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String nomEntreprise;
	private String emailEntreprise;
	private String type;
	private double salaire_min;
	private double salaire_max;
	private int experience;
	@Column(length = 2500)
	private String description;
	private Date deadline;
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] imageData;
	
	private String website;
	@Column(length = 1500)
	private String exigences;
	private LocalDate date;
	@OneToMany(mappedBy = "emploi")
	private List<Application> applications;

	@OneToMany(mappedBy = "emploi")
	private List<Keyword> keywords;

	@ManyToOne
	@JoinColumn(name = "langue_id")
	private Langue langue;

	@ManyToOne
	@JoinColumn(name = "id_employeur")
	private Employeur employeur;

	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Catégorie catégorie;

	@ManyToOne
	@JoinColumn(name = "id_ville")
	private Ville ville;

	public Emploi(int id, String titre, String nomEntreprise, String emailEntreprise, String type, double salaire_min,
			double salaire_max, int experience, String description, Date deadline, byte[] imageData, String website,
			String exigences, LocalDate date, List<Application> applications, List<Keyword> keywords, Langue langue,
			Employeur employeur, Catégorie catégorie, Ville ville) {
		super();
		this.id = id;
		this.titre = titre;
		this.nomEntreprise = nomEntreprise;
		this.emailEntreprise = emailEntreprise;
		this.type = type;
		this.salaire_min = salaire_min;
		this.salaire_max = salaire_max;
		this.experience = experience;
		this.description = description;
		this.deadline = deadline;
		this.imageData = imageData;
		this.website = website;
		this.exigences = exigences;
		this.date = date;
		this.applications = applications;
		this.keywords = keywords;
		this.langue = langue;
		this.employeur = employeur;
		this.catégorie = catégorie;
		this.ville = ville;
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

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getEmailEntreprise() {
		return emailEntreprise;
	}

	public void setEmailEntreprise(String emailEntreprise) {
		this.emailEntreprise = emailEntreprise;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSalaire_min() {
		return salaire_min;
	}

	public void setSalaire_min(double salaire_min) {
		this.salaire_min = salaire_min;
	}

	public double getSalaire_max() {
		return salaire_max;
	}

	public void setSalaire_max(double salaire_max) {
		this.salaire_max = salaire_max;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employeur getEmployeur() {
		return employeur;
	}

	public void setEmployeur(Employeur employeur) {
		this.employeur = employeur;
	}

	public Emploi() {
		super();
		 this.date = LocalDate.now();
	}

	public Catégorie getCatégorie() {
		return catégorie;
	}

	public void setCatégorie(Catégorie catégorie) {
		this.catégorie = catégorie;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getImageData() {
		return Base64.getEncoder().encodeToString(this.imageData);
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public String getExigences() {
		return exigences;
	}

	public void setExigences(String exigences) {
		this.exigences = exigences;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	

}
