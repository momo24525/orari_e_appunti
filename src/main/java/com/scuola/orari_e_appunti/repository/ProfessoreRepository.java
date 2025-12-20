package com.scuola.orari_e_appunti.repository;

import com.scuola.orari_e_appunti.model.Professore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


    @Repository
    public interface ProfessoreRepository extends JpaRepository<Professore, Long> {


    }
