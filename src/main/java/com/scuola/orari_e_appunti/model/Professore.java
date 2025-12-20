package com.scuola.orari_e_appunti.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professore")
public class Professore  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String telefono;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "professore_classe",
               joinColumns = @JoinColumn(name = "professore_id"),
    inverseJoinColumns = @JoinColumn(name = "classe_id"))
    private Set<Classe> classi = new HashSet<>();




    public Professore() {}

    public Professore(String nome, String cognome, User user, String telefono, Set<Classe> classi) {
        this.nome = nome;
        this.cognome = cognome;
        this.user = user;
        this.telefono = telefono;
        this.classi = classi;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Classe> getClassi() {
        return classi;
    }

    public void setClassi(Set<Classe> classi) {
        this.classi = classi;
    }

    @Override
    public String toString() {
        return "Professore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", user=" + user +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }
}
