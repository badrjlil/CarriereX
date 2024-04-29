package com.iga.belvedere.entities;

import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name="employeur")
public class Employeur extends Utilisateur{
	
	
	@OneToMany(mappedBy = "employeur")
	private List<Emploi> emploi;

	
	
	public Employeur() {
    }

	

	public Employeur(List<Emploi> emploi) {
		super();
		this.emploi = emploi;
	}



	public List<Emploi> getEmploi() {
		return emploi;
	}

	public void setEmploi(List<Emploi> emploi) {
		this.emploi = emploi;
	}

	

	

}
