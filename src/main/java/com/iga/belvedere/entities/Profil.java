package com.iga.belvedere.entities;

import java.util.List;

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
	private String aperçu;
	
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
	
	public Profil() {
		super();
	}

	public Profil(int id, String aperçu, Ingenieur ingenieur, List<Formation> formations, List<Compétence> compétences,
			List<Expérience> expériences, List<Certification> certifications) {
		super();
		this.id = id;
		this.aperçu = aperçu;
		this.ingenieur = ingenieur;
		this.formations = formations;
		this.compétences = compétences;
		this.expériences = expériences;
		this.certifications = certifications;
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
	
	
	
	
}
