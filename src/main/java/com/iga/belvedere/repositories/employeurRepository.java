package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Employeur;

public interface employeurRepository extends JpaRepository<Employeur, Integer> {
	@Query("SELECT emp FROM Employeur emp  WHERE emp.email = :email AND emp.password = :password")
	public Employeur fetchUser(@Param("email") String email, @Param("password") String password);
}
