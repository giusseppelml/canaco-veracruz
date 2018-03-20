package com.beta.giusseppe.canacoveracruz.models;

/**
 * Created by Giusseppe on 30/10/2017.
 */

public class InfoSiem {
    private String encabezado;
    private String informacion;

    public InfoSiem(){}

    public InfoSiem(String encabezado, String informacion) {
        this.encabezado = encabezado;
        this.informacion = informacion;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
