package com.iga.belvedere.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	@Column(length = 500)
	private String aperçu;
	private String pays;
	private String ville;
	private String region;
	private String Category;
	private int experience;

	@OneToOne
	private Ingenieur ingenieur;

	@OneToMany(mappedBy = "profil")
	private List<Formation> formations;
	@OneToMany(mappedBy = "profil")
	private List<Compétence> compétences;
	@OneToMany(mappedBy = "profil")
	private List<Expérience> expériences;
	@OneToMany(mappedBy = "profil")
	private List<Certification> certifications;
	@OneToMany(mappedBy = "profil")
	private List<Langue> langues;

	public Profil() {
		super();
	}

	public Profil(int id, String titre, String aperçu, String pays, String ville, String region, String category,
			int experience, Ingenieur ingenieur, List<Formation> formations, List<Compétence> compétences,
			List<Expérience> expériences, List<Certification> certifications, List<Langue> langues) {
		super();
		this.id = id;
		this.titre = titre;
		this.aperçu = aperçu;
		this.pays = pays;
		this.ville = ville;
		this.region = region;
		Category = category;
		this.experience = experience;
		this.ingenieur = ingenieur;
		this.formations = formations;
		this.compétences = compétences;
		this.expériences = expériences;
		this.certifications = certifications;
		this.langues = langues;
	}

	public List<Langue> getLangues() {
		return langues;
	}

	public void setLangues(List<Langue> langues) {
		this.langues = langues;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAperçu() {
		return aperçu;
	}

	public void setAperçu(String aperçu) {
		this.aperçu = aperçu;
	}

	public Ingenieur getIngenieur() {
		return ingenieur;
	}

	public void setIngenieur(Ingenieur ingenieur) {
		this.ingenieur = ingenieur;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public List<Compétence> getCompétences() {
		return compétences;
	}

	public void setCompétences(List<Compétence> compétences) {
		this.compétences = compétences;
	}

	public List<Expérience> getExpériences() {
		return expériences;
	}

	public void setExpériences(List<Expérience> expériences) {
		this.expériences = expériences;
	}

	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
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

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
