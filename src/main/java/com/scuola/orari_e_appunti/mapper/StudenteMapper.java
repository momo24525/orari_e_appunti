package com.scuola.orari_e_appunti.mapper;

import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.model.Studente;

public class StudenteMapper {

    public static StudenteDTO toDto(Studente studente) {
        if (studente == null) {
            return null;
        }

        return new StudenteDTO(
                studente.getId(),
                studente.getNome(),
                studente.getCognome(),
                studente.getClasse() != null ? studente.getClasse().getId() : null,
                studente.getUser() != null ? studente.getUser().getEmail() : null
        );
    }
}
