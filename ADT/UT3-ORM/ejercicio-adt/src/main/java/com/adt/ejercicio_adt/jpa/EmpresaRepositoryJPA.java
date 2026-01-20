package com.adt.ejercicio_adt.jpa;

import com.adt.ejercicio_adt.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepositoryJPA extends JpaRepository<Empresa, Integer> {
// JpaRepository ya me proporciona findAll(), findById(), save(), deleteById(), etc
     Empresa findByNombre(String nombre);
    List<Empresa> findByNombreContaining(String texto);
    List<Empresa> findByEmailAndTelefono(String email, String telefono);

    /**
     * Mismo metodo que findByNombreContaining pero con mayor control
     * sobre la consulta utilizada:
     */
    @Query("SELECT e FROM Empresa e WHERE e.nombre LIKE %:texto%")
    List<Empresa> buscarPorNombreConteniendo(@Param("texto") String texto);

    @Query("SELECT e FROM Empresa e " +
            "WHERE e.nombre " +
            "LIKE %:texNombre% and" +
            "e.email LIKE %:textEmail")
    List<Empresa> buscarPorNombreEmail(@Param("textNombre") String textNombre,
                                       @Param("textEmail") String textEmail);
}
