package com.scuola.orari_e_appunti.model;

import jakarta.persistence.*;


@Entity
@Table(name = "lezioni")
public class Lezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---- CAMPI ----

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Giorno_Enum giorno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ora_Enum ora;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Materia_Enum materia;        // <-- solo così, niente ManyToOne

    @ManyToOne
    @JoinColumn(name = "professore_id", nullable = false)
    private Professore professore;  // <-- così è perfetto

    public Lezione() {}

    public Lezione(Classe classe, Giorno_Enum giorno, Ora_Enum ora, Materia_Enum materia, Professore professore) {
        this.classe = classe;
        this.giorno = giorno;
        this.ora = ora;
        this.materia = materia;
        this.professore = professore;
    }

    public Ora_Enum getOra() {
        return ora;
    }

    public void setOra(Ora_Enum ora) {
        this.ora = ora;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Giorno_Enum getGiorno() {
        return giorno;
    }

    public void setGiorno(Giorno_Enum giorno) {
        this.giorno = giorno;
    }



    public Materia_Enum getMateria() {
        return materia;
    }

    public void setMateria(Materia_Enum materia) {
        this.materia = materia;
    }

    public Professore getProfessore() {
        return professore;
    }

    public void setProfessore(Professore professore) {
        this.professore = professore;
    }

    @Override
    public String toString() {
        return giorno + " " + ora + " → " + materia + " (" +
                (professore != null ? professore.getNome() : "???") +
                ") - " + classe.getNome();
    }


}
