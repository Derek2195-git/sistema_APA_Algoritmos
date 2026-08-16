package Modelo;

import java.util.HashMap;

public class SistemaAPA {
    Instrumento instrumento;
    HashMap<Integer, Instrumento> directorio;

    public SistemaAPA() {
        directorio = new HashMap<>();
        registrarInstrumento();
    }

    private void registrarInstrumento() {

    }

}
