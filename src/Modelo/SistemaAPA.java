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
        clave = 1;

        directorio.put(clave, instrumento);
    }

}
