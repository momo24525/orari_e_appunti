package com.scuola.orari_e_appunti.services.jwt;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeleteToken {

    private Set<String> blacklistedTokens = new HashSet<>();

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
        // --- STAMPA DI DEBUG ---
        System.out.println("--- TOKEN REVOCATO ---");
        System.out.println("Token aggiunto alla blacklist: " + token);
        System.out.println("Totale token in blacklist: " + blacklistedTokens.size());
        System.out.println("Lista completa: " + blacklistedTokens);
        System.out.println("----------------------");
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
