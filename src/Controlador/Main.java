import Modelo.SistemaAPA;
import Vista.*;
void main() {
    /*
    Aqui se va a enlazar la vista y el modelo cuando esten listos, por mientras vayan trabajando
    en los archivos de texto, estos se deben guardar o cargar automaticamente al registrar usuarios,
    eliminarlos, y al correr o salir de la aplicacion
     */
    Vista vista = new Vista();
    vista.mostrarMenu();
    SistemaAPA modelo = new SistemaAPA();
    int opcion = vista.leerNumeroEntero();
    switch (opcion) {
        case 1:

            modelo.registrarInstrumento();
            break;
        case 2:

            break;
        case 3:
            break;
        case 4:
            break;
        default:
            break;
    }
}

