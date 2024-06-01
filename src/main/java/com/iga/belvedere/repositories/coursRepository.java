package com.iga.belvedere.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Cours;
import com.iga.belvedere.entities.Employeur;


public interface coursRepository extends JpaRepository<Cours, Integer>{
	@Query("SELECT c FROM Cours c ORDER BY vues DESC")
	public List<Cours> getPopularCourses();
	
	@Query("SELECT c FROM Cours c WHERE titre LIKE %:keyword%")
	List<Cours> findByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT c FROM Cours c WHERE c.employeur = :employeur ORDER BY c.id DESC")
	List<Cours> findAllByEmployeur(@Param("employeur") Employeur employeur);
}
