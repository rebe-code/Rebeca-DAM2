package com.adt.ejercicios_clase.model;

public class Attachment {
    private String nombre;
    private String url;
    private String mimeType; // image/jpg applicacion/pdf

    public Attachment(String nombre, String url, String mimeType) {
        this.nombre = nombre;
        this.url = url;
        this.mimeType = mimeType;
    }
    public Attachment() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
