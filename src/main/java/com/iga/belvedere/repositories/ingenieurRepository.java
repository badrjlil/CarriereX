package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iga.belvedere.entities.Ingenieur;

public interface ingenieurRepository extends JpaRepository<Ingenieur, Integer>{
	@Query("SELECT ing FROM Ingenieur ing WHERE ing.email = :email AND ing.password = :password")
	public Ingenieur fetchUser(@Param("email") String email, @Param("password") String password);

}
