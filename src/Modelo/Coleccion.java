package Modelo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Coleccion {
    // Maneja un array list de instrumentos como pide la rúbrica
    private ArrayList<Instrumento> listaInstrumentos;

    public Coleccion() {
        listaInstrumentos = new ArrayList<>();
    }

    // 1) Se registran instrumentos
    public void registrarInstrumento(Instrumento instrumento) {
        listaInstrumentos.add(instrumento);
    }

    public void eliminarInstrumento(int clave) {
        // Lambda para eliminar el que coincida con la clave
        listaInstrumentos.removeIf(ins -> ins.getClave() == clave);
    }

    public ArrayList<Instrumento> obtenerTodos() {
        return listaInstrumentos;
    }

    // 2) Se hacen las consultas con filtros y lambdas

    public ArrayList<Instrumento> consultarPorAutor(String autor) {
        return listaInstrumentos.stream()
                .filter(ins -> ins.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> consultarPorCondicionYValidez(int condicion, boolean validez) {
        return listaInstrumentos.stream()
                .filter(ins -> ins.getCondicion() == condicion && ins.isValidezConfiabilidad() == validez)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> consultarPorTipo(String tipo) {
        return listaInstrumentos.stream()
                .filter(ins -> ins.getTipoInstrumento().equalsIgnoreCase(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> consultarPorCondicion(int condicionBusqueda) {
        return listaInstrumentos.stream()
                .filter(ins -> ins.getCondicion() == condicionBusqueda)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> consultarPorValidez(boolean validezBusqueda) {
        return listaInstrumentos.stream()
                .filter(ins -> ins.isValidezConfiabilidad() == validezBusqueda)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}