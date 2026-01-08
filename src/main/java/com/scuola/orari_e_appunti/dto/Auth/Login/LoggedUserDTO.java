package com.scuola.orari_e_appunti.dto.Auth.Login;
import java.util.Set;

public class LoggedUserDTO {

    private String email;
    private Long id;
    private Set<String> role;

    public  LoggedUserDTO () {}

    public LoggedUserDTO(String email, Long id, Set<String> role) {
        this.email = email;
        this.id = id;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
