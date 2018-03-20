package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class Directorio {
    private int id;
    private String categoria;
    private String imagen;

    public Directorio(){}

    public Directorio(int id, String categoria, String imagen) {
        this.id = id;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
