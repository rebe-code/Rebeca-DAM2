package com.pspr.controller;

import com.pspr.model.User;
import com.pspr.security.dto.LoginRequest;
import com.pspr.security.dto.LoginResponse;
import com.pspr.security.jwt.JwtUtil;
import com.pspr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService usuarioService;
    private final JwtUtil jwtUtil;
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // consulta a servicio para comprobar usuario y pass //
        boolean usuarioValido = usuarioService.validaUsuarioPassword(request.getUsername(), request.getPassword());
        if (usuarioValido) {
            Optional<User> optUser = usuarioService.buscarPorNombre(request.getUsername());
            String token = jwtUtil.generateToken(optUser.get().getUsername()); // TODO generar token
            return ResponseEntity.ok(new LoginResponse(token));
        }

        // Error 401 (Unathorized)
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}

