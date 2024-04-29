package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Ville;

public interface villeRepository extends JpaRepository<Ville, Integer>{
	
}
