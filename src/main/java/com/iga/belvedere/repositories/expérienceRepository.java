package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Expérience;
import com.iga.belvedere.entities.Profil;


public interface expérienceRepository extends JpaRepository<Expérience, Integer>{
	@Query("SELECT e FROM Expérience e WHERE e.profil = :profil")
	public List<Expérience> findAllByProfil(@Param("profil") Profil profil);
}
