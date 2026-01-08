package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;


    public List<Classe> getClassiBySezione(String sezione) {
        return classeRepository.findBySezioneOrderByAnnoAsc(sezione);
    }

    // Metodo per prendere tutte le classi
    public List<Classe> getAllClassi() {
        return classeRepository.findAll();
    }


    public Classe getClasseById(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id non trovato"));
    }


    public Classe eliminaClasse(Long id) {
        Classe c = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trovata"));

        classeRepository.delete(c);
        return c;
    }

}
