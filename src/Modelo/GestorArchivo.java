package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorArchivo {
    /**
     * Metodo que lee un archivo de texto CSV y pasa los datos de este al directorio de instrumentos
     *
     * @throws IOException Excepcion que ocurre en caso de que no se pueda cargar el archivo
     */
    public ArrayList<String[]> leerArchivo() throws IOException {
        ArrayList<String[]> arregloCSV = new ArrayList<>();
        // Cargamos el archivo y lo metemos al arrayList

        try (
                BufferedReader lector = new BufferedReader(new FileReader("src/directorio.csv"))
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    try {
                        String[] celdas = linea.split(",");
                        arregloCSV.add(celdas);
                    } catch (Exception e) {
                        System.out.println("Error en la linea: " + e.getMessage());
                    }
                }
            }
        }

        return arregloCSV;

    }

    /**
     * Metodo que guarda en un archivo .csv los instrumentos existentes
     *
     * @param directorio ArrayList de los instrumentos que se van a guardar
     * @throws IOException Excepcion que ocurre en el caso de que no se pueda escribir
     */
    public void guardarArchivo(ArrayList<Instrumento> directorio) throws IOException {
        try (
                BufferedWriter escritor = new BufferedWriter(new FileWriter("src/directorio.csv"));
        ) {
            // Escribimos el encabezado primero
            escritor.write("Nombre,Autor,Forma,Condicion,Validez,Fecha,Clave");
            escritor.newLine();

            // Iteramos sobre el ArrayList
            for (Instrumento v : directorio) {
                escritor.write(v.getNombre() + "," + v.getAutor() + "," +
                        v.getTipoInstrumento() + "," + v.getCondicion() + "," + v.isValidezConfiabilidad()
                        + "," + v.getFecha() + "," + v.getClave());
                escritor.newLine();
            }
        }
    }
}

