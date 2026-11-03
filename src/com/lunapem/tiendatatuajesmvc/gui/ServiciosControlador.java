package com.lunapem.tiendatatuajesmvc.gui;

import com.lunapem.tiendatatuajesmvc.base.Piercing;
import com.lunapem.tiendatatuajesmvc.base.Servicio;
import com.lunapem.tiendatatuajesmvc.base.Tatuaje;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
    private String hayCamposVacios() {
        String campos = "";
        if (ventana.nombreTxt.getText().isEmpty()){
            campos += "- Nombre\n";
        }
        if (ventana.telefonoClienteTxt.getText().isEmpty()) {
            campos += "- Teléfono\n";
        }
        if (ventana.fechaCitaDPicker.getText().isEmpty()) {
            campos += "- Fecha cita\n";
        }
        if (ventana.precioTxt.getText().isEmpty()) {
            campos += "- Precio\n";
        }
        if (ventana.zonaTxt.getText().isEmpty()) {
            campos += "- Zona\n";
        }
        if (ventana.piercingsRadioButton.isSelected()) {
            if(ventana.tamannoMaterialCmb.getSelectedIndex()<=-1){
                campos += "- Material\n";
            }
            if (ventana.estiloTipoJoyaTxt.getText().isEmpty()) {
                campos += "- Tipo joya\n";
            }
            if (ventana.descripcionAlergiasTxt.getText().isEmpty()) {
                campos += "- Alergias\n";
            }
            //if(ventana.sesionesCicatricacionSpn.getValue()){

            //}
        }
        if (ventana.tatuajesRadioButton.isSelected()) {
            if(ventana.tamannoMaterialCmb.getSelectedIndex()<=-1){
                campos += "- Tamaño\n";
            }
            if (ventana.estiloTipoJoyaTxt.getText().isEmpty()) {
                campos += "- Estilo\n";
            }
            if (ventana.descripcionAlergiasTxt.getText().isEmpty()) {
                campos += "- Descripción\n";
            }
            if (ventana.colorTintaTxt.getText().isEmpty()) {
                campos += "- Color tinta\n";
            }
        }
        return campos;
    }

    private void limpiarCampos(){
        ventana.nombreTxt.setText(null);
        ventana.telefonoClienteTxt.setText(null);
        ventana.precioTxt.setText(null);
        ventana.zonaTxt.setText(null);
        ventana.fechaCitaDPicker.setText(null);
        ventana.tamannoMaterialCmb.setSelectedItem(null);
        ventana.colorTintaTxt.setText(null);
        ventana.estiloTipoJoyaTxt.setText(null);
        ventana.descripcionAlergiasTxt.setText(null);
        ventana.sesionesCicatricacionSpn.setValue(0);
        ventana.nombreTxt.requestFocus();
    }

    public void refrescar(){
        ventana.dlmServicios.clear();
        for(Servicio unServicio: modelo.obtenerServicios()){
            ventana.dlmServicios.addElement(unServicio);
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
        ventana.editarButton.addActionListener(listener);
        ventana.eliminarButton.addActionListener(listener);
        ventana.buscarButton.addActionListener(listener);
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
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (!hayCamposVacios().isEmpty()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios: \n" + hayCamposVacios());
                    break;
                }
                if (modelo.existeCliente(ventana.nombreTxt.getText())) {
                    Util.mensajeError("Ya existe un cliente con ese nombre");
                    break;
                }
                if (ventana.piercingsRadioButton.isSelected()) {
                    modelo.altaPiercings(ventana.nombreTxt.getText(), ventana.telefonoClienteTxt.getText(), ventana.fechaCitaDPicker.getDate(),
                            Float.parseFloat(ventana.precioTxt.getText()), ventana.zonaTxt.getText(), ventana.tamannoMaterialCmb.getSelectedItem().toString(),
                            ventana.estiloTipoJoyaTxt.getText(),ventana.descripcionAlergiasTxt.getText(),
                            (Double) ventana.sesionesCicatricacionSpn.getValue());
                } else {
                    modelo.altaTatuaje(ventana.nombreTxt.getText(), ventana.telefonoClienteTxt.getText(), ventana.fechaCitaDPicker.getDate(),
                            Float.parseFloat(ventana.precioTxt.getText()), ventana.zonaTxt.getText(), ventana.tamannoMaterialCmb.getSelectedItem().toString(),
                            ventana.colorTintaTxt.getText(), ventana.estiloTipoJoyaTxt.getText(),ventana.descripcionAlergiasTxt.getText(),
                            (Integer) ventana.sesionesCicatricacionSpn.getValue());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Editar":
                int posicionServicioModificar = ventana.list1.getSelectedIndex();

                if(posicionServicioModificar > 0){
                    Util.mensajeError("Selecciona un servicio");
                    break;
                }
                Servicio modificar;
                if(ventana.piercingsRadioButton.isSelected()){
                    modificar = new Piercing(ventana.nombreTxt.getText(), ventana.telefonoClienteTxt.getText(),
                            ventana.fechaCitaDPicker.getDate(), Float.parseFloat(ventana.precioTxt.getText()),
                            ventana.zonaTxt.getText(), ventana.tamannoMaterialCmb.getSelectedItem().toString(),
                            ventana.estiloTipoJoyaTxt.getText(), ventana.descripcionAlergiasTxt.getText(),
                            (Double) ventana.sesionesCicatricacionSpn.getValue());
                }else{
                    modificar = new Tatuaje(ventana.nombreTxt.getText(), ventana.telefonoClienteTxt.getText(),
                            ventana.fechaCitaDPicker.getDate(), Float.parseFloat(ventana.precioTxt.getText()),
                            ventana.zonaTxt.getText(), ventana.tamannoMaterialCmb.getSelectedItem().toString(),
                            ventana.colorTintaTxt.getText(), ventana.estiloTipoJoyaTxt.getText(),
                            ventana.descripcionAlergiasTxt.getText(), (Integer) ventana.sesionesCicatricacionSpn.getValue());
                }
                modelo.editarServicio(posicionServicioModificar, modificar);
                refrescar();
                break;
            case "Eliminar":
                Servicio servicioEliminar = (Servicio) ventana.list1.getSelectedValue();

                if(servicioEliminar == null){
                    Util.mensajeError("Elige un servicio");
                    break;
                }
                modelo.eliminarServicio(servicioEliminar);
                limpiarCampos();
                refrescar();
                break;
            case "Buscar":
                String nombreClienteBuscar = ventana.buscarTxt.getText();
                boolean encontrado = false;
                for (Servicio unServicio: modelo.obtenerServicios()){
                    if(unServicio.getNombreCliente().equalsIgnoreCase(nombreClienteBuscar)){
                        ventana.list1.setSelectedValue(unServicio,true);
                        encontrado = true;
                        break;
                    }
                }
                if(!encontrado){
                    Util.mensajeError("Cliente no encontrado");
                    break;
                }
                break;
            case "Piercings":
                ventana.tamannoMaterialLbl.setText("Material");
                ventana.estiloTipoJoyaLbl.setText("Tipo Joya");
                ventana.descripcionAlergiasLbl.setText("Alergias");
                ventana.sesionesCicatrizacionLbl.setText("Tiempo cicatrizacion");
                ventana.colorTintaLbl.setVisible(false);
                ventana.colorTintaTxt.setVisible(false);
                ventana.semanasTextField.setVisible(true);
                ventana.tamannoMaterialCmb.setModel(
                        new DefaultComboBoxModel<>(
                                new String[]{"Titanio", "Acero Quirúrgico", "Oro", "Plata"
                                }
                        )
                );
                ventana.sesionesCicatricacionSpn.setModel(new SpinnerNumberModel());
                break;
            case "Tatuajes":
                ventana.tamannoMaterialLbl.setText("Tamaño");
                ventana.estiloTipoJoyaLbl.setText("Estilo");
                ventana.descripcionAlergiasLbl.setText("Descripcion tatuaje");
                ventana.sesionesCicatrizacionLbl.setText("Sesiones");
                ventana.semanasTextField.setVisible(false);
                ventana.colorTintaLbl.setVisible(true);
                ventana.colorTintaTxt.setVisible(true);
                ventana.tamannoMaterialCmb.setModel(
                        new DefaultComboBoxModel<>(
                                new String[]{"Pequeño", "Mediano", "Grande"
                                }
                        )
                );
                ventana.sesionesCicatricacionSpn.setModel(new SpinnerNumberModel());
                break;
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("Deseas cerrar la ventana", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Servicio servicioSeleccionado = (Servicio) ventana.list1.getSelectedValue();
            ventana.nombreTxt.setText(servicioSeleccionado.getNombreCliente());
            ventana.telefonoClienteTxt.setText(servicioSeleccionado.getTelefonoCliente());
            ventana.fechaCitaDPicker.setDate(servicioSeleccionado.getFechaCita());
            ventana.precioTxt.setText(String.valueOf(servicioSeleccionado.getPrecio()));
            ventana.zonaTxt.setText(servicioSeleccionado.getZona());

            if (servicioSeleccionado instanceof Piercing) {
                ventana.piercingsRadioButton.doClick();
                ventana.tamannoMaterialCmb.setSelectedItem(((Piercing) servicioSeleccionado).getMaterial());
                ventana.estiloTipoJoyaTxt.setText(((Piercing) servicioSeleccionado).getTipoJoya());
                ventana.descripcionAlergiasTxt.setText(((Piercing) servicioSeleccionado).getAlergias());
                ventana.sesionesCicatricacionSpn.setValue(String.valueOf(((Piercing) servicioSeleccionado).getTiempoCicatrizacion()));
                ventana.colorTintaTxt.setVisible(false);
            } else {
                ventana.tatuajesRadioButton.doClick();
                ventana.tamannoMaterialCmb.setSelectedItem(((Tatuaje) servicioSeleccionado).getTamano());
                ventana.estiloTipoJoyaTxt.setText(((Tatuaje) servicioSeleccionado).getEstilo());
                ventana.descripcionAlergiasTxt.setText(((Tatuaje) servicioSeleccionado).getDescripcion());
                ventana.sesionesCicatricacionSpn.setValue(String.valueOf(((Tatuaje) servicioSeleccionado).getNumeroSesiones()));
                ventana.colorTintaTxt.setVisible(true);
                ventana.colorTintaTxt.setText(((Tatuaje) servicioSeleccionado).getColorTinta());
            }

        }
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
