package com.lunapem.tiendatatuajesmvc;

import com.lunapem.tiendatatuajesmvc.gui.ServiciosControlador;
import com.lunapem.tiendatatuajesmvc.gui.ServiciosModelo;
import com.lunapem.tiendatatuajesmvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana ventana= new Ventana();
        ServiciosModelo modelo= new ServiciosModelo();
        ServiciosControlador controlador=new ServiciosControlador(ventana,modelo);
    }
}
