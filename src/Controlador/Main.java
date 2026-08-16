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
            /*
             Esto se debe poner en la vista, lo deje asi por mientras en lo que
             checaba si se estaban metiendo los instrumentos
             */
                System.out.println("Introduce la clave del instrumento");
                int clave = vista.leerNumeroEntero();
                modelo.consultarInstrumento(clave);
                break;
            case 3:
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                break;
        }

    } while (opcion != 4);
}

