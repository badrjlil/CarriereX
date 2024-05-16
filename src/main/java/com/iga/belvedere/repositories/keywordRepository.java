package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Keyword;

public interface keywordRepository extends JpaRepository<Keyword, Integer>{
	List<Keyword> findByEmploi(Emploi emploi);
}
