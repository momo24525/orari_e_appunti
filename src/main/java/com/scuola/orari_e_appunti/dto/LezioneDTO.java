package com.scuola.orari_e_appunti.dto;
import com.scuola.orari_e_appunti.model.Classe;

public class LezioneDTO {

    private String ora;
    private String giorno;
    private String materia;
    private String nomeProfessore;
    private String nomeClasse;

    public LezioneDTO () {}

    public LezioneDTO(String ora, String giorno, String materia, String nomeProfessore, String nomeClasse) {
        this.ora = ora;
        this.giorno = giorno;
        this.materia = materia;
        this.nomeProfessore = nomeProfessore;
        this.nomeClasse = nomeClasse;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNomeProfessore() {
        return nomeProfessore;
    }

    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }
}
