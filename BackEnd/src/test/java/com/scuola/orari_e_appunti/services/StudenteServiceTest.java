package com.scuola.orari_e_appunti.services;

import com.scuola.orari_e_appunti.dto.StudenteDTO;
import com.scuola.orari_e_appunti.model.Classe;
import com.scuola.orari_e_appunti.model.Role;
import com.scuola.orari_e_appunti.model.Studente;
import com.scuola.orari_e_appunti.model.User;
import com.scuola.orari_e_appunti.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// --- ANNOTAZIONI CHIAVE ---

// @SpringBootTest: Dice a Spring di caricare l'intera applicazione.
// È un test di integrazione, non un test unitario isolato.
@SpringBootTest
// @Transactional: Ogni test viene eseguito in una transazione che viene annullata alla fine.
// Questo significa che il database torna allo stato iniziale dopo ogni test,
// così i test non si influenzano a vicenda. FONDAMENTALE.
@Transactional
class StudenteServiceTest {

    // --- DEPENDENCY INJECTION ---
    // Iniettiamo il service che vogliamo testare e i repository
    // che ci servono per preparare i dati e verificare i risultati.
    @Autowired
    private StudenteService studenteService;

    @Autowired
    private ProfessoreRepository professoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClasseRepository classeRepository;

    private Classe classeDiTest;

    // --- PREPARAZIONE DEL TEST ---

    // @BeforeEach: Questo metodo viene eseguito PRIMA di ogni metodo di test.
    // Lo usiamo per preparare i dati necessari al test, come il ruolo e la classe.
    @BeforeEach
    void setUp() {
        // Pulisco i repository per essere sicuro che ogni test parta da una situazione pulita
        // (anche se @Transactional aiuta, questo dà una sicurezza in più).
        studenteRepository.deleteAll();
        professoreRepository.deleteAll();
        userRepository.deleteAll();
        classeRepository.deleteAll();
        roleRepository.deleteAll();

        // Creiamo e salviamo i dati PRE-REQUISITI per il nostro test.
        // Il nostro service si aspetta che un ruolo "ROLE_STUDENTE" e una classe esistano.
        Role studenteRole = new Role();
        studenteRole.setNome("ROLE_STUDENTE");
        roleRepository.save(studenteRole);

        Classe classe = new Classe();
        classe.setArticolazione("Informatica");
        classe.setAnno(2);
        classe.setSezione("A");
        classeDiTest = classeRepository.save(classe);
    }

    // --- IL TEST VERO E PROPRIO ---

    // @Test: Indica che questo è un metodo di test che JUnit deve eseguire.
    @Test
    void registerUser_ShouldCreateStudenteAndUserWithCorrectRole() {
        // Questo test verifica il "caso felice": la registrazione va a buon fine.

        // 1. ARRANGE (Prepara): Definiamo i dati di input per il nostro test.
        String email = "mario.rossi@test.com";
        String password = "password123";
        String nome = "Mario";
        String cognome = "Rossi";
        Long classeId = classeDiTest.getId();

        // 2. ACT (Agisci): Eseguiamo il metodo che vogliamo testare.
        StudenteDTO resultDTO = studenteService.registerUser(email, password, nome, cognome, classeId);

        // 3. ASSERT (Verifica): Controlliamo che il risultato sia quello che ci aspettiamo.

        // Verifica 3.1: Il DTO restituito non è nullo e ha i dati corretti.
        assertNotNull(resultDTO);
        assertEquals(nome, resultDTO.getNome());
        assertEquals(cognome, resultDTO.getCognome());

        // Verifica 3.2: L'utente è stato salvato nel database?
        Optional<User> savedUserOpt = userRepository.findByEmail(email);
        assertTrue(savedUserOpt.isPresent(), "L'utente avrebbe dovuto essere salvato nel database");
        User savedUser = savedUserOpt.get();

        // Verifica 3.3: L'utente ha il ruolo corretto? (LA VERIFICA DEL NOSTRO BUG)
        assertFalse(savedUser.getRoles().isEmpty(), "L'utente dovrebbe avere almeno un ruolo");
        boolean hasStudenteRole = savedUser.getRoles().stream()
                .anyMatch(role -> role.getNome().equals("ROLE_STUDENTE"));
        assertTrue(hasStudenteRole, "L'utente dovrebbe avere il ruolo ROLE_STUDENTE");

        // Verifica 3.4: Lo studente è stato salvato nel database?
        Optional<Studente> savedStudenteOpt = studenteRepository.findById(resultDTO.getId());
        assertTrue(savedStudenteOpt.isPresent(), "Lo studente avrebbe dovuto essere salvato nel database");
        Studente savedStudente = savedStudenteOpt.get();

        // Verifica 3.5: Lo studente è collegato all'utente e alla classe corretti?
        assertEquals(savedUser.getId(), savedStudente.getUser().getId());
        assertEquals(classeId, savedStudente.getClasse().getId());
    }
}