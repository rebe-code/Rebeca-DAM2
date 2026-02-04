package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Seguimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface SeguimientoRepository extends MongoRepository<Seguimiento, String> {

    List<Seguimiento> findByIdEmpresa(Integer idEmpresa);
    @Query(
            value = "{ 'attachments.mimeType': ?0 }",
            fields = "{ 'alumno': 1, 'tipo': 1, 'datetime': 1, 'message': 1, '_id': 0 }"
    )
    List<Seguimiento> findByAttachmentMimeTypeProjected(String mimeType);
}
