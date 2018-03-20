package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class DetalleDirectorio {
    private int id;
    private String nobre;
    private String direccion;
    private String imagen;
    private String telefono;
    private int categoria;

    DetalleDirectorio(){}

    public DetalleDirectorio(int id, String nobre, String direccion, String imagen, String telefono, int categoria) {
        this.id = id;
        this.nobre = nobre;
        this.direccion = direccion;
        this.imagen = imagen;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNobre() {
        return nobre;
    }

    public void setNobre(String nobre) {
        this.nobre = nobre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
