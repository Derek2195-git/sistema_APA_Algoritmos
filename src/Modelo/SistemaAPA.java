package Modelo;

import java.util.HashMap;

public class SistemaAPA {
    private Instrumento instrumento;
    private HashMap<Integer, Instrumento> directorio;
    private int clave;

    /**
     * Constructor del sistema
     */
    public SistemaAPA() {
        directorio = new HashMap<>();
    }

    /**
     * Metodo que crea el instrumento y lo registra al hashmap con una clave generada automaticamente
     * @param nombre Nombre del instrumento
     * @param autor Autor del instrumento
     * @param tipoInstrumento Tipo de instrumento
     * @param condicion Condicion del instrumento (Si es para el estrés, la ansiedad o las dos)
     * @param validez Si el instrumento es válido y confiable
     * @param fecha Fecha en la que se realiza la evaluación
     */
    public void registrarInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                     boolean validez, String fecha) {
        instrumento = new Instrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha);
        clave = generarClave();

        directorio.put(clave, instrumento);
        System.out.println("Exito! Se creo el instrumento con los siguientes datos: \n" + instrumento);
        System.out.println("Quitar esto despues de hacer las consultas: La clave del instrumento es: " + clave);
    }

    /**
     * Metodo que genera una clave para el hashmap tomando en cuenta el tamaño de este
     * @return Un número entero que funciona como la clave para diferenciar entre los distintos instrumentos
     */
    public int generarClave() {
        return directorio.size() + 1;
    }

    /**
     * Version preliminar de la consulta la cual se esta
     * empleando mientras se elabora la sección de consultas
     * @param clave Clave única que tiene un instrumento en el hashmap
     */
    public void consultarInstrumento(int clave) {
        if (directorio.get(clave) == null) {
            System.out.println("Error: El instrumento con la clave ingresada no existe");
        } else System.out.println(directorio.get(clave));
    }

    /**
     * Se encarga de eliminar el instrumento deseado.
     * @param claveARemover Clave del instrumento que se quiere eliminar.
     */
    public void eliminarInstrumento(int claveARemover) {
        if (directorio.containsKey(claveARemover)) {
            directorio.remove(claveARemover);
            System.out.println("\nInstrumento eliminado.");
        } else {
            System.out.println("Este instrumento ya fue eliminado o no existe.");
        }

    }

    // 1. Mostrar todos ordenados por clave (del 1 en adelante)
    public void mostrarTodos() {
        if (directorio.isEmpty()) {
            System.out.println("No hay instrumentos registrados.");
            return;
        }
        for (int i = 1; i <= directorio.size(); i++) {
            if (directorio.containsKey(i)) {
                System.out.println("Clave: " + i);
                System.out.println(directorio.get(i));
            }
        }
    }

    // 2. Buscar instrumentos por autor
    public void consultarPorAutor(String autor) {
        boolean encontrado = false;
        for (Integer key : directorio.keySet()) {
            Instrumento ins = directorio.get(key);
            if (ins.getAutor().equalsIgnoreCase(autor)) {
                System.out.println("Clave: " + key);
                System.out.println(ins);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron instrumentos para el autor: " + autor);
        }
    }

    // 3. Buscar por condición y validez
    public void consultarPorCondicionYValidez(int condicion, boolean validez) {
        boolean encontrado = false;
        for (Integer key : directorio.keySet()) {
            Instrumento ins = directorio.get(key);
            if (ins.getCondicion() == condicion && ins.isValidezConfiabilidad() == validez) {
                System.out.println("Clave: " + key);
                System.out.println(ins);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron instrumentos con la condición y validez indicadas.");
        }
    }

}
