package util;

import java.util.Scanner;

public class EntradaConsola {

    private Scanner sc = new Scanner(System.in);

    public String solicitarTexto(String mensajeConsola, String mensajeError) {
        String dato;

        do {
            System.out.print(mensajeConsola);
            dato = sc.nextLine().trim().toLowerCase();

            if (!Validador.textoValido(dato)) {
                System.out.println(mensajeError);
            }

        } while (!Validador.textoValido(dato));

        return dato;
    }
}
