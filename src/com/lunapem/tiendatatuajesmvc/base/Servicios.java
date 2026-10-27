package com.lunapem.tiendatatuajesmvc.base;

import java.time.LocalDate;

public abstract class Servicios {
    private String nombreCliente;
    private String telefonoCliente;
    private LocalDate fechaCita;
    private float precio;
    private String zona;

    public Servicios(){

    }

    public Servicios(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona) {
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.fechaCita = fechaCita;
        this.precio = precio;
        this.zona = zona;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }


}
