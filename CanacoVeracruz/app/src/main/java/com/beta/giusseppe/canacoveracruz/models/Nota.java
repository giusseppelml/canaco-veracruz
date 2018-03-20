package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class Nota {
    private String titulo;
    private String descripcion;
    private String imagen;
    private String fecha;

    public Nota(){}

    public Nota(String titulo, String descripcion, String imagen, String fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
