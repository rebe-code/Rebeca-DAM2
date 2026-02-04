package com.adt.ejercicios_clase.model;

import com.adt.ejercicios_clase.repository.mongo.EmpresaRepository;
import com.adt.ejercicios_clase.repository.mongo.SeguimientoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class SeguimientoHibridoTest {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Test
    void resumenSeguimientosPorEmpresa() {
        Integer idEmpresa = 1;

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada: " + idEmpresa));


        List<Seguimiento> seguimientos = seguimientoRepository.findByIdEmpresa(idEmpresa);


        System.out.println("nombreEmpresa: " + empresa.getNombre());
        System.out.println("totalSeguimientos: " + seguimientos.size());

        List<Seguimiento> ultimosTres = seguimientos.stream()
                .sorted(Comparator.comparing(Seguimiento::getDatetime).reversed())
                .limit(3)
                .toList();

        System.out.println("=== Últimos 3 seguimientos ===");
        ultimosTres.forEach(s -> {
            System.out.println(
                    s.getDatetime() + " | " +
                            s.getTipo() + " | " +
                            s.getAlumno() + " | " +
                            s.getMessage()
            );
        });
    }
}
