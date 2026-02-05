package com.pspr.controller;

import com.pspr.model.Alumno;
import com.pspr.security.dto.ErrorResponse;
import com.pspr.service.AlumnoService;
import com.pspr.security.exceptions.RecursoNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> listar() {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public Alumno buscarPorId(@PathVariable Integer id) {
        return alumnoService.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Alumno " + id + " no existe"));
    }

    @PostMapping
    public Alumno crear(@RequestBody Alumno alumno) {
        return alumnoService.save(alumno);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id) {
        if (alumnoService.findById(id).isEmpty()) {
            throw new RecursoNoEncontradoException("Alumno " + id + " no existe");
        }
        alumnoService.deleteById(id);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(RecursoNoEncontradoException ex, HttpServletRequest req) {
        return new ErrorResponse(404, "Not Found", ex.getMessage(), req.getRequestURI());
    }
}

