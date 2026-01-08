package com.scuola.orari_e_appunti.mapper;

import com.scuola.orari_e_appunti.dto.Auth.Login.LoggedUserDTO;
import com.scuola.orari_e_appunti.model.Role;
import com.scuola.orari_e_appunti.model.User;

import java.util.stream.Collectors;

public class LoggedUserMapper {

    public static LoggedUserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        return new LoggedUserDTO(

                user.getEmail(),
                user.getId(),
                user.getRoles().stream()
                        .map(Role::getNome)
                        .collect(Collectors.toSet())

        );
    }
}
