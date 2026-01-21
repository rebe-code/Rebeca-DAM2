package com.pspr.security.dto;
/**
 * En la respuesta devolveremos el token
 * */
public class LoginResponse {
    private String token;
    public LoginResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
