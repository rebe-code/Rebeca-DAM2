package com.adt.ejercicios_clase.repository.mongo;

import com.adt.ejercicios_clase.model.Mensaje;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

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

        Mensaje buscado = mensajeRepository.findById(insertado.getId()).get();
        assertEquals(insertado.getId(),buscado.getId());
    }

    @Test
    void findById() {
        // SAVE
        // READ by id
    }
    @Test
    void update() {
        // SAVE
        // READ by id
    }
    @Test
    void deleteById() {
        // CREATE
        // delete by id
    }
    @Test
    void findByUser() {
    }
    @Test
    void findByRoom() {
    }
}