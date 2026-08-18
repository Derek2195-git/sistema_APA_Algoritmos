package Modelo;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// El contador controla la posición
//

public class SistemaAPA {
    private Instrumento instrumento;
    private HashMap<Integer, Instrumento> directorio;
    private int clave;
    private int contador;
    private Instrumento[] instrumentos;
    Instrumento[] arregloI;
    GestorArchivo gestor;

    /**
     * Constructor del sistema
     */
    public SistemaAPA() {
        directorio = new HashMap<>();
        contador = 0;

    }

    /*
    public void aumentarTamano(int aumento) {
        Instrumento[ temp = new Instrumento[instrumentos.length + aumento];
        for (int i=0; i<instrumento.length; i++) {
            temp[i] = instrumento[i];
        }
        instrumento = temp;
    }

    public int calcularAumento() {
        return (int) (arreglo.length * 1.25);
    }
     */

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
        try {
            gestor.guardarArchivo(directorio);
        } catch (IOException e) {
            System.out.println("Error: El sistema no pudo guardar en un archivo de texto el instrumento");
        }
    }

    public Instrumento crearInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                 boolean validez, String fecha) {
        return new Instrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha);
    }

    public void registrarInstrumento (Instrumento instrumento, int pos) {
        arregloI = new Instrumento[10];
        arregloI[pos-1] = instrumento;
        System.out.println(arregloI[pos-1]);
        aumentarCapacidadArreglo(arregloI);
        contador++;
    }




    /**
     * Metodo sobrecargado de registrarInstrumento el cual permite una clave personalizada
     * @param nombre Nombre del instrumento
     * @param autor Autor del instrumento
     * @param tipoInstrumento Tipo de instrumento
     * @param condicion Condicion del instrumento (Si es para el estrés, la ansiedad o las dos)
     * @param validez Si el instrumento es válido y confiable
     * @param fecha Fecha en la que se realiza la evaluación
     * @param clave Clave que tendra el hashmap para ubicar al instrumento
     */
    public void registrarInstrumento(String nombre, String autor, String tipoInstrumento, int condicion,
                                     boolean validez, String fecha, int clave) {
        instrumento = new Instrumento(nombre, autor, tipoInstrumento, condicion, validez, fecha);
        directorio.put(clave, instrumento);
    }
    /**
     * Metodo que genera una clave para el hashmap tomando en cuenta el tamaño de este
     * @return Un número entero que funciona como la clave para diferenciar entre los distintos instrumentos
     */
    public int generarClave() {
        int factorClave = 1;
        do {
            if (!directorio.containsKey(directorio.size() + factorClave)) {
                break;
            } else {
                factorClave++;
            }
        } while (true);

        return directorio.size() + factorClave;
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
            try {
                gestor.guardarArchivo(directorio);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Este instrumento ya fue eliminado o no existe.");
        }

    }


    public Instrumento[] aumentarCapacidadArreglo(Instrumento[] arreglo) {
        if (contador == arreglo.length) {
            int nuevoTamaño = (int) (arreglo.length * 1.25);

            arreglo = Arrays.copyOf(arreglo, nuevoTamaño);
            System.out.println("Se creo un nuevo arreglo de " + nuevoTamaño + "elementos");
        }

        return arreglo;
    }

    // 1. Mostrar todos ordenados por clave (del 1 en adelante)
    // Se cambio la firma y contenido de los metodos para reflejar
    /**
     * Metodo que muestra todos los instrumentos que hay en el directorio de instrumento, ordenados por clave
     */
    public ArrayList<Instrumento> mostrarTodos() {
        ArrayList<Instrumento> listadoInstrumentos = new ArrayList<>();
        directorio.entrySet().stream().forEach(k -> {
            listadoInstrumentos.add(k.getValue());
        });
        return listadoInstrumentos;
    }

    public void mostrarArreglo() {
        int posicionActual = 0;
        Arrays.stream(arregloI).forEach( k-> {
                    System.out.println("Clave: " + (posicionActual+1));
                    System.out.println(k);
                });
    }

    // 2. Buscar instrumentos por autor

    /**
     * Metodo que muestra todos los instrumentos bajo el nombre de un autor
     * @param autor Nombre del autor
     */
    // Este no debe imprimir las cosas, la vista lo debe de hacer
    // Debemos usar lambdas aqui
    // Cambiar todas las consultas para que sigan estos principios
    // Filtrar las cosas y volverlo una lista, luego se devuelve el arraylist o lo que vayamos a hacer
    public ArrayList<Instrumento> consultarPorAutor(String autor) {
        ArrayList<Instrumento> instrumentosPorAutor = new ArrayList();
        for (Integer key : directorio.keySet()) {
            Instrumento ins = directorio.get(key);
            if (ins.getAutor().equalsIgnoreCase(autor)) {
                instrumentosPorAutor.add(ins);
            }
        }
        return instrumentosPorAutor;
    }

    // 3. Buscar por condición y validez

    /**
     * Metodo que muestra todos los instrumentos bajo una condicion y una evaluacion de validez especifica
     * @param condicion Numero correspondiente de la condicion a buscar
     * @param validez Resultado de la evaluación de validez y confiabilidad a buscar
     */
    public ArrayList<Instrumento> consultarPorCondicionYValidez(int condicion, boolean validez) {
        ArrayList<Instrumento> instrumentosPorCondicionValidez = new ArrayList<>();
        for (Integer key : directorio.keySet()) {
            Instrumento ins = directorio.get(key);
            if (ins.getCondicion() == condicion && ins.isValidezConfiabilidad() == validez) {
                instrumentosPorCondicionValidez.add(ins);
            }
        }
        return instrumentosPorCondicionValidez;
    }

    /**
     * Metodo que busca todos los instrumentos bajo un mismo tipo, en este caso se uso un Map.Entry para manejarlo todo
     * bajo un lambda y poder avisar que no hay resultados si no se encuentran
     * @param tipoBusqueda Tipo de los instrumentos a buscar
     */
    public ArrayList<Map.Entry<Integer, Instrumento>> consultarPorTipo(String tipoBusqueda) {
        ArrayList<Map.Entry<Integer, Instrumento>> directorioFiltrado = directorio.entrySet().stream().filter((k) ->
                k.getValue().getTipoInstrumento().equalsIgnoreCase(tipoBusqueda)
        ).collect(Collectors.toCollection(ArrayList::new));
        return directorioFiltrado;
    }

    /**
     * Metodo que busca todos los instrumentos bajo una misma condicion, en este caso se uso un Map.Entry
     * para manejarlo bajo un lambda y poder avisar que no hay resultados si no se encuentran
     * @param condicionBusqueda
     */
    public ArrayList consultarPorCondicion(int condicionBusqueda) {
        ArrayList<Map.Entry<Integer, Instrumento>> directorioFiltrado = directorio.entrySet().stream()
                .filter(k -> k.getValue().getCondicion() == condicionBusqueda)
                .collect(Collectors.toCollection(ArrayList::new));
        return directorioFiltrado;
    }

    /**
     * Metodo que busca todos los instrumentos bajo una misma evaluación de validez y busqueda. Aqui tambien usamos
     * Map.Entry para manejar el metodo bajo un lambda y avisar en caso de que no haya coincidencias
     * @param validezBusqueda
     */
    public ArrayList consultarPorValidez(boolean validezBusqueda) {
        ArrayList<Map.Entry<Integer, Instrumento>> directorioFiltrado = directorio.entrySet().stream()
                .filter(k -> k.getValue().isValidezConfiabilidad() == validezBusqueda)
                .collect(Collectors.toCollection(ArrayList::new));
        return directorioFiltrado;
    }

    public void cargarCSVDirectorio(ArrayList<String[]> arregloCSV) {
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
    }

    public HashMap<Integer, Instrumento> getDirectorio() {
        return directorio;
    }
}
