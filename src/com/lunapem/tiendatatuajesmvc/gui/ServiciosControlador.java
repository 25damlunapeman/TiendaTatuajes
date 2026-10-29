package com.lunapem.tiendatatuajesmvc.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.*;
import java.util.Properties;

public class ServiciosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana ventana;
    private ServiciosModelo modelo;
    private File ultimaRutaExportada;

    public ServiciosControlador(Ventana ventana, ServiciosModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No se encontro el archivo "+ e.getMessage());
        }

        addActionListener(this);
        addListSelectionListener(this);
        addWindowsListener(this);

    }
    private boolean hayCamposVacios(){
        if(ventana.NombreTxt.getText().isEmpty() ||
                ventana.telefonoClienteTxt.getText().isEmpty() ||
                ventana.precioTxt.getText().isEmpty() ||
                ventana.zonaTxt.getText().isEmpty() ){

        }
    }

    private void addWindowsListener(WindowListener listener) {
        ventana.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        ventana.list1.addListSelectionListener(listener);
    }

    private void addActionListener(ActionListener listener) {
        ventana.nuevoButton.addActionListener(listener);
        ventana.importarButton.addActionListener(listener);
        ventana.exportarButton.addActionListener(listener);
        ventana.piercingsRadioButton.addActionListener(listener);
        ventana.tatuajesRadioButton.addActionListener(listener);

    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("servicios.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }
    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("servicios.conf"), "Datos configuracion servicios");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }



    //no se usan
    @Override
    public void windowOpened(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

}
