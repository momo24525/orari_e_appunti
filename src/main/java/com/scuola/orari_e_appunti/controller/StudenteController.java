package com.scuola.orari_e_appunti.controller; // Package corretto

import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.services.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenti") // Tutte le richieste a questo controller inizieranno con /api/studenti
public class StudenteController {

    @Autowired
    private StudenteService studenteService;

    // Endpoint per ottenere tutti gli studenti
    @GetMapping
    public ResponseEntity<List<StudenteDTO>> getAllStudenti() {
        List<StudenteDTO> studenti = studenteService.getAllStudenti();
        return ResponseEntity.ok(studenti); // Restituisce 200 OK con la lista nel corpo della risposta
    }



}

