package Vista;
import Modelo.*;

import java.util.Scanner;

public class Vista {
    Instrumento instrumento = new Instrumento();

    Scanner teclado = new Scanner(System.in);
    // Esto se va a reescribir, por que no es lo que se pedia
    public void preguntarNombreInstrumento() {
        System.out.println("Ingresa el nombre del autor");
        String autor = leerCadena();
        System.out.println("Mostrando los instrumentos que tiene " + autor);
        instrumento.getDirectorioAutores().get(autor);
        System.out.println("Introduce la opcion a realizar \n 1. Identificar por tipo \n " +
                "2. Identificar por forma de instrumento \n 3. Identificar por condicion" );

    }

    public String leerCadena() {
        String cadena = teclado.nextLine();
        return cadena;
    }


}
