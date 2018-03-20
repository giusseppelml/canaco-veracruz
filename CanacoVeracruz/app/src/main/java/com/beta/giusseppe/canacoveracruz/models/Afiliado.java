package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class Afiliado {
    private String Titulo;
    private String Descripcion;
    private int icono;

    public Afiliado(){}

    public Afiliado(String titulo, String descripcion, int icono) {
        Titulo = titulo;
        Descripcion = descripcion;
        this.icono = icono;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
