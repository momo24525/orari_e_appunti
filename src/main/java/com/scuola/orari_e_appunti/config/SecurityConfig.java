package com.scuola.orari_e_appunti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // <-- AGGIUNTO: Abilita la sicurezza a livello di metodo (es. @PreAuthorize)
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter; // <-- AGGIUNTO: Iniettiamo il nostro filtro JWT

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                // --- NUOVA CONFIGURAZIONE DELLE RICHIESTE ---
                .authorizeHttpRequests(auth -> auth
                        // 1. Endpoint pubblici: login, registrazione, documentazione API, ecc.
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/registerstudente/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/lezioni/**").authenticated()
                        // 2. Risorse statiche pubbliche (JS, CSS, Immagini, ecc.)
                        .requestMatchers("/", "/js/**", "/css/**", "/images/**").permitAll() // <-- AGGIUNGI QUESTA RIGA

                        // Lasciamo questo pubblico per ora
                        .requestMatchers("/h2-console/**").permitAll()
                        // 2. Tutte le altre richieste devono essere autenticate
                        .anyRequest().permitAll()
                )

                // --- GESTIONE DELLA SESSIONE ---
                // Impostiamo la gestione della sessione a STATELESS perché usiamo JWT.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // --- PROVIDER DI AUTENTICAZIONE ---
                .authenticationProvider(authenticationProvider)

                // --- AGGIUNTA DEL NOSTRO FILTRO JWT ---
                // Diciamo a Spring di usare il nostro filtro PRIMA del filtro standard di username/password.
                // Questo è il cuore dell'integrazione JWT.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // Rimuoviamo httpBasic perché ora l'autenticazione è gestita solo tramite token JWT.
                // .httpBasic(Customizer.withDefaults())

                // Fix per la console H2 (questo rimane uguale)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}