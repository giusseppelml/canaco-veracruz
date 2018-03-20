package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class Contenido {
    private int id;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String fecha;
    private String categoria;
    private String estado;

    public Contenido(){}

    public Contenido(int id, String titulo, String descripcion, String imagen, String fecha, String categoria, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fecha = fecha;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
