package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Lezione;
import com.scuola.orari_e_appunti.repository.LezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LezioneService {

    @Autowired
    private LezioneRepository lezioneRepository;


    public List<Lezione> getOrarioByClasse(Classe classe) {
        return lezioneRepository.findByClasseOrderByGiornoAscOraAsc(classe);
    }

    public Lezione eliminaLezione(Long id) {
        Lezione l = lezioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lezione non trovata"));

        lezioneRepository.delete(l);
        return l;
    }

    public Lezione findById(Long id) {
        return lezioneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lezione con id " + id + " non trovata"));
    }

    public Lezione aggiornaLezione(Long id, Lezione lezioneAggiornata) {
        Lezione l = lezioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lezione non trovata"));

        // Aggiorni solo i campi modificabili
        lezioneAggiornata.setClasse(l.getClasse()); // forza la classe originale
        l.setGiorno(lezioneAggiornata.getGiorno());
        l.setOra(lezioneAggiornata.getOra());
        l.setMateria(lezioneAggiornata.getMateria());
        l.setProfessore(lezioneAggiornata.getProfessore());

        return lezioneRepository.save(l);
    }

    public Lezione salva(Lezione lezione) {
        return lezioneRepository.save(lezione);
    }


}