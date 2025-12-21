package com.scuola.orari_e_appunti.dto;

public class ClasseDTO {

    private String sezione;
    private String articolazione;
    private Integer anno;

    public ClasseDTO () {}

    public ClasseDTO(String sezione, String articolazione, Integer anno) {
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
}
