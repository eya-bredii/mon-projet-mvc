package com.data.examen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.examen.Entities.Compte;

public interface CompteRepository extends JpaRepository<Compte,Long> {

}
