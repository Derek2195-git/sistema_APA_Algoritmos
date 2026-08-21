package Modelo;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;

public class SistemaAPA {
    private Coleccion coleccion; // ¡Aquí usamos nuestra nueva clase!
    GestorArchivo gestor;

    /**
     * Constructor del sistema
     */
    public SistemaAPA() {
        coleccion = new Coleccion();
        gestor = new GestorArchivo();
    }

    /**
     * Metodo que crea el instrumento y lo registra
     */
    public void registrarInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                     boolean validez, String fecha) {
        int clave = generarClave();
        registrarInstrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha, clave);
    }

    /**
     * Metodo sobrecargado de registrarInstrumento el cual permite una clave personalizada
     */
    public void registrarInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                     boolean validez, String fecha, int clave) {
        Instrumento instrumento = new Instrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha);
        instrumento.setClave(clave);

        coleccion.registrarInstrumento(instrumento); // Delegamos a la clase colección

        System.out.println("Exito! Se creo el instrumento con los siguientes datos: \n" + instrumento);
        try {
            gestor.guardarArchivo(coleccion.obtenerTodos());
        } catch (IOException e) {
            System.out.println("Error: El sistema no pudo guardar en un archivo de texto el instrumento");
        }
    }

    /**
     * Metodo que genera una clave usando lambdas
     */
    public int generarClave() {
        return coleccion.obtenerTodos().stream()
                .mapToInt(Instrumento::getClave)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Se encarga de eliminar el instrumento deseado.
     */
    public void eliminarInstrumento(int claveARemover) {
        boolean existe = coleccion.obtenerTodos().stream().anyMatch(ins -> ins.getClave() == claveARemover);

        if (existe) {
            coleccion.eliminarInstrumento(claveARemover);
            System.out.println("\nInstrumento eliminado.");
            try {
                gestor.guardarArchivo(coleccion.obtenerTodos());
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Este instrumento ya fue eliminado o no existe.");
        }
    }

    // --- MÉTODOS DE CONSULTA (Delegados a la Colección) ---
    public ArrayList<Instrumento> mostrarTodos() {
        return coleccion.obtenerTodos();
    }

    public ArrayList<Instrumento> consultarPorAutor(String autor) {
        return coleccion.consultarPorAutor(autor);
    }

    public ArrayList<Instrumento> consultarPorCondicionYValidez(int condicion, boolean validez) {
        return coleccion.consultarPorCondicionYValidez(condicion, validez);
    }

    public ArrayList<Instrumento> consultarPorTipo(String tipoBusqueda) {
        return coleccion.consultarPorTipo(tipoBusqueda);
    }

    public ArrayList<Instrumento> consultarPorCondicion(int condicionBusqueda) {
        return coleccion.consultarPorCondicion(condicionBusqueda);
    }

    public ArrayList<Instrumento> consultarPorValidez(boolean validezBusqueda) {
        return coleccion.consultarPorValidez(validezBusqueda);
    }

    public void cargarCSVDirectorio(ArrayList<String[]> arregloCSV) {
        arregloCSV.forEach(linea -> {
            String nombre = linea[0];
            String autor = linea[1];
            String tipo = linea[2];
            int condicion = Integer.parseInt(linea[3]);
            boolean validez = Boolean.parseBoolean(linea[4]);
            String fecha = linea[5];
            int clave = Integer.parseInt(linea[6]);
            registrarInstrumento(nombre, autor, tipo, condicion, validez, fecha, clave);
        });
    }

    public Coleccion getColeccion() {
        return coleccion;
    }
}