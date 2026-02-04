package com.adt.ejercicios_clase.service;

import com.adt.ejercicios_clase.model.Mensaje;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MensajeService {
    private final MongoTemplate mongoTemplate;
    public MensajeService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Mensaje> mensajesDeUser(String user) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("user").is(user))
        );
        return mongoTemplate.aggregate(agg, "mensajes", Mensaje.class).getMappedResults();
    }

    public List<Mensaje> mensajesDeUserYRoom(String user, String room) {

        return null;
    }
}
