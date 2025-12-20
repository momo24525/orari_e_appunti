package com.scuola.orari_e_appunti.repository;

import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Giorno_Enum;
import com.scuola.orari_e_appunti.model.Lezione;
import com.scuola.orari_e_appunti.model.Ora_Enum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;



@Repository
public interface LezioneRepository extends JpaRepository<Lezione, Long> {

    // Questo è il metodo MAGICO che ci serve
    List<Lezione> findByClasseOrderByGiornoAscOraAsc(Classe classe);

    // (Opzionale, ma bello) un altro modo per prenderle tutte
    List<Lezione> findByClasse(Classe classe);

    boolean existsByClasseIdAndGiornoAndOra(Long classeId, Giorno_Enum giorno, Ora_Enum ora);
}