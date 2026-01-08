package com.scuola.orari_e_appunti.controller;

import com.scuola.orari_e_appunti.dto.Auth.Login.LoginRequestDTO;
import com.scuola.orari_e_appunti.dto.Auth.Login.LoginResponseDTO;
import com.scuola.orari_e_appunti.dto.ProfessoreDTO;
import com.scuola.orari_e_appunti.dto.Auth.Registrazione.ProfessoreRegistrationRequestDTO;
import com.scuola.orari_e_appunti.dto.Auth.Registrazione.RegistrationRequestDTO;
import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.services.Auth.AuthService;
import com.scuola.orari_e_appunti.services.jwt.DeleteToken;
import com.scuola.orari_e_appunti.services.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DeleteToken deleteToken;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        System.out.println("Tentativo di login per email: " + request.getEmail());

        try {
            // 1. Autenticazione
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            System.out.println("Autenticazione riuscita!");

            // 2. Se l'autenticazione ha successo, genera il token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(roleName -> roleName.substring(5))
                    .collect(Collectors.toList());

            System.out.println("Token generato: " + token);
            System.out.println("Ruolo: "+roles);
            // 3. Restituisci il token al client
            return ResponseEntity.ok(new LoginResponseDTO(token, roles));

        } catch (Exception e) {
            System.out.println("Errore durante il login: " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("/registerProfessore")
    public ResponseEntity<ProfessoreDTO> profRegister(@RequestBody ProfessoreRegistrationRequestDTO request) {
        ProfessoreDTO nuovoP = authService.registerProfessore(request);
        return new ResponseEntity<>(nuovoP, HttpStatus.CREATED);
    }

    @PostMapping("/registerStudente")
    public ResponseEntity<StudenteDTO> studenteRegister(@RequestBody RegistrationRequestDTO request) {
        StudenteDTO nuovoS = authService.registerUser(request);
        return  new ResponseEntity<>(nuovoS, HttpStatus.CREATED);
    }



}