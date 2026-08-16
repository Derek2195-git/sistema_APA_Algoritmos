package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

// Esta clase se va a reestructurar totalmente para estar más acorde a lo que se pidió en clase
public class Instrumento {
    private String nombre;
    private String autor;
    private String tipoInstrumento;
    private int condicion;
    private boolean validezConfiabilidad;
    private String fecha;
    public Instrumento() {
        nombre = "prueba";
        autor = "prueba";
        tipoInstrumento = "prueba";
        condicion = 3;
        validezConfiabilidad = true;
        fecha = "fecha de prueba";
    }

    @Override
    public String toString() {
        return "Nombre del instrumento: " + nombre +
                "Autor del instrumento: " + autor +
                "Tipo del instrumento: " + tipoInstrumento +
                "¿Es usado para el estrés o la ansiedad? Usado para " + convertirCondicion() +
                "Validez de confiabilidad: " + validezConfiabilidad +
                "Fecha de la cita: " + fecha;
    }

    public Instrumento(String nombre, String autor, String tipoInstrumento,
                       int condicion, boolean validezConfiabilidad, String fecha) {
        this.nombre = nombre;
        this.autor = autor;
        this.tipoInstrumento = tipoInstrumento;
        this.condicion = condicion;
        this.validezConfiabilidad = validezConfiabilidad;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(String tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }

    public int getCondicion() {
        return condicion;
    }

    public String convertirCondicion() {
        // Como ocupamos saber si este atributo es para el estrés, la ansiedad o ambos,
        // vamos a hacer esta funcion para cuando la consultemos
        if (condicion == 1) {
            return "el estrés";
        } else if (condicion == 2) {
            return "la ansiedad";
        } else {
            return "Ambos";
        }
    }

    public void setCondicion(int condicion) {

        this.condicion = condicion;
    }

    public boolean isValidezConfiabilidad() {
        return validezConfiabilidad;
    }

    public void setValidezConfiabilidad(boolean validezConfiabilidad) {
        this.validezConfiabilidad = validezConfiabilidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
