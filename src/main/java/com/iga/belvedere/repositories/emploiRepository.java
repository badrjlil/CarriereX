package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Ville;

public interface emploiRepository extends JpaRepository<Emploi, Integer>{
	
	@Query("SELECT emp FROM Emploi emp WHERE emp.titre LIKE %:keyword% AND emp.ville.id = :ville AND emp.catégorie.id = :categorie")
	public List<Emploi> findAllByKeyword(@Param("keyword") String keyword, @Param("ville") int ville, @Param("categorie") int category);

	
	@Query("SELECT emp FROM Emploi emp WHERE emp.titre LIKE %:keyword% AND emp.ville.id = :ville")
	public List<Emploi> findAllByKeyword(@Param("keyword") String keyword, @Param("ville") int ville);
}
