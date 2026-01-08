package com.scuola.orari_e_appunti.repository;

import com.scuola.orari_e_appunti.model.Studente;
import com.scuola.orari_e_appunti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
        boolean existsByEmail(String email);
    }

