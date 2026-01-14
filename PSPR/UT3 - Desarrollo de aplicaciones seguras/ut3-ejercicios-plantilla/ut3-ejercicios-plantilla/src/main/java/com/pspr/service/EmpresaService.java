package com.pspr.service;


import com.pspr.model.Empresa;
import com.pspr.repository.EmpresaRepository;
import com.pspr.security.dto.exceptions.RecursoNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring la detecta como bean y podrás inyectarla
public class EmpresaService {
    private EmpresaRepository empresaRepository;

    private static final Logger log = LoggerFactory.getLogger(EmpresaService.class);

    // Inicializa el Repositorio (inyeccion de Spring por constructor)
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // listar todas las empresas
    public List<Empresa> listarEmpresas() {

        return empresaRepository.findAll();

    }

    // Buscar empresa por ID
    public Empresa buscarPorId(int id) throws RecursoNoEncontradoException{
          Optional<Empresa> optional =  empresaRepository.findById(id);
          if(optional.isPresent())
              return optional.get();
          else{
              // loguear empresa no encontrada //
              log.info("Empresa no encontrada");
              throw new RecursoNoEncontradoException("empresa no encontrada");
              //return null;
          }
    }

    //Insertar nueva empresa
    public Empresa insertarEmpresa(Empresa empresa) {

        return empresaRepository.save(empresa);
    }

    //Actualizar empresa existente
    public Empresa actualizarEmpresa(Empresa empresa) {

        return empresaRepository.save(empresa);

    }


    public List<Empresa> consultarEmpresasPorNombre (String nombre){

           return empresaRepository.findByNombreContaining(nombre);

    }

}