package Vista;
import Modelo.*;

import java.util.Scanner;

public class Vista {
    Scanner teclado;

    public Vista() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("ESte es un menu");
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


}
