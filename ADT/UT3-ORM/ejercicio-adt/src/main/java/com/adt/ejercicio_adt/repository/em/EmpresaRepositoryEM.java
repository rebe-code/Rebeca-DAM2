package com.adt.ejercicio_adt.repository.em;

import com.adt.ejercicio_adt.model.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class EmpresaRepositoryEM {
    @PersistenceContext
    private EntityManager em;

    //private static Logger log = (Logger) LoggerFactory.getLogger(EmpresaRepositoryEM.class);

    @Transactional
    public Empresa crearEmpresa(Empresa e) {
        em.persist(e);
        return e; // devuelve la empresa con el Id creado
        // Al salir del metodo se hace flush() + Commit
    }

    @Transactional
    public Empresa actualizarEmpresa(Empresa empresa) {
        // merge devuelve una copia gestionada (managed) de la entidad
        Empresa managed = em.merge(empresa);
        return managed;
        // Al salir del metodo se hace flush() + Commit
    }

    //(readOnly = true) optimiza la transaccion,
    //en las consultas evita hacer el commit
    @Transactional(readOnly = true)
    public Empresa buscarPorId(Integer id) {
        return em.find(Empresa.class, id);
    }

    @Transactional(readOnly = true)
    public Empresa buscarPorIdPQL(Integer id) {
                                                //nombre de la clase no de la tabla
        String jpql = "SELECT e FROM Empresa e WHERE e.idEmpresa = :id";
        Empresa e = em.createQuery(jpql, Empresa.class)
                .setParameter("id",id)
                .getSingleResult();
        return e;
    }

    @Transactional(readOnly = true)
    public List<Empresa> listar() {
        // Seleccionar todas las empresas
        String jpql = "SELECT e FROM Empresa e";
        // Uso típico si esperamos varios resultados en una lista
        List<Empresa> empresas = em.createQuery(jpql, Empresa.class).getResultList();

        return empresas;
        }

    @Transactional
    public void borrarEmpresa(Empresa empresa) {
        Empresa managed = empresa;
        // Dentro de la transaccion
        // debemos comprobar si la entidad no esta gestionada (es detached),
        // la unimos al contexto primero con merge
        if (!em.contains(empresa)) {
            managed = em.merge(empresa);
        }
        em.remove(managed);
    }
}

