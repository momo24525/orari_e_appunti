package com.scuola.orari_e_appunti.mapper;

import com.scuola.orari_e_appunti.dto.ProfessoreDTO;
import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Professore;

import java.util.stream.Collectors;

public class ProfessoreMapper {

    public static ProfessoreDTO toDto(Professore professore) {
        if (professore == null) {
            return null;
        }

        return new ProfessoreDTO(
                professore.getId(),
                professore.getNome(),
                professore.getCognome(),
                professore.getUser() != null ? professore.getUser().getEmail() : null,
                professore.getTelefono(),
                professore.getClassi().stream().map(Classe::getId).collect(Collectors.toSet())
        );
    }
}
