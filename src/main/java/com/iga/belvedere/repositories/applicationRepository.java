package com.iga.belvedere.repositories;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Application;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Ingenieur;

public interface applicationRepository extends JpaRepository<Application, Integer>{
	@Query("SELECT app FROM Application app WHERE app.ingenieur = :ingenieur AND app.emploi = :emploi")
	public Application findByIngenieurAndEmploi(@Param("ingenieur") Ingenieur ingenieur, @Param("emploi") Emploi emploi);


}
