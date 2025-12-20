package com.scuola.orari_e_appunti.controller; // Package corretto


import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.services.Auth.AuthService;
import com.scuola.orari_e_appunti.services.ClasseService;
import com.scuola.orari_e_appunti.services.ProfessoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classi") // Tutte le richieste a questo controller inizieranno con /api/studenti
public class ClassiController {


    @Autowired
    private ClasseService classeService;

    // Endpoint per ottenere tutti gli studenti
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClassi() {
        List<Classe> classi = classeService.getAllClassi();
        return ResponseEntity.ok(classi); // Restituisce 200 OK con la lista nel corpo della risposta
    }



}

