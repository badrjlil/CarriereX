package com.iga.belvedere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iga.belvedere.entities.Employeur;

public interface employeurRepository extends JpaRepository<Employeur,Integer> {

}
