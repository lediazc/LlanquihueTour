package util;

import java.util.Scanner;


/**
 * Centraliza la captura de datos ingresados por consola y aplica validaciones básicas de texto.
 */
public class EntradaConsola {

    private Scanner sc = new Scanner(System.in);

    /**
     * Solicita un texto al usuario hasta que el dato ingresado cumpla con las validaciones definidas.
     * @param mensajeConsola Mensaje mostrado al usuario.
     * @param mensajeError Mensaje mostrado en caso de error.
     * @return Texto validado ingresado por el usuario.
     */
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
