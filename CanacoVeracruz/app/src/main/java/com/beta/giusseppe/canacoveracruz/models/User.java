package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 14/10/2017.
 */

public class User {
    private int id;
    private String nombre, numeroafiliado, role;

    public User(int id, String nombre, String numeroafiliado, String role) {
        this.id = id;
        this.nombre = nombre;
        this.numeroafiliado = numeroafiliado;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return nombre;
    }

    public String getEmail() {
        return numeroafiliado;
    }

    public String getGender() {
        return role;
    }
}
