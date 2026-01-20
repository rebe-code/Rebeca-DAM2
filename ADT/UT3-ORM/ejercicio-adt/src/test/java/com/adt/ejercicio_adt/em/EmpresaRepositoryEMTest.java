package com.adt.ejercicio_adt.em;

import com.adt.ejercicio_adt.repository.em.EmpresaRepositoryEM;
import com.adt.ejercicio_adt.model.Empresa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmpresaRepositoryEMTest {
    @Autowired // inyectado/instanciado por SpringBoot
    private EmpresaRepositoryEM empresaRepository;

    @Test
    void crearEmpresa() {
        Empresa e1 = new Empresa();
        e1.setNombre("EmpresaJUnitADT");
        e1 = empresaRepository.crearEmpresa(e1);
        System.out.println(e1);
    }

    @Test
    void actualizarEmpresa() {
        Empresa e1 = new Empresa();
        e1.setNombre("nombreEmpresa");
        e1 = empresaRepository.crearEmpresa(e1);
        e1.setNombre("nombreModificado");
        e1 = empresaRepository.actualizarEmpresa(e1);
        System.out.println(e1);
    }

    @Test
    void buscarPorID() {
        /*Empresa e1 = new Empresa();
        e1.setNombre("nombreEmpresa");
        e1.setSector("Software");
        e1 = empresaRepository.crearEmpresa(e1);
        e1 = empresaRepository.buscarPorId(e1.getIdEmpresa());*/
        Empresa e1 = empresaRepository.buscarPorId(1);
        System.out.println(e1);
    }

    @Test
    void buscarPorIDPQL() {
        Empresa e1 = empresaRepository.buscarPorIdPQL(3);
        System.out.println("Empresa: " + e1);
    }

    @Test
    void listar() {
        List<Empresa> empresas = empresaRepository.listar();
        System.out.println("Empresa: " + empresas);
    }

    @Test
    void borrar() {
        Empresa e = new Empresa();
        e.setIdEmpresa(40);
        empresaRepository.borrarEmpresa(e);
    }
}
