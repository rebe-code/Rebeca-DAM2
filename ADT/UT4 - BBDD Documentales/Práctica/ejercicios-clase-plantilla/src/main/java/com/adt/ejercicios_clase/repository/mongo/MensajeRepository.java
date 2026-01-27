package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.repository.mongo.dto.DatosAdjuntoDTO;
import com.adt.ejercicios_clase.model.Mensaje;
import org.bson.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MensajeRepository extends MongoRepository<Mensaje, String> {
    List<Mensaje> findByUser(String user);
    List<Mensaje> findByRoom(String room);

    //Mostrar textos de mensajes de un usuario pasado por parametro
    @Query(
        value = "{ 'user': ?0 }",
        fields = "{ 'text': 1 }"
    )
    List<Mensaje> buscarTextosPorNombreUsuario(String nombreUsuario);

    @Query(
            value = "{ 'user': ?0, 'room'; ?1}",
            fields = "{ 'text': 1, 'room':1 }"
    )
    List<Mensaje> buscarTextosPorNombreUsuarioYRoom(String nombreUsuario, String room);
    @Query(
            value = "{ 'attachments.mimeType': ?0 }"
    )
    List<Mensaje> findMensajesPorMimeTipe(String mimeType);

    @Aggregation(pipeline = {
            "{ $match: { user: ?0 } }"
    })
    List<Mensaje> buscarPorUsuario(String user);

    @Aggregation(pipeline = {
            "{ $match: { room: ?0, $or: [ { user: ?1 }, { user: ?2 } ] }"
    })
    List<Mensaje> buscarPorUsuariosOr(String room, String user1, String user2);

    @Aggregation(pipeline = {
            ""
    })
    List<Mensaje> buscarDespuesDeFecha(Date fecha);

    @Aggregation(pipeline = {
            "{ $match: { user: ?0} }",
            "{ $sort: { datetime: 1} }"
            //" { $limit: 1} "
    })
    List<Mensaje> buscarPorUsuarioOrdenadoPorFechaAscendente(String usuario);

    @Aggregation(pipeline = {
            "{ $match: { user: ?0} }",
            "{ $sort: { datetime: -1} }",
             " { $limit: 1} "
    })
    Optional<Mensaje> buscarMensajeMasReciente(String user);

    @Aggregation(pipeline = {
            "{ $match: { user: ?0} }",
            "{ $sort: { datetime: -1} }",
            " { $limit: ?1} "
    })
    List<Mensaje> buscarMensajeMasRecienteLimit(String user, int limit);

    @Aggregation(pipeline = {
            "{ $match: { user: ?0 } }",
            "{ $unwind: '$attachments' }"
    })
    List<Document> adjuntosDeUsuario(String user);

    @Aggregation(pipeline = {
            "{ $unwind: '$attachments' }," +
            "{ $project: { _id: 0, nombreUsuario: '$user', adjunto: '$attachments.name', tipoAdjunto: '$attachments.mimeType'} }"
    })
    List<DatosAdjuntoDTO> datosAdjuntosDeUsuario();

    @Aggregation(pipeline = {
            "{ $unwind: '$attachments' }," +
            "{ $group: { _id: '$user', totalAdjuntos: { $sum:1 } } }",
            "{ $project: { _id:0, usuario: '$_id', totalAdjuntos: 1 } }",
            "{ $sort: { totalAdjuntos: -1 } }"
    })
    List<DatosAdjuntoDTO> contarAdjuntosDeUsuario();
}
