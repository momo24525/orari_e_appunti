package com.scuola.orari_e_appunti.dto.Auth.Registrazione;

import jakarta.validation.constraints.*;

public class RegistrationRequestDTO {

    private String nome;
    private String cognome;
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, message = "La lunghezza minima è di 8 caratteri")
    private String password;



    // Getters e Setters sono necessari per la deserializzazione JSON

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
