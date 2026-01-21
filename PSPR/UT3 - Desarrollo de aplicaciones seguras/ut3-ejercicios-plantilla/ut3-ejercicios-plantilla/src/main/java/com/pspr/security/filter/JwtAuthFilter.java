package com.pspr.security.filter;

import com.pspr.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro que:
 * - Lee el token del header Authorization.
 * - Usa JwtUtil para extraer el username y validar el token.
 * - Si es válido, marca al usuario como autenticado.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    // Inyectamos JwtUtil y UserService por constructor
    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 0) Si es el login, no exigimos token: dejamos pasar y salimos del filtro
        String path = request.getServletPath();
        if (path.equals("/auth/login")) {
            // continua la cadena de filtros
            filterChain.doFilter(request, response);
            return;
        }
        // 1) Leer la cabecera Authorization
        String authHeader = request.getHeader("Authorization");
        // Si no hay cabecera o no empieza por "Bearer ", seguimos sin autenticación
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Respuesta de error //
            generarErrorResponse(response, "No autorizado: cabecera");
            return;
        }

        // 2) Sacar el token (sin "Bearer ")
        String token = authHeader.substring(7);

        // 3) Pedir a JwtUtil el username que hay dentro del token
        String username = jwtUtil.extractUsername(token);//TODO

        // 4) Si tenemos username y aún no hay nadie autenticado en este hilo
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 5) Validar el token con JwtUtil
            if (jwtUtil.validateToken(token, username)) {

                // Recuperamos los datos del usuario //
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 6) Crear la autenticación para Spring Security
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null, // sin credentials (usamos token ya generado, la password no la necesitam
                                userDetails.getAuthorities() // Roles
                        );

                // añade información extra de la petición (como IP, sessionID, etc.) al objeto de autenticación
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 7) Guardar la autenticación en el contexto
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
                generarErrorResponse(response, "No autorizado: token no valido");
            }
        }

        // 8) Continuar la cadena de filtros
        filterChain.doFilter(request, response);
    }
    private static void generarErrorResponse(HttpServletResponse response, String mensajeError) throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("""
{
"error": """ + mensajeError +"""
}
""");
}
}