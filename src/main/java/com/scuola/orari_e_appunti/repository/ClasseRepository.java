package com.scuola.orari_e_appunti.repository;

import com.scuola.orari_e_appunti.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

    List<Classe> findBySezioneOrderByAnnoAsc(String classe);

    Optional<Classe> findById(Long id);

}