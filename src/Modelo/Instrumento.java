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

    /**
     * Constructor vacio del instrumento
     */
    public Instrumento() {
        nombre = "prueba";
        autor = "prueba";
        tipoInstrumento = "prueba";
        condicion = 3;
        validezConfiabilidad = true;
        fecha = "fecha de prueba";
    }

    /**
     * Constructor del instrumento usado en la otra clase del modelo
     * @param nombre Nombre del instrumento
     * @param autor Autor del instrumento
     * @param tipoInstrumento Tipo del instrumento
     * @param condicion Condicion del instrumento (Si es usado para el estrés, ansiedad o ambos)
     * @param validezConfiabilidad Validez y confiabilidad del instrumento
     * @param fecha Fecha en la que se realizo la evaluación de validez y confiabilidad
     */
    public Instrumento(String nombre, String autor, String tipoInstrumento,
                       int condicion, boolean validezConfiabilidad, String fecha) {
        this.nombre = nombre;
        this.autor = autor;
        this.tipoInstrumento = tipoInstrumento;
        this.condicion = condicion;
        this.validezConfiabilidad = validezConfiabilidad;
        this.fecha = fecha;
    }

    /**
     * Getter del nombre del instrumento
     * @return Nombre del instrumento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *  Setter del nombre del instrumento
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del autor del instrumento
     * @return Autor del instrumento
     */
    public String getAutor() {
        return autor;
    }

    /**
     *  Setter del autor del instrumento
     * @param autor Nuevo autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Getter del tipo de instrumento
     * @return Tipo del instrumento
     */
    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    /**
     *  Setter del tipo del instrumento
     * @param tipoInstrumento Nuevo tipo del instrumento
     */
    public void setTipoInstrumento(String tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }

    /**
     * Getter del numero correspondiente a la condicion del instrumento
     * @return Condicion del instrumento
     */
    public int getCondicion() {
        return condicion;
    }

    /**
     *  Metodo que convierte el entero que tiene la condicion a una cadena la cual
     *  indica lo que esta tratando actualmente
     * @return Cadena la cual indica cual es el uso del instrumento
     */
    public String convertirCondicion() {
        if (condicion == 1) {
            return "el estrés";
        } else if (condicion == 2) {
            return "la ansiedad";
        } else {
            return "Ambos";
        }
    }

    /**
     *  Setter de la condicion del instrumento
     * @param condicion Nuevo entero correspondiente a la condición
     */
    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }

    /**
     * Getter del booleano de la evaluación de validez y confiabilidad
     * @return Booleano que indica la validez y confiabilidad del instrumento
     */
    public boolean isValidezConfiabilidad() {
        return validezConfiabilidad;
    }

    /**
     *  Setter del booleano que indica la validez y confiabilidad
     * @param validezConfiabilidad Nuevo booleano que indica la validez y confiabilidad
     */
    public void setValidezConfiabilidad(boolean validezConfiabilidad) {
        this.validezConfiabilidad = validezConfiabilidad;
    }

    /**
     *  Getter de la fecha en la que se realizo la evaluación de validez y confiabilidad
     * @return Fecha cuando se realizo la evaluación de validez y confiabilidad
     */
    public String getFecha() {
        return fecha;
    }

    /**
     *  Setter de la fecha en la que se realizo la validez y confiabilidad
     * @param fecha Nueva fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo toString del instrumento
     * @return Retorna los datos que tiene el instrumento actualmente
     */
    @Override
    public String toString() {
        return "Nombre del instrumento: " + nombre + "\n" +
                "Autor del instrumento: " + autor + "\n" +
                "Tipo del instrumento: " + tipoInstrumento + "\n" +
                "¿Es usado para el estrés o la ansiedad? Usado para " + convertirCondicion() + "\n" +
                "¿Evaluado como valido y confiabilidad? " + validezConfiabilidad + "\n" +
                "Fecha donde se realizo la evaluación: " + fecha + "\n";
    }
}
