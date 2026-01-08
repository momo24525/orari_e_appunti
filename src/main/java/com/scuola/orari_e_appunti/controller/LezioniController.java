package com.scuola.orari_e_appunti.controller; // Package corretto


import com.scuola.orari_e_appunti.dto.LezioneDTO;
import com.scuola.orari_e_appunti.services.LezioneService;
import com.scuola.orari_e_appunti.services.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Assicurati che la porta sia quella del tuo frontend
@RestController
@RequestMapping("/api/lezioni") // Tutte le richieste a questo controller inizieranno con /api/studenti
public class LezioniController {


    @Autowired
    private LezioneService lezioneService;
    @Autowired
    private StudenteService studenteService;

    // Endpoint per ottenere tutti gli studenti
    @GetMapping
    public ResponseEntity<List<LezioneDTO>> getAllLezioni() {
        List<LezioneDTO> lezione = lezioneService.getAllLezioni();
        return ResponseEntity.ok(lezione); // Restituisce 200 OK con la lista nel corpo della risposta
    }

    @GetMapping("/oraristudente")
    public ResponseEntity<List<LezioneDTO>> mostraOrarioStudente(@AuthenticationPrincipal UserDetails userDetails)
    {
        return ResponseEntity.ok(lezioneService.getOrarioPerStudente(userDetails.getUsername()));
    }

    @GetMapping("/orariprofessore")
    public ResponseEntity<List<LezioneDTO>> mostraOrarioProfessore(@AuthenticationPrincipal UserDetails userDetails)
    {
        return ResponseEntity.ok(lezioneService.getOrarioPerProf(userDetails.getUsername()));
    }



}

