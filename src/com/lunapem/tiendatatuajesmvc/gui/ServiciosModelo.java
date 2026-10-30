package com.lunapem.tiendatatuajesmvc.gui;

import com.lunapem.tiendatatuajesmvc.base.Piercing;
import com.lunapem.tiendatatuajesmvc.base.Servicio;
import com.lunapem.tiendatatuajesmvc.base.Tatuaje;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServiciosModelo {
    private ArrayList<Servicio> listaServicios;
    public ServiciosModelo() {
        listaServicios= new ArrayList<>();
    }
    public ArrayList<Servicio> obtenerServicios() {
        return listaServicios;
    }
    public void altaPiercings(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona,
                              String material, String tipoJoya, double tiempoCicatrizacion) {
        Piercing nuevoPiercing = new Piercing(nombreCliente, telefonoCliente,fechaCita, precio, zona,
                material,tipoJoya,tiempoCicatrizacion);
        listaServicios.add(nuevoPiercing);
    }
    public void altaTatuaje(String nombreCliente, String telefonoCliente, LocalDate fechaCita, float precio, String zona,
                           String tamano,String colorTinta,String estilo,int numeroSesiones){
        Tatuaje nuevoTatuaje = new Tatuaje(nombreCliente, telefonoCliente,fechaCita, precio, zona,
                tamano, colorTinta,estilo,numeroSesiones);
        listaServicios.add(nuevoTatuaje);
    }
    public boolean existeCliente(String nombreCliente){
        for(Servicio unServicio : listaServicios) {
            if(unServicio.getNombreCliente().equals(nombreCliente))
            return true;
        }
        return false;
    }
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento =dom.createDocument(null,"xml",null);

        //añado el nodo raiz
        Element raiz = documento.createElement("servicios");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoServicio= null;
        Element nodoDatos = null;
        Text texto= null;

        for(Servicio unServicio: listaServicios){
            if(unServicio instanceof Piercing){
                nodoServicio = documento.createElement("Piercing");
            }else{
                nodoServicio = documento.createElement("Tatuaje");
            }
            raiz.appendChild(nodoServicio);

            nodoDatos = documento.createElement("NombreCliente");
            nodoServicio.appendChild(nodoDatos);
            texto = documento.createTextNode(unServicio.getNombreCliente());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("TelefonoCliente");
            nodoServicio.appendChild(nodoDatos);
            texto = documento.createTextNode(unServicio.getTelefonoCliente());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("FechaCita");
            nodoServicio.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unServicio.getFechaCita()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("Precio");
            nodoServicio.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unServicio.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("Zona");
            nodoServicio.appendChild(nodoDatos);
            texto = documento.createTextNode(unServicio.getZona());
            nodoDatos.appendChild(texto);

            if(unServicio instanceof Piercing){
                nodoDatos = documento.createElement("MaterialPiercing");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Piercing) unServicio).getMaterial()));
                nodoDatos.appendChild(texto);
            }else{
                nodoDatos = documento.createElement("TamañoTatuaje");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Tatuaje) unServicio).getTamano()));
                nodoDatos.appendChild(texto);
            }

            if(unServicio instanceof Piercing){
                nodoDatos = documento.createElement("TipoJoya");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Piercing) unServicio).getTipoJoya()));
                nodoDatos.appendChild(texto);
            }else{
                nodoDatos = documento.createElement("EstiloTatuaje");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Tatuaje) unServicio).getEstilo()));
                nodoDatos.appendChild(texto);
            }

            if(unServicio instanceof Piercing){
                nodoDatos = documento.createElement("TiempoCicatrizacion");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Piercing) unServicio).getTiempoCicatrizacion()));
                nodoDatos.appendChild(texto);
            }else{
                nodoDatos = documento.createElement("NumeroSesiones");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Tatuaje) unServicio).getNumeroSesiones()));
                nodoDatos.appendChild(texto);
            }

            if(unServicio instanceof Tatuaje){
                nodoDatos = documento.createElement("ColorTintaCliente");
                nodoServicio.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Tatuaje) unServicio).getColorTinta()));
                nodoDatos.appendChild(texto);
            }else{

            }

        }

        //guardar el  fichero
        Source source= new DOMSource(documento);
        Result resultado= new StreamResult(fichero);
        Transformer transformer= TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }
    public void  importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaServicios = new ArrayList<Servicio>();
        Piercing nuevoPiercing= null;
        Tatuaje nuevoTatuaje= null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElemento = documento.getElementsByTagName("*");
        for (int i = 0; i < listaElemento.getLength(); i++) {
            Element nodoServicio = (Element) listaElemento.item(i);
            if (nodoServicio.getTagName().equals("Piercing")) {
                nuevoPiercing = new Piercing();
                nuevoPiercing.setNombreCliente(nodoServicio.getChildNodes().item(0).getTextContent());
                nuevoPiercing.setTelefonoCliente(nodoServicio.getChildNodes().item(1).getTextContent());
                nuevoPiercing.setFechaCita(LocalDate.parse(nodoServicio.getChildNodes().item(2).getTextContent()));
                nuevoPiercing.setPrecio(Float.parseFloat(nodoServicio.getChildNodes().item(3).getTextContent()));
                nuevoPiercing.setMaterial(nodoServicio.getChildNodes().item(4).getTextContent());
                nuevoPiercing.setTipoJoya(nodoServicio.getChildNodes().item(5).getTextContent());
                nuevoPiercing.setTiempoCicatrizacion(Double.parseDouble(nodoServicio.getChildNodes().item(6).getTextContent()));
                listaServicios.add(nuevoPiercing);
            } else {
                if (nodoServicio.getTagName().equals("Tatuaje")) {
                    nuevoTatuaje = new Tatuaje();
                    nuevoTatuaje.setNombreCliente(nodoServicio.getChildNodes().item(0).getTextContent());
                    nuevoTatuaje.setTelefonoCliente(nodoServicio.getChildNodes().item(1).getTextContent());
                    nuevoTatuaje.setFechaCita(LocalDate.parse(nodoServicio.getChildNodes().item(2).getTextContent()));
                    nuevoTatuaje.setPrecio(Float.parseFloat(nodoServicio.getChildNodes().item(3).getTextContent()));
                    nuevoTatuaje.setTamano(nodoServicio.getChildNodes().item(4).getTextContent());
                    nuevoTatuaje.setColorTinta(nodoServicio.getChildNodes().item(5).getTextContent());
                    nuevoTatuaje.setEstilo(nodoServicio.getChildNodes().item(6).getTextContent());
                    nuevoTatuaje.setNumeroSesiones(Integer.parseInt(nodoServicio.getChildNodes().item(7).getTextContent()));
                    listaServicios.add(nuevoTatuaje);
                }
            }
        }

    }
}
