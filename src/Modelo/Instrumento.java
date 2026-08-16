package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

// Esta clase se va a reestructurar totalmente para estar más acorde a lo que se pidió en clase
public class Instrumento {
    private String formaInstrumento;
    private HashMap<String, ArrayList<String>> directorioAutores;
    private String condicion;
    private int validez;
    public Instrumento() {
        // Autor y los instrumentos que usa el autor
        directorioAutores = new HashMap<>();
        formaInstrumento = "";
        condicion = "";
        validez = 100;
        // Falta algo de cita

    }

    public String getFormaInstrumento() {
        return formaInstrumento;
    }

    public void setFormaInstrumento(String formaInstrumento) {
        this.formaInstrumento = formaInstrumento;
    }

    public HashMap getDirectorioAutores() {
        return directorioAutores;
    }

    public void setDirectorioAutores(HashMap<String, ArrayList<String>> directorioAutores) {
        this.directorioAutores = directorioAutores;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }



}
