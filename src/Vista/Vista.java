package Vista;
import Modelo.*;

import java.util.Scanner;

public class Vista {
    Scanner teclado;

    public Vista() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Bienvenido al Sistema de la APA. Por favor ingresa una opción" +
                "\n 1. Registrar un instrumento \n 2. Consultar un instrumento " +
                "\n 3. Eliminar un instrumento \n 4. Salir");
    }

    public String pedirNombre() {
        System.out.println("Ingresa el nombre del instrumento: ");
        return leerCadena();
    }

    public String pedirAutor() {
        System.out.println("Ingresa el nombre del autor del instrumento: ");
        return leerCadena();
    }

    public String pedirTipo() {
        System.out.println("Ingresa el tipo de instrumento: ");
        return leerCadena();
    }

    public int pedirCondicion() {
        System.out.println("Escribe el numero que corresponda a la condición: \n " +
                "1. El instrumento es para la ansiedad \n 2. El instrumento es para el estrés " +
                "\n 3. El instrumento es para la ansiedad y el estrés");
        return leerNumeroEntero();
    }

    public boolean pedirValidez() {
        System.out.println("¿El instrumento esta evaluado como valido y confiable? Escribe Si o No");
        return leerBooleano();
    }

    public String pedirFecha() {
        System.out.println("Escribe la fecha en la que se realiza la evaluación");
        return leerCadena();
    }


    public String leerCadena() {
        return teclado.nextLine().trim().toLowerCase();
    }

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

    public boolean leerBooleano() {
        String cadena = leerCadena();
        boolean valido;
        while(true) {
            // Tenemos que verificar primero si no son otra cosa
            if (cadena.equalsIgnoreCase("Si") || cadena.equalsIgnoreCase("No")) {
                valido = cadena.equalsIgnoreCase("Si") ? true : false;
                break;
            } else {
                System.out.println("Entrada invalida, por favor escribe 'Si' o 'No' ");
                cadena = leerCadena();
            }
        }
        return valido;

    }


}
