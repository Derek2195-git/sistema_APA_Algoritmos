import Modelo.SistemaAPA;
import Vista.*;
void main() {
    /*
    Aqui se va a enlazar la vista y el modelo cuando esten listos, por mientras vayan trabajando
    en los archivos de texto, estos se deben guardar o cargar automaticamente al registrar usuarios,
    eliminarlos, y al correr o salir de la aplicacion
     */
    Vista vista = new Vista();
    SistemaAPA modelo = new SistemaAPA();
    int opcion = 0;
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

                modelo.registrarInstrumento(nombre, autor, tipo, condicion, validez, fecha);
                break;
            case 2:
                int opcionConsulta = vista.pedirOpcionConsulta();

                if (opcionConsulta == 1) {
                    modelo.mostrarTodos();
                } else if (opcionConsulta == 2) {
                    String autorBusqueda = vista.pedirAutor();
                    modelo.consultarPorAutor(autorBusqueda);
                } else if (opcionConsulta == 3) {
                    int condicionBusqueda = vista.pedirCondicion();
                    boolean validezBusqueda = vista.pedirValidez();
                    modelo.consultarPorCondicionYValidez(condicionBusqueda, validezBusqueda);
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
            case 3:
                System.out.println("Introduce la clave del instrumento: ");
                int claveAEliminar = vista.leerNumeroEntero();
                modelo.eliminarInstrumento(claveAEliminar);
                break;
            case 4:
                System.out.println("\nSaliendo...");
                break;
            default:
                break;
        }

    } while (opcion != 4);
}

