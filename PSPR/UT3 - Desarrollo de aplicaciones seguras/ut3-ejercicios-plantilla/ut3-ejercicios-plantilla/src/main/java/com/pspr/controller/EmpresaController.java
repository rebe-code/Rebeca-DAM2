package com.pspr.controller;


import com.pspr.model.Empresa;
import com.pspr.security.dto.ErrorResponse;
import com.pspr.security.dto.exceptions.RecursoNoEncontradoException;
import com.pspr.service.EmpresaService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private EmpresaService empresaService;

    private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);


    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listarEmpresas();
    }


    @GetMapping("/{id}")
    public Empresa buscarPorId(@PathVariable int id) {
        return empresaService.buscarPorId(id);
    }

    // --- MANEJO DE ERRORES ---
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse generic(RuntimeException ex,
                                 HttpServletRequest req) {
        return new ErrorResponse(500, "Internal Server Error",
                "Error interno. Inténtelo más tarde.", req.getRequestURI());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(RecursoNoEncontradoException ex,
                                  HttpServletRequest req) {
        return new ErrorResponse(404, "Not Found", ex.getMessage(), req.getRequestURI());
    }
}
