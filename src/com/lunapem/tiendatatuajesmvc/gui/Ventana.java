package com.lunapem.tiendatatuajesmvc.gui;

import com.lunapem.tiendatatuajesmvc.base.Servicios;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton tatuajesRadioButton;
    private JRadioButton piercingsRadioButton;
    private JTextField NombreTxt;
    private JButton nuevoButton;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JTextField textField3;
    private JLabel tamannoMaterialLbl;
    private JLabel estiloTipoJoyaLbl;
    private JLabel colorTintaJoyaLbl;
    private JLabel sesionesCicatrizacionLbl;
    private JButton importarButton;
    private JButton exportarButton;

    //creados por mi
    public JFrame frame;
    //pintar en un elemento Default(elemento)Model
    public DefaultListModel<Servicios> dlmServicios;

    public Ventana() {
        frame = new JFrame("Servicios");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmServicios= new DefaultListModel<Servicios>();
        list1.setModel(dlmServicios);
    }


}
