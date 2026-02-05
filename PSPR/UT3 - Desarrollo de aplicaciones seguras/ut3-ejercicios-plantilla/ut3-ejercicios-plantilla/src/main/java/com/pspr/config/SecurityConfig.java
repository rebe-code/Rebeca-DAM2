package com.pspr.config;

import com.pspr.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/empresas/**").hasRole("ADMIN")
                        .requestMatchers("/alumnos/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 401: no autenticado / token inválido
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.getWriter().write("""
                                {"status":401,"error":"Unauthorized","message":"No autenticado o token inválido","path":"%s"}
                                """.formatted(request.getRequestURI()));
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            // 403: autenticado pero sin rol
                            response.setStatus(403);
                            response.setContentType("application/json");
                            response.getWriter().write("""
                                {"status":403,"error":"Forbidden","message":"No tienes permisos para este recurso","path":"%s"}
                                """.formatted(request.getRequestURI()));
                        })
                );

        // Añadir filtro JWT antes del filtro de usuario/contraseña
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
