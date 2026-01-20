package com.adt.ejercicio_adt;

import com.adt.ejercicio_adt.jpa.EmpresaRepositoryJPA;
import com.adt.ejercicio_adt.model.Empresa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmpresaRepositoryTest {
    @Autowired
    private EmpresaRepositoryJPA empresaRepository;

    public void insertarEmpresa() {
        Empresa e = new Empresa();
        e.setNombre("Empresa");
        e = empresaRepository.save(e);
        System.out.println(e);
    }

    @Test
    void findById() {
        Empresa e = new Empresa();
        e.setNombre("Empresa");
        e = empresaRepository.save(e);
        Optional<Empresa> consultada = empresaRepository.findById(e.getIdEmpresa());
        Empresa empresa = consultada.get();
        System.out.println(consultada);
        /*Optional<Empresa> consultada = empresaRepository.findById(500);
        if (consultada.isPresent()){
            Empresa empresa = consultada.get();
            System.out.println("Empresa consultada " + empresa);
        }else{
            System.out.println("ERROR, empresa no encontrada");
        }*/
    }

    @Test
    void findAll() {
        List<Empresa> empresas = empresaRepository.findAll();
        System.out.println(empresas);
    }

    @Test
    void findByNombre(){
        Empresa empresaEncontrada = empresaRepository.findByNombre("empresaJunit");
        System.out.println("Empresa encontrada: " + empresaEncontrada);

        Empresa empresaNoEncontrada = empresaRepository.findByNombre("empresaNoExistente");
        if (empresaNoEncontrada == null) {
            System.out.println("Empresa no encontrada");
        }
    }

    @Test
    void findByNombreContainin(){
        List<Empresa> empresas = empresaRepository.findByNombreContaining("presa");
        System.out.println("Empresas encontradas :" + empresas);
    }

    @Test
    void findByNombreEmailTfno(){
        List<Empresa> empresas = empresaRepository.findByEmailAndTelefono(null,null);
        System.out.println("Empresas encontradas :" + empresas);
    }

    @Test
    void buscarPorNombreConteniendo(){
        List<Empresa> empresas = empresaRepository.buscarPorNombreConteniendo("");
        System.out.println("Empresas encontradas :" + empresas);
    }

    @Test
    void actualizar() {
        Empresa e = new Empresa();
        e.setNombre("Empresa");
        e = empresaRepository.save(e);
        Optional<Empresa> consultada = empresaRepository.findById(e.getIdEmpresa());
        if (consultada.isEmpty()) {
            System.out.println("Entidad no encontrada");
        } else {
            e = consultada.get();
            e.setNombre("modificado");
            e = empresaRepository.save(e);
            System.out.println(e);
        }
    }

    @Test
    void borrar() {
        Empresa e = new Empresa();
        e.setNombre("Empresa");
        e = empresaRepository.save(e);
        Optional<Empresa> consultada = empresaRepository.findById(e.getIdEmpresa());
        if (consultada.isEmpty()) {
            System.out.println("Entidad no encontrada");
        } else {
            e = consultada.get();
            empresaRepository.delete(e);
            System.out.println(e);
        }
    }
}
