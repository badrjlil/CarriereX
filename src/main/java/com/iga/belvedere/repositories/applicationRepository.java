package com.iga.belvedere.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Application;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Employeur;
import com.iga.belvedere.entities.Ingenieur;

public interface applicationRepository extends JpaRepository<Application, Integer>{
	@Query("SELECT app FROM Application app WHERE app.ingenieur = :ingenieur AND app.emploi = :emploi")
	public Application findByIngenieurAndEmploi(@Param("ingenieur") Ingenieur ingenieur, @Param("emploi") Emploi emploi);

	@Query("SELECT a FROM Application a WHERE a.ingenieur = :ingenieur")
    List<Application> findAllAppByIngenieur(@Param("ingenieur") Ingenieur ingenieur);

	@Query("SELECT a FROM Application a WHERE a.emploi.id = :emploiId")
	List<Application> findApplicationsByEmploiId(@Param("emploiId") int emploiId);
	
	@Query("SELECT a FROM Application a WHERE a.emploi.employeur = :employeur GROUP BY a.ingenieur")
	List<Application> findAllByEmployeur(@Param("employeur") Employeur employeur);
	
}
