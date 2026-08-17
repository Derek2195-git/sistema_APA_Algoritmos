package Vista;
import Modelo.*;

import java.util.Scanner;

public class Vista {
    Scanner teclado;

    /**
     * Constructor de la vista
     */
    public Vista() {
        teclado = new Scanner(System.in);
    }

    /**
     * Muestra el menu de opciones a elegir
     */
    public void mostrarMenu() {
        System.out.println("\nBienvenido al Sistema de la APA. Por favor ingresa una opción" +
                "\n 1. Registrar un instrumento \n 2. Consultar un instrumento " +
                "\n 3. Eliminar un instrumento \n 4. Salir");
    }

    /**
     * Este metodo se usa para pedir o leer el nombre del instrumento al usuario
     * @return Nombre del instrumento
     */
    public String pedirNombre() {
        System.out.println("\nIngresa el nombre del instrumento: ");
        return leerCadena();
    }

    /**
     * Este metodo se usa para pedir o leer el autor del instrumento al usuario
     * @return Autor del instrumento
     */
    public String pedirAutor() {
        System.out.println("Ingresa el nombre del autor del instrumento: ");
        return leerCadena();
    }

    /**
     * Este metodo se usa para pedir o leer el tipo del instrumento al usuario
     * @return Tipo del instrumento
     */
    public String pedirTipo() {
        System.out.println("Ingresa el tipo de instrumento: ");
        return leerCadena();
    }

    /**
     * Este metodo se usa para pedir o leer la condicion del instrumento al usuario, si es usado para
     * el estrés, la ansiedad o ambas cosas
     * @return Número el cual representa el uso del instrumento
     */
    public int pedirCondicion() {
        int numeroLeido;
        do {
            System.out.println("Escribe el numero que corresponda a la condición: \n " +
                    "1. El instrumento es para la ansiedad \n 2. El instrumento es para el estrés " +
                    "\n 3. El instrumento es para la ansiedad y el estrés");
            numeroLeido = leerNumeroEntero();
            if (numeroLeido > 0 && numeroLeido <= 3) {
                break;
            } else {
                System.out.println("Error: El numero ingresado no esta en el rango de condiciones");
            }
        } while (!(numeroLeido > 0) || !(numeroLeido <= 3));
        return numeroLeido;
    }

    /**
     * Este metodo se usa para pedir o leer la validez y confiabilidad del instrumento al usuario
     * @return Un booleano que determina si el instrumento es valido y confiable
     */
    public boolean pedirValidez() {
        System.out.println("¿El instrumento esta evaluado como valido y confiable? Escribe Si o No");
        return verificarValidez();
    }

    /**
     * Este metodo se usa para pedir o leer la fecha en la que se realizó la
     * evaluación de confiabilidad al usuario
     * @return Una cadena la cual indica la fecha en la que se realizó la evaluación
     */
    public String pedirFecha() {
        System.out.println("Escribe la fecha en la que se realizó la evaluacion de validez y " +
                "confiabilidad en el formato que se desee");
        return leerCadena();
    }

    /**
     * Este metodo lo usamos para leer una cadena, donde se eliminan los espacios o caracteres
     * extras antes o despues de la cadena ingresada por el usuario
     * @return Una cadena sin espacios innecesarios y convertida a minusculas
     */
    public String leerCadena() {
        return teclado.nextLine().trim().toLowerCase().replace(",", "");
    }

    /**
     * Este metodo se usa para leer un numero y comprueba que no sea un numero decimal o con otro formato
     * @return Retorna un numero entero
     */
    public int leerNumeroEntero() {
        boolean numeroIngresado = false;
        int numeroLeido = 0;
        while (!numeroIngresado) {
            try {
                String numero =  teclado.nextLine().trim();
                numeroLeido = Integer.parseInt(numero);
                numeroIngresado = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida, escribe solamente un numero entero.");
            }
        }
        return numeroLeido;
    }

    /**
     * Este metodo se usa especificamente para leer el booleano en leerValidez(), donde se verifica que
     * la cadena ingresada fuera escrita correctamente y posteriormente se "convierte" en un booleano
     * @return Un booleano que indica si el instrumento es valido y confiable
     */
    private boolean verificarValidez() {
        String cadena;
        boolean cadenaValida = false;
        boolean resultado = true;
        do {
            cadena = leerCadena();
            if (cadena.equalsIgnoreCase("Si") || cadena.equalsIgnoreCase("No")) {
                resultado = cadena.equalsIgnoreCase("Si") ? true : false;
                cadenaValida = true;
            } else {
                System.out.println("Error: Ingresa por favor 'Si' o 'No'");
            }
        } while (!cadenaValida);
        return resultado;
    }

    /**
     * Metodo que muestra un submenú al consultar los instrumentos
     * @return opcion del menu dada por el usuario
     */
    public int pedirOpcionConsulta() {
        System.out.println("¿Cómo deseas consultar los instrumentos?\n" +
                " 1. Mostrar todos\n" +
                " 2. Buscar por autor\n" +
                " 3. Buscar por condición y validez \n" +
                " 4. Buscar por tipo de instrumento \n" +
                " 5. Buscar por condicion del instrumento \n" +
                " 6. Buscar de acuerdo a la evaluación de validez y confiabilidad \n" +
                " 7. Volver al menú anterior");

        return leerNumeroEntero();
    }



}
