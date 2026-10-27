package com.lunapem.tiendatatuajesmvc.base;

import java.time.LocalDate;

public class Piercings extends Servicios{
    private String material;
    private String tipoJoya;
    private int tiempoCicatrizacion;
    private String colorJoya;

    public Piercings(){
        super();
    }

    public Piercings(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona, String material, String tipoJoya, int tiempoCicatrizacion, String colorJoya) {
        super(nombreCliente, telefonoCliente, fechaCita, precio, zona);
        this.material = material;
        this.tipoJoya = tipoJoya;
        this.tiempoCicatrizacion = tiempoCicatrizacion;
        this.colorJoya = colorJoya;
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

    public int getTiempoCicatrizacion() {
        return tiempoCicatrizacion;
    }

    public void setTiempoCicatrizacion(int tiempoCicatrizacion) {
        this.tiempoCicatrizacion = tiempoCicatrizacion;
    }

    public String getColorJoya() {
        return colorJoya;
    }

    public void setColorJoya(String colorJoya) {
        this.colorJoya = colorJoya;
    }

    @Override
    public String toString() {
        return "Piercing:" + "nombreCliente='" + getNombreCliente() + '\'' + ", telefonoCliente='" + getTelefonoCliente() + '\'' +
                ", fechaCita=" + getFechaCita() + ", precio=" + getPrecio() + ", zona='" + getZona();
    }
}
