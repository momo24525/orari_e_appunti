package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.dto.ProfessoreDTO;
import com.scuola.orari_e_appunti.mapper.ProfessoreMapper;
import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Professore;
import com.scuola.orari_e_appunti.model.Role;
import com.scuola.orari_e_appunti.model.User;
import com.scuola.orari_e_appunti.repository.ClasseRepository;
import com.scuola.orari_e_appunti.repository.ProfessoreRepository;
import com.scuola.orari_e_appunti.repository.RoleRepository;
import com.scuola.orari_e_appunti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessoreService {

    @Autowired
    private ProfessoreRepository professoreRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Aggiunta la dipendenza a ClasseRepository, che ora è necessaria
    @Autowired
    private ClasseRepository classeRepository;

    public List<ProfessoreDTO> getAllProfessori() {
        return professoreRepository.findAll()
                .stream()
                .map(ProfessoreMapper::toDto)
                .collect(Collectors.toList());
    }

}
