package com.scuola.orari_e_appunti.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "classe")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---- CAMPI ----

    @Column(nullable = false)
    private String sezione;

    @Column(nullable = false)
    private String articolazione;

    @Column(nullable = false)
    private Integer anno;

    public Classe() {}

    public Classe(String sezione, String articolazione, Integer anno) {
        this.sezione = sezione;
        this.articolazione = articolazione;
        this.anno = anno;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public String getArticolazione() {
        return articolazione;
    }

    public void setArticolazione(String articolazione) {
        this.articolazione = articolazione;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return anno + "^ " + sezione + " - " + articolazione;
    }


}
