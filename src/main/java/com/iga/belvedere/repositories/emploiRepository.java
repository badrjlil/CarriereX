package com.iga.belvedere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Emploi;

public interface emploiRepository extends JpaRepository<Emploi, Integer>{
	
	@Query("SELECT emp FROM Emploi emp WHERE emp.titre LIKE %:keyword%")
	public List<Emploi> findAllByKeyword(@Param("keyword") String keyword);

}
