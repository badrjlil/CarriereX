package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Ingenieur;
import com.iga.belvedere.entities.Profil;

public interface profilRepository extends JpaRepository<Profil, Integer> {

	@Query("SELECT p FROM Profil p WHERE p.ingenieur = :ingenieur")
	public Profil findByIng(@Param("ingenieur") Ingenieur ingenieur);

}
