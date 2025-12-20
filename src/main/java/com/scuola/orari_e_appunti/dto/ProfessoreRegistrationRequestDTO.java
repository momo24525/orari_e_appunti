package com.scuola.orari_e_appunti.dto;

import com.scuola.orari_e_appunti.model.Classe;

import java.util.List;

public class ProfessoreRegistrationRequestDTO {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String telefono;
    private String classi;

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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getClassi() {
        return classi;
    }

    public void setClassi(String classi) {
        this.classi = classi;
    }
}
