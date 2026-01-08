package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.mapper.StudenteMapper;
import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Role;
import com.scuola.orari_e_appunti.model.Studente;
import com.scuola.orari_e_appunti.model.User;
import com.scuola.orari_e_appunti.repository.ClasseRepository;
import com.scuola.orari_e_appunti.repository.RoleRepository;
import com.scuola.orari_e_appunti.repository.StudenteRepository;
import com.scuola.orari_e_appunti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClasseRepository classeRepository;



    public List<StudenteDTO> getAllStudenti() {
        return studenteRepository.findAll()
                .stream()
                .map(StudenteMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<StudenteDTO> findByEmail(String email) {
        return studenteRepository.findByUserEmail(email)
                .stream()
                .map(StudenteMapper::toDto)
                .collect(Collectors.toList());
    }



}