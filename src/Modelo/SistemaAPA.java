package Modelo;

import java.util.HashMap;

public class SistemaAPA {
    private Instrumento instrumento;
    private HashMap<Integer, Instrumento> directorio;
    private int clave;

    public SistemaAPA() {
        directorio = new HashMap<>();
    }

    public void registrarInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                     boolean validez, String fecha) {
        instrumento = new Instrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha);
        clave = generarClave();

        directorio.put(clave, instrumento);
        System.out.println("Exito! Se creo el instrumento con los siguientes datos: \n" + instrumento);
        System.out.println("Quitar esto despues de hacer las consultas: La clave del instrumento es: " + clave);
    }

    public int generarClave() {
        // La idea es que sea una clave del 1 al 9999, lo más facil que encuentro es tomar el tamaño del hashmap y sumarle uno
        return directorio.size() + 1;
    }

    public void consultarInstrumento(int clave) {
        // Version muy preliminar de la consulta solo para saber si el instrumento se esta metiendo al hashmap
        System.out.println(directorio.get(clave));
    }

}
