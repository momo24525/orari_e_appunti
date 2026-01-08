package com.scuola.orari_e_appunti.services.Auth;

import com.scuola.orari_e_appunti.dto.ProfessoreDTO;
import com.scuola.orari_e_appunti.dto.Auth.Registrazione.ProfessoreRegistrationRequestDTO;
import com.scuola.orari_e_appunti.dto.Auth.Registrazione.RegistrationRequestDTO;
import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.mapper.ProfessoreMapper;
import com.scuola.orari_e_appunti.mapper.StudenteMapper;
import com.scuola.orari_e_appunti.model.*;
import com.scuola.orari_e_appunti.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfessoreRepository professoreRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private StudenteRepository studenteRepository;

    @Transactional
    public ProfessoreDTO registerProfessore(ProfessoreRegistrationRequestDTO request) {
        // 1. Validazione: Controlla se l'email esiste già
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email già in uso!");
        }

        // Trova il ruolo corretto
        Role assignedRole = roleRepository.findByNome("ROLE_PROFESSORE")
                .orElseThrow(() -> new RuntimeException("Errore: ruolo professore non trovato"));

        // 2. Creazione User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(assignedRole);

        // 3. Creazione Professore
        Professore professore = new Professore();
        professore.setNome(request.getNome());
        professore.setCognome(request.getCognome());
        professore.setTelefono(request.getTelefono());
        professore.setUser(user); // Collega il professore all'utente



        // 5. Salvataggio
        Professore savedProfessore = professoreRepository.save(professore);

        // 6. Output: Converte in DTO e restituisce
        return ProfessoreMapper.toDto(savedProfessore);
    }

    @Transactional
    public StudenteDTO registerUser(RegistrationRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email già in uso!");
        }


        Role assignedRole = roleRepository.findByNome("ROLE_STUDENTE")
                .orElseThrow(() -> new RuntimeException("errore: ruolo studente non trovato"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(assignedRole);

        // 4. Crea e popola la nuova entità Studente
        Studente studente = new Studente();
        studente.setNome(request.getNome());
        studente.setCognome(request.getCognome());

        studente.setUser(user);
        Studente savedStudente = studenteRepository.save(studente);

        // 7. Converte l'entità salvata in DTO e la restituisce
        return StudenteMapper.toDto(savedStudente);
    }


}