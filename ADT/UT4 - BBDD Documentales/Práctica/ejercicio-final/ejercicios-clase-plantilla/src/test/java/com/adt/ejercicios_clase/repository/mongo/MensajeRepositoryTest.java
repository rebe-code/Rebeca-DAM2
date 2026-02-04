package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Attachment;
import com.adt.ejercicios_clase.model.Mensaje;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MensajeRepositoryTest {
@Autowired
    MensajeRepository mensajeRepository;
    @Test
    void create() {
        // SAVE
        Mensaje nuevo = new Mensaje();
        nuevo.setText("Junit mensaje de texto");
        nuevo.setUser("JunitUser");
        nuevo.setRoom("Junit");
        nuevo.setDatetime(Instant.now());

        Mensaje insertado = mensajeRepository.insert(nuevo);
        System.out.println(insertado);
    }

    @Test
    void findById() {
        // SAVE
        // READ by id
        Mensaje nuevo = new Mensaje();
        nuevo.setText("Junit mensaje de texto");
        nuevo.setUser("JunitUser");
        nuevo.setRoom("Junit");
        nuevo.setDatetime(Instant.now());

        Mensaje insertado = mensajeRepository.insert(nuevo);

        Mensaje buscado = mensajeRepository.findById(insertado.getId()).get();
        assertEquals(insertado.getId(),buscado.getId());
    }

    @Test
    void update() {
        // SAVE
        // READ by id
        Mensaje nuevo = new Mensaje();
        nuevo.setText("Junit mensaje de texto");
        nuevo.setUser("JunitUser");
        nuevo.setRoom("Junit");
        nuevo.setDatetime(Instant.now());

        /*Mensaje insertado = mensajeRepository.insert(nuevo);
        //Modificamos datos
        insertado.setUser("JunitUserModificado");
        insertado.setRoom("JunitUserModificado");*/

        Mensaje buscado = mensajeRepository.findById("696fbe45a574f40617e75de0").get();
        buscado.setUser("JunitUserModificado");
        buscado.setRoom("JunitUserModificado");

        mensajeRepository.save(buscado);
    }

    @Test
    void deleteById() {
        // CREATE
        // delete by id
        mensajeRepository.deleteById("696fbe45a574f40617e75de0");
    }

    @Test
    void findByUser() {
        List<Mensaje> mensajeUsuario = mensajeRepository.findByUser("ana");
        //Mostrar texto de los mensajes de usuario
        for (Mensaje m : mensajeUsuario){
            System.out.println(m.getText());
        }
        assertNotNull(mensajeUsuario);
    }

    @Test
    void findByRoom() {
        List<Mensaje> mensajeUsuario = mensajeRepository.findByRoom("ana");
        //Mostrar texto de los mensajes de usuario
        for (Mensaje m : mensajeUsuario){
            System.out.println(m.getText());
        }
        assertNotNull(mensajeUsuario);
    }

    @Test
    void createWithAttachment(){
        // crear el mensaje
        Mensaje nuevo = new Mensaje();
        nuevo.setText("Junit mensaje de texto");
        nuevo.setUser("JunitUser");
        nuevo.setRoom("Junit");
        nuevo.setDatetime(Instant.now());

        // crear la lista de 2 adjuntos imagen y pdf
        Attachment attachment = new Attachment("Attachment","http://.../url","image.png");
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);

        // setear la lista dentro del mensaje
        nuevo.setAttachments(attachments);

        // insertar en db
        mensajeRepository.save(nuevo);

        // comprobar que haya insertado correctamente con findById
        Mensaje buscado = mensajeRepository.findById(nuevo.getId()).get();
        assertEquals(nuevo.getId(),buscado.getId());
    }

    @Test
    void buscarTextosPorNombreUsuario(){
        List<Mensaje> mensajes = mensajeRepository.buscarTextosPorNombreUsuario("JunitUser");
        assertNotNull(mensajes);
    }

    @Test
    void findMensajesPorMimeType(){
        List<Mensaje> mensajes = mensajeRepository.findMensajesPorMimeTipe("image.png");
        assertNotNull(mensajes);
    }

    @Test
    void buscarPorUsuariosOr(){
        List<Mensaje> mensajes = mensajeRepository.buscarPorUsuariosOr("Junit","JunitUser", "JunitUser");
        assertNotNull(mensajes);
    }

    @Test
    void buscarDespuesDeFecha(){
        List<Mensaje> mensajes = mensajeRepository.buscarDespuesDeFecha(Date.from(Instant.parse("2026-01-01T00:00:00Z")));
        assertNotNull(mensajes);
    }

    @Test
    void buscarPorUsuarioOrdenadoPorFechaAscendente(){
        List<Mensaje> mensajes = mensajeRepository.buscarPorUsuarioOrdenadoPorFechaAscendente("JunitUser");
        assertNotNull(mensajes);
    }
    @Test
    void buscarMensajeMasReciente(){
        Optional<Mensaje> mensajes = mensajeRepository.buscarMensajeMasReciente("JunitUser");
        assertNotNull(mensajes);
    }
    @Test
    void buscarMensajeMasRecienteLimit(){
        List<Mensaje> mensajes = mensajeRepository.buscarMensajeMasRecienteLimit("JunitUser",2);
        assertNotNull(mensajes);
    }
    @Test
    void adjuntosDeUsuario(){
        List<Document> mensajes = mensajeRepository.adjuntosDeUsuario("JunitUser");
        assertNotNull(mensajes);
    }
}