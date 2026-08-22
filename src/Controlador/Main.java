import Modelo.GestorArchivo;
import Modelo.Instrumento;
import Modelo.SistemaAPA;
import java.util.ArrayList;
import Vista.*;

import java.io.IOException;

// TODO: Pasar a un projecto de JavaFX este proyecto para empezar con lo visual

public static void main() {
    /*
    Aqui se va a enlazar la vista y el modelo cuando esten listos, por mientras vayan trabajando
    en los archivos de texto, estos se deben guardar o cargar automaticamente al registrar usuarios,
    eliminarlos, y al correr o salir de la aplicacion
     */
    Vista vista = new Vista();
    SistemaAPA modelo = new SistemaAPA();
    GestorArchivo gestor = new GestorArchivo();
    int opcion;
    try {
        modelo.cargarCSVDirectorio(gestor.leerArchivo());
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
    do {
        vista.mostrarMenu();
        opcion = vista.leerNumeroEntero();
        switch (opcion) {
            case 1:
                // Pedimos las cosas una a una y luego registramos el instrumento
                // El orden es nombre, autor, tipo, condicion, validez y fecha
                String nombre = vista.pedirNombre();
                String autor = vista.pedirAutor();
                String tipo = vista.pedirTipo();
                int condicion = vista.pedirCondicion();
                boolean validez = vista.pedirValidez();
                String fecha = vista.pedirFecha();


                //modelo.registrarInstrumento(new Instrumento(), 1);
                modelo.registrarInstrumento(nombre, autor, tipo, condicion, validez, fecha);
                break;
            case 2:
                menuConsulta(vista, modelo);
                break;
            case 3:
                System.out.println("Introduce la clave del instrumento: ");

                int claveAEliminar = vista.leerNumeroEntero();
                modelo.eliminarInstrumento(claveAEliminar);
                break;
            case 4:
                System.out.println("\nSaliendo...");
                try {
                    gestor.guardarArchivo(modelo.mostrarTodos());
                } catch (IOException e) {
                    System.out.println("Error: El sistema no pudo guardar en un archivo de texto el instrumento");
                }
                break;
            default:
                break;
        }

    } while (opcion != 4);
}

/**
 * Este metodo es un submenu para las consultas, se dividio para hacer más legible el switch del metodo principal
 * @param vista Vista que se esta usando para el programa
 * @param modelo Modelo usado para el programa
 */
static void menuConsulta(Vista vista, SistemaAPA modelo) {
    int opcionConsulta = vista.pedirOpcionConsulta();
    switch(opcionConsulta) {
        case 1:
            // Vamos a mostrar el Arreglo
            //modelo.mostrarArreglo();
            ArrayList<Instrumento> listaInstrumentos = modelo.mostrarTodos();
            vista.mostrarTodosLosInstrumentos(listaInstrumentos);
            break;
        case 2:
            ArrayList<Instrumento> instrumentosPorAutor = modelo.consultarPorAutor(vista.pedirAutor());
            vista.mostrarLosInstrumentosDeAutor(instrumentosPorAutor);
            break;
        case 3:
            ArrayList<Instrumento> instrumentosPorAutorValidez = modelo.consultarPorCondicionYValidez(
                    vista.pedirCondicion(), vista.pedirValidez()
            );
            vista.mostrarLosInstrumentosPorCondicionValidez(instrumentosPorAutorValidez);
            break;
        case 4:
            ArrayList instrumentosPorTipo = modelo.consultarPorTipo(vista.pedirTipo());
            vista.mostrarInstrumentosFiltrados(instrumentosPorTipo);
         break;
        case 5:
            ArrayList instrumentosPorCondicion = modelo.consultarPorCondicion(vista.pedirCondicion());
            vista.mostrarInstrumentosFiltrados(instrumentosPorCondicion);
            break;
        case 6:
            ArrayList instrumentosPorValidez = modelo.consultarPorValidez(vista.pedirValidez());
            vista.mostrarInstrumentosFiltrados(instrumentosPorValidez);
            break;
        case 7:
        default:
            System.out.println("Volviendo al menu principal...");
    }

}


