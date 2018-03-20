package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 21/10/2017.
 */

public class Siem {
    private int id;
    private String nombre;
    private String telefono;
    private String imagen;
    private String direccion;

    public Siem(){}

    public Siem(int id, String nombre, String telefono, String imagen, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
