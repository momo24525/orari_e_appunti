package com.scuola.orari_e_appunti.mapper;

import com.scuola.orari_e_appunti.dto.LezioneDTO;
import com.scuola.orari_e_appunti.model.Lezione;

public class LezioneMapper {

    public static LezioneDTO toDTO(Lezione lezione) {
        if (lezione == null) {
            return null;
        }

        return new LezioneDTO(
                lezione.getId(),
                lezione.getOra().name(),
                lezione.getGiorno().name(),
                lezione.getMateria().name(),
                lezione.getProfessore().getNome(),
                lezione.getClasse().getNome()

        );
    }

}
