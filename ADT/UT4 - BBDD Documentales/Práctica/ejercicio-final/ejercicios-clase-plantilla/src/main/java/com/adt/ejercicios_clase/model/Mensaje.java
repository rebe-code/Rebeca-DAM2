package com.adt.ejercicios_clase.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "mensajes")
public class Mensaje {
    @Id
    private String id;
    private String user;
    private String text;
    private String room;
    private Instant datetime;
    private List<Attachment> attachments;

    public Mensaje(String id, String user, String text, String room, Instant datetime) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.room = room;
        this.datetime = datetime;
    }

    public Mensaje() {

    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

