package com.scuola.orari_e_appunti.dto;

public class StudenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Long classeId;
    private String email;


    public StudenteDTO() {
    }

    public StudenteDTO(Long id, String nome, String cognome, Long classeId, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.classeId = classeId;
        this.email = email;
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

    public Long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
