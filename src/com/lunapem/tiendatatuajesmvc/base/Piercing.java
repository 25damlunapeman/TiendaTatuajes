package com.lunapem.tiendatatuajesmvc.base;

import java.time.LocalDate;

public class Piercing extends Servicio {
    private String material;
    private String tipoJoya;
    private double tiempoCicatrizacion;

    public Piercing(){
        super();
    }

    public Piercing(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona, String material, String tipoJoya, double tiempoCicatrizacion) {
        super(nombreCliente, telefonoCliente, fechaCita, precio, zona);
        this.material = material;
        this.tipoJoya = tipoJoya;
        this.tiempoCicatrizacion = tiempoCicatrizacion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTipoJoya() {
        return tipoJoya;
    }

    public void setTipoJoya(String tipoJoya) {
        this.tipoJoya = tipoJoya;
    }

    public double getTiempoCicatrizacion() {
        return tiempoCicatrizacion;
    }

    public void setTiempoCicatrizacion(double tiempoCicatrizacion) {
        this.tiempoCicatrizacion = tiempoCicatrizacion;
    }

    @Override
    public String toString() {
        return "Piercing:" + "nombreCliente='" + getNombreCliente() + '\'' + ", telefonoCliente='" + getTelefonoCliente() + '\'' +
                ", fechaCita=" + getFechaCita() + ", precio=" + getPrecio() + ", zona='" + getZona();
    }
}
