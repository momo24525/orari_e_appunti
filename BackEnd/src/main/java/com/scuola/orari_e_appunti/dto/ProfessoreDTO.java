package com.scuola.orari_e_appunti.dto;

import com.scuola.orari_e_appunti.model.Classe;

import java.util.Set;

public class ProfessoreDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private Set<Long> classi;

    public ProfessoreDTO() {
    }

    public ProfessoreDTO(Long id, String nome, String cognome, String email, String telefono, Set<Long> classi) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.classi = classi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Set<Long> getClassi() {
        return classi;
    }

    public void setClassi(Set<Long> classi) {
        this.classi = classi;
    }
}
