package com.scuola.orari_e_appunti.controller;

import com.scuola.orari_e_appunti.services.jwt.DeleteToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class LogoutController {

    @Autowired
    private DeleteToken deleteToken;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Rimuove "Bearer "
        deleteToken.blacklistToken(token);
        return ResponseEntity.ok("Logout effettuato");
    }
}
