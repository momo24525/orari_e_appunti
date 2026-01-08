package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.dto.LezioneDTO;
import com.scuola.orari_e_appunti.mapper.LezioneMapper;
import com.scuola.orari_e_appunti.model.*;
import com.scuola.orari_e_appunti.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class LezioneService {

    @Autowired
    private LezioneRepository lezioneRepository;
    @Autowired
    private StudenteRepository studenteRepository;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private ProfessoreRepository professoreRepository;




    public List<LezioneDTO> getAllLezioni() {
        return lezioneRepository.findAll()
                .stream()
                .map(LezioneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LezioneDTO> getOrarioPerStudente (String email) {

        Studente studente = studenteRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Studente non trovato"));

        Classe classeStudente = studente.getClasse();

        if (classeStudente == null) {
            throw new RuntimeException("Studente non assegnato a una classe");
        }

        return  lezioneRepository.findByClasseOrderByGiornoAscOraAsc(classeStudente)
                .stream()
                .map(LezioneMapper::toDTO)
                .collect(Collectors.toList());

    }

    // In LezioneService.java
    public List<LezioneDTO> getOrarioPerProf(String email) {

        // 1. Trova il professore tramite l'email
        Professore professore = professoreRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Professore non trovato"));

        // 2. Usa il nuovo metodo del repository per trovare direttamente le sue lezioni
        return lezioneRepository.findByProfessoreOrderByGiornoAscOraAsc(professore)
                .stream()
                .map(LezioneMapper::toDTO)
                .collect(Collectors.toList());
    }



}
