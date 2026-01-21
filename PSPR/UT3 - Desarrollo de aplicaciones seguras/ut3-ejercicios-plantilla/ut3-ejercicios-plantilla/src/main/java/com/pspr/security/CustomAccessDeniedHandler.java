package com.pspr.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 (Forbidden), se quien eres, pero no tienes
        response.setContentType("application/json;charset=UTF-8");
        String body = """
{
"error": "Acceso denegado: no tienes permisos suficientes",
"path": "%s"
}
""".formatted(request.getRequestURI());
        response.getWriter().write(body);
    }
}
