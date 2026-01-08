package com.scuola.orari_e_appunti.controller;

import com.scuola.orari_e_appunti.dto.Auth.Login.LoggedUserDTO;
import com.scuola.orari_e_appunti.mapper.LoggedUserMapper;
import com.scuola.orari_e_appunti.model.User;
import com.scuola.orari_e_appunti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (userDetails == null) {
                throw new RuntimeException("Nessun utente autenticato trovato nel contesto.");
            }

            String email = userDetails.getUsername();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Utente non trovato nel database con email: " + email));

            LoggedUserDTO dto = LoggedUserMapper.toDto(user);

            return ResponseEntity.ok(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Errore nel recupero dell'utente: " + e.getMessage());
        }
    }
}
