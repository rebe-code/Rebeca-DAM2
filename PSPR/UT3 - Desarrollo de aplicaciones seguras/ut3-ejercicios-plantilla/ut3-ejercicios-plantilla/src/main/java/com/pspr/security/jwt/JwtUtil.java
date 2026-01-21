package com.pspr.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    // Clave secreta para firmar el token
    @Value("${jwt.secret}")
    private String secretKey;

    // Tiempo de expiración del token en milisegundos (ej: 900000 = 15 min)
    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(String username) {
        // Fecha de creación
        Date issuedAt = new Date();

        // Fecha de expiración = ahora + tiempo configurado
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);
        return Jwts.builder()
                .setSubject(username) // "sub": usuario
                .setIssuedAt(issuedAt) // "iat": fecha creación
                .setExpiration(expiration) // "exp": fecha caducidad
                .signWith(SignatureAlgorithm.HS256 // Algoritmo de firma HS256
                        , secretKey) // Usa la clave secreta
                .compact(); // Construye el token (String)
    }
    public String extractUsername(String token) {
        // Devuelve el "subject" (usuario) guardado en el token
        return getClaims(token).getSubject();
    }
    public boolean validateToken(String token, String username) {
        // El token es válido si:
        // - el usuario dentro del token coincide
        // - el token no ha caducado
        String usernameFromToken = extractUsername(token);
        boolean notExpired = !isTokenExpired(token);
        return username.equals(usernameFromToken) && notExpired;
    }
    private Claims getClaims(String token) {
        // Parsea el token y devuelve el payload (claims)
        return Jwts.parser()
                .setSigningKey(secretKey) // misma clave usada para firmar
                .parseClaimsJws(token) // valida firma y formato
                .getBody(); // payload
    }
    private boolean isTokenExpired(String token) {
        // Compara la fecha de expiración con la fecha actual
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}
