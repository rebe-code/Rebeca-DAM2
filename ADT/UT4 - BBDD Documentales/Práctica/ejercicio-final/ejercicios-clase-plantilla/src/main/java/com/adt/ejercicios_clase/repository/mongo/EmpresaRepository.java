package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
