package com.example.demo.auth.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @Size(max=15)
    private String mobile;

    private String carrier;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
