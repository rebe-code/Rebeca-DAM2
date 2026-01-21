package com.pspr.config;

import com.pspr.security.CustomAccessDeniedHandler;
import com.pspr.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuración de seguridad para la API
 * - Protege endpoints Ej: empresas/**
 * - Usa JWT en la cabecera Authorization
 **/
@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler; // Para manejar el error de rol requerido //
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1) Desactivar CSRF con la sintaxis nueva de funciones flecha (no deprecated)
                .csrf(csrf -> {
                    csrf.disable();
                })
                // 2) API sin sesiones (stateless): cada petición trae su token
                .sessionManagement(sess -> {
                    sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // 3) Reglas de autorización sobre peticiones a los endpoint
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login").permitAll() // el endpoint login es público
                            .requestMatchers("/empresas/**").hasRole("ADMIN") // endpoints autenticacion (token) co
                            .anyRequest().authenticated(); // el resto de peticiones requiere autenticacion (token)
                })
                // Manejo de errores customizado:
                // Si el usuario tiene token valido pero no tiene el rol requerido devuelve 403 Forbidden//
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler) // <<--- AQUÍ
                )
                // 4) Añadir nuestro filtro que valida el token a la cadena de filtros
                // antes del filtro estandar UsernamePasswordAuthenticationFilter.class
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}