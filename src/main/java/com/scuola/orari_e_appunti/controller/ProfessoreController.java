package com.scuola.orari_e_appunti.controller; // Package corretto

import com.scuola.orari_e_appunti.dto.ProfessoreDTO;
import com.scuola.orari_e_appunti.dto.ProfessoreRegistrationRequestDTO;
import com.scuola.orari_e_appunti.services.Auth.AuthService;
import com.scuola.orari_e_appunti.services.ProfessoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professori") // Tutte le richieste a questo controller inizieranno con /api/studenti
public class ProfessoreController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ProfessoreService professoreService;

    // Endpoint per ottenere tutti gli studenti
    @GetMapping
    public ResponseEntity<List<ProfessoreDTO>> getAllProfessori() {
        List<ProfessoreDTO> professori = professoreService.getAllProfessori();
        return ResponseEntity.ok(professori); // Restituisce 200 OK con la lista nel corpo della risposta
    }



}

