package com.scuola.orari_e_appunti.repository;

import com.scuola.orari_e_appunti.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

}