package com.scuola.orari_e_appunti.dto.Auth;

public class LoginRequestDTO {

    private String email;
    private String password;

    // Getters e Setters sono necessari per la deserializzazione JSON


    public LoginRequestDTO() {}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
