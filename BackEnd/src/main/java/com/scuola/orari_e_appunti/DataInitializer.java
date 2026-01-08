package com.scuola.orari_e_appunti;

import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Role;
import com.scuola.orari_e_appunti.model.User;
import com.scuola.orari_e_appunti.repository.ClasseRepository;
import com.scuola.orari_e_appunti.repository.RoleRepository;
import com.scuola.orari_e_appunti.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    // Inietta anche il RoleRepository
    @Bean
    CommandLineRunner init(ClasseRepository classeRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return args -> {

            // --- CREAZIONE CLASSI ---
            // Controlla se ci sono già classi per evitare duplicati a ogni riavvio
            if (classeRepository.count() == 0) {
                System.out.println("Creazione classi di esempio...");
                classeRepository.save(new Classe("A", "Informatica", 3));
                classeRepository.save(new Classe("B", "Informatica", 3));
                classeRepository.save(new Classe("A", "Liceo Matematico", 5));
            }

            // --- CREAZIONE RUOLI (FONDAMENTALE) ---
            // Controlla se ci sono già ruoli
            if (roleRepository.count() == 0) {
                System.out.println("Creazione ruoli di base...");
                roleRepository.save(new Role("ROLE_STUDENTE"));
                roleRepository.save(new Role("ROLE_PROFESSORE"));
                roleRepository.save(new Role("ROLE_ADMIN"));
            }

        /*    if (!userRepository.existsByEmail("admin@gmail.com")){
                System.out.println("Creazione utente admin...");

                User user = new User();

                Role adminRole = roleRepository.findByNome("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("errore, ruolo admin non trovato"));
                user.getRoles().add(adminRole);
                user.setEmail("admin@gmail.com");
                user.setPassword(passwordEncoder.encode("1234"));
                userRepository.save(user);


            }  */


        };
    }
}