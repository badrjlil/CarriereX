package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Keyword;

public interface keywordRepository extends JpaRepository<Keyword, Integer>{
	
}
