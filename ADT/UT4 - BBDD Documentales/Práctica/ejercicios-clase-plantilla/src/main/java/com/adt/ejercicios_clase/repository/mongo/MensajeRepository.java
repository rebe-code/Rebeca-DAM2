package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MensajeRepository extends MongoRepository<Mensaje, String> {
    List<Mensaje> findByUser(String user);
    List<Mensaje> findByRoom(String room);

    //Mostrar textos de mensajes de un usuario pasado por parametro
    @Query(
        value = "{ 'user': ?0 }",
        fields = "{ 'text': 1 }"
    )
    List<Mensaje> buscarTextosPorNombreUsuario(String nombreUsuario);

    List<Mensaje> findMensajesPorMimeTipe(String s);
}
