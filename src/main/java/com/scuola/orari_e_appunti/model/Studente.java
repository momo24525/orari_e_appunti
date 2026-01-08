package com.scuola.orari_e_appunti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "studente")
public class Studente {


    // ---- CAMPI ----

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @ManyToOne(optional = true)
    @JoinColumn(name = "classe_id", nullable = true)
    private Classe classe;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Studente() {}

    public Studente(String nome, String cognome, Classe classe, User user) {
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
        this.user = user;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }


}
