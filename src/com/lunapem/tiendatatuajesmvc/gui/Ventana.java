package com.lunapem.tiendatatuajesmvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.lunapem.tiendatatuajesmvc.base.Servicio;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Ventana {
    private JPanel panel1;
    public JRadioButton tatuajesRadioButton;
    public JRadioButton piercingsRadioButton;
    public JTextField nombreTxt;
    public JButton nuevoButton;
    public JTextField precioTxt;
    public JTextField zonaTxt;
    public JList list1;
    public JTextField telefonoClienteTxt;
    public JLabel tamannoMaterialLbl;
    public JLabel estiloTipoJoyaLbl;
    public JLabel colorTintaLbl;
    public JLabel sesionesCicatrizacionLbl;
    public JComboBox tamannoMaterialCmb;
    public JTextField estiloTipoJoyaTxt;
    public JTextField colorTintaTxt;
    public DatePicker fechaCitaDPicker;
    public JSpinner sesionesCicatricacionSpn;
    public JTextArea descripcionObservacionesTxt;
    public JLabel descripcionObservacionesLbl;
    public JButton editarButton;
    public JButton eliminarButton;
    public JButton buscarButton;
    public JTextField semanasTextField;
    public JTextField buscarTxt;
    public JMenuItem itemImportar;
    public JMenuItem itemExportar;

    //creados por mi
    public JFrame frame;
    private ServiciosModelo modelo;
    //pintar en un elemento Default(elemento)Model
    public DefaultListModel<Servicio> dlmServicios;

    public Ventana() {
        //margenes
        panel1.setBorder(new EmptyBorder(16, 16, 16, 16));
        //ventana
        frame = new JFrame("Estudio de Tatuajes y Piercings");
        frame.setContentPane(panel1);
        //introducimos menu
        crearMenu();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        //tamaño ventana
        frame.setSize(700, 800);
        //centrar ventana
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        initComponents();

    }

    public void initComponents() {
        dlmServicios = new DefaultListModel<Servicio>();
        list1.setModel(dlmServicios);
    }

    public void crearMenu(){
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemExportar = new JMenuItem("Exportar XML");
        itemImportar = new JMenuItem("Importar XML");

        menu.add(itemExportar);
        menu.add(itemImportar);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }

}
