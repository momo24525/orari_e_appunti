package com.scuola.orari_e_appunti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // <-- Nota: @Controller, non @RestController
public class PageController {

    @GetMapping("/login") // Risponde all'URL principale
    public String showLoginPage() {
        return "login"; // Restituisce il nome del file HTML da mostrare
    }

    @GetMapping("/registerstudente") // Risponde all'URL principale
    public String showRegisterPageS() {
        return "Register/registerStudente"; // Restituisce il nome del file HTML da mostrare
    }

    @GetMapping("/registerprofessore") // Risponde all'URL principale
    public String showRegisterPageP() {
        return "Register/registerProfessore"; // Restituisce il nome del file HTML da mostrare
    }
}