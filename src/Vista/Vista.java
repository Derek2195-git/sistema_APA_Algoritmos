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

    public void pedirTipo() {

    }
    String nombre;
    private String autor;
    private String tipoInstrumento;
    private int condicion;
    private boolean validezConfiabilidad;
    private String fecha;


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


}
