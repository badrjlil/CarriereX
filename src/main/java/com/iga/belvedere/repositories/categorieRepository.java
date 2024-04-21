package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Catégorie;

public interface categorieRepository extends JpaRepository<Catégorie, Integer>{
	
}
