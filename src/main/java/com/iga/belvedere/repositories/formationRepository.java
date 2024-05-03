package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Formation;
import com.iga.belvedere.entities.Profil;


public interface formationRepository  extends JpaRepository<Formation, Integer>{
	@Query("SELECT f FROM Formation f WHERE f.profil = :profil")
	public List<Formation> findAllByProfil(@Param("profil") Profil profil);

}
