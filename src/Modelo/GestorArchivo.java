package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorArchivo {
    // Todo: Crear un gestor de archivos

    /**
     * Metodo que lee un archivo de texto CSV y pasa los datos de este al directorio de instrumentos
     *
     * @throws IOException Excepcion que ocurre en caso de que no se pueda cargar el archivo
     */
    public ArrayList<String[]> leerArchivo() throws IOException {
        ArrayList<String[]> arregloCSV = new ArrayList<>();
        // Cargamos el archivo y lo metemos al arrayList

        try (
                BufferedReader lector = new BufferedReader(new FileReader("src/directorio.txt"))
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
        /*
            // Una vez hecho esto deberia meter al hashmap el contenido del archivo
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
         */

    }

    /**
     * Metodo que guarda en un archivo .txt los instrumentos existentes en el directorio de instrumentos
     * @throws IOException Excepcion que ocurre en el caso de que no se pueda escribir la informacion al archivo
     */
    /**
     * Metodo que guarda en un archivo .txt los instrumentos existentes
     *
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

