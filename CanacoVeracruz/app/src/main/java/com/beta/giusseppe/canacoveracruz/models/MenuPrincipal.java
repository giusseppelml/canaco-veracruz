package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class MenuPrincipal {
    private String nombre;
    private int icono;

    public MenuPrincipal(){}

    public MenuPrincipal(String nombre, int icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
