package com.adt.ejercicios_clase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "seguimientos")
public class Seguimiento {
    @Id
    private String id;

    private Integer idEmpresa;
    private String alumno;
    private String tipo;
    private Instant datetime;
    private String message;

    private List<Attachment> attachments;

    public Seguimiento(String id, Integer idEmpresa, String alumno, String tipo, Instant datetime, String message, List<Attachment> attachments) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.alumno = alumno;
        this.tipo = tipo;
        this.datetime = datetime;
        this.message = message;
        this.attachments = attachments;
    }
    public Seguimiento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
