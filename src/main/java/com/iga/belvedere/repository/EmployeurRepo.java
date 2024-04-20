package com.iga.belvedere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Employeur;

public interface EmployeurRepo extends JpaRepository<Employeur,Integer> {

}
