package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MensajeRepository extends MongoRepository<Mensaje, String> {
    List<Mensaje> findByUser(String user);
    List<Mensaje> findByRoom(String room);
}
