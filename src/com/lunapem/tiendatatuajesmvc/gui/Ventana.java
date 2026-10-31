package com.lunapem.tiendatatuajesmvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.lunapem.tiendatatuajesmvc.base.Servicio;

import javax.swing.*;

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
    public JButton importarButton;
    public JButton exportarButton;
    public JTextField tamannoMaterialTxt;
    public JTextField estiloTipoJoyaTxt;
    public JTextField colorTintaTxt;
    public DatePicker fechaCitaDPicker;
    public JTextField sesionesCicatricacionTxt;
    public JTextArea descripcionAlergiasTxt;
    public JLabel descripcionAlergiasLbl;

    //creados por mi
    public JFrame frame;
    //pintar en un elemento Default(elemento)Model
    public DefaultListModel<Servicio> dlmServicios;

    public Ventana() {
        frame = new JFrame("Servicios");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmServicios= new DefaultListModel<Servicio>();
        list1.setModel(dlmServicios);
    }


}
