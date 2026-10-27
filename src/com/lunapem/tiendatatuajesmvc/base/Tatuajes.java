package com.lunapem.tiendatatuajesmvc.base;

import java.time.LocalDate;

public class Tatuajes extends Servicios {
    private String tamano;
    private String colorTinta;
    private String estilo;
    private int numeroSesiones;

    public Tatuajes(){
        super();
    }
    public Tatuajes(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona, String tamano, String colorTinta, String estilo, int numeroSesiones) {
        super(nombreCliente, telefonoCliente, fechaCita, precio, zona);
        this.tamano = tamano;
        this.colorTinta = colorTinta;
        this.estilo = estilo;
        this.numeroSesiones = numeroSesiones;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getColorTinta() {
        return colorTinta;
    }

    public void setColorTinta(String colorTinta) {
        this.colorTinta = colorTinta;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getNumeroSesiones() {
        return numeroSesiones;
    }

    public void setNumeroSesiones(int numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }

    @Override
    public String toString() {
        return "Tatuaje:" + "nombreCliente='" + getNombreCliente() + '\'' + ", telefonoCliente='" + getTelefonoCliente() + '\'' +
                ", fechaCita=" + getFechaCita() + ", precio=" + getPrecio() + ", zona='" + getZona();
    }
}
