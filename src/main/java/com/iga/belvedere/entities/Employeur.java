package com.iga.belvedere.entities;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name="employeur")
public class Employeur extends Utilisateur{
	
	private String status;
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] image;
	
	@OneToMany(mappedBy = "employeur")
	private List<Emploi> emploi;

	public Employeur() {
    }

	public Employeur(String status, byte[] image, List<Emploi> emploi) {
		super();
		this.status = status;
		this.image = image;
		this.emploi = emploi;
	}

	public List<Emploi> getEmploi() {
		return emploi;
	}

	public void setEmploi(List<Emploi> emploi) {
		this.emploi = emploi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Transient
	private String encodedImage;

	public String getEncodedImage() {
	    return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
	    this.encodedImage = encodedImage;
	}
	

}
