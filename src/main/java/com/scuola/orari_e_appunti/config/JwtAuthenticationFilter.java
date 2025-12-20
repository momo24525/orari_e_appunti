package com.scuola.orari_e_appunti.config;

import com.scuola.orari_e_appunti.services.jwt.DeleteToken;
import com.scuola.orari_e_appunti.services.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Registriamo il filtro come un componente gestito da Spring
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService; // Il "traduttore" che carica gli utenti
    @Autowired
    private DeleteToken deleteToken;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Prendi l'header "Authorization" dalla richiesta
        final String authHeader = request.getHeader("Authorization");

        // 2. Se l'header non esiste o non inizia con "Bearer ", è una richiesta senza token.
        // In questo caso, passiamo la richiesta al prossimo filtro e terminiamo.
        // Sarà compito di Spring Security bloccarla più avanti se l'endpoint è protetto.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Estrai il token vero e proprio (rimuovendo il prefisso "Bearer ")
        final String jwt = authHeader.substring(7);

        // 4. Estrai l'email dal token usando il nostro JwtService
        final String userEmail = jwtService.extractUsername(jwt);

        // 5. Se abbiamo un'email e l'utente non è ancora stato autenticato in questa richiesta...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Carica i dettagli dell'utente dal database usando l'email
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            boolean isTokenValid = jwtService.isTokenValid(jwt,userDetails);
            boolean isTokenBlacklisted = deleteToken.isBlacklisted(jwt);
            // 6. Verifica se il token è valido per questo utente
            if (isTokenValid && !isTokenBlacklisted) {
                // Se il token è valido, crea un oggetto di autenticazione
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // La password non è necessaria qui
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 7. Imposta l'autenticazione nel contesto di sicurezza di Spring.
                // Da questo momento, Spring sa che l'utente è autenticato per questa richiesta.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 8. Passa la palla al prossimo filtro nella catena.
        filterChain.doFilter(request, response);
    }
}