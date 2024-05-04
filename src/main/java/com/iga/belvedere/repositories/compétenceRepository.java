package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Compétence;
import com.iga.belvedere.entities.Profil;

public interface compétenceRepository extends JpaRepository<Compétence, Integer>{
	@Query("SELECT c FROM Compétence c WHERE c.profil = :profil")
	public List<Compétence> findAllByProfil(@Param("profil") Profil profil);
}
