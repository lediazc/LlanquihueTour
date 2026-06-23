package service;

import model.Direccion;
import model.Evento;
import model.Turista;
import util.EntradaConsola;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Permite ingresar por consola la información necesaria para crear un nuevo Turista.
 */
public class FormularioTurista {

    Scanner sc = new Scanner(System.in);
    private EntradaConsola entrada = new EntradaConsola();

    /**
     * Solicita al usuario los datos de un turista, valida la información ingresada y construye el nuevo Turista.
     *
     * @return Turista creado con los datos ingresados.
     */

    public Turista agregarTurista() {

        Evento evento;
        Direccion direccion;

        String nombreTurista;
        String correoTurista;
        String telefonoTurista;
        int edadTurista = 0;
        String generoTurista;
        String respuesta;

        String nombreEvento;
        int numeroParticipante = 0;
        String nombreCalleEvento;
        String tipoEdificioEvento;
        String numeroEdificio;

        System.out.println("Agreguemos un Turista");

        nombreTurista = entrada.solicitarTexto("Digita el nombre del turista: ","El nombre no puede estar vacío.");

        do {
            System.out.print("Digita el correo del turista(xx@xx.cl): ");
            correoTurista = sc.nextLine().trim().toLowerCase();

            if (!Validador.correoValido(correoTurista)) {
                System.out.println("El correo no respeta el formato solicitado xx@xx.cl.");
            }

        } while (!Validador.correoValido(correoTurista));

        do {
            System.out.print("Digita el número de contacto del turista(No agregar +569): ");
            telefonoTurista = sc.nextLine().trim();

            if (!Validador.telefonoValido(telefonoTurista)) {
                System.out.println("El teléfono debe tener exactamente 8 dígitos.");
            }

        } while (!Validador.telefonoValido(telefonoTurista));

        do {
            try {
                System.out.print("Digita la edad del turista: ");
                edadTurista = sc.nextInt();
                sc.nextLine();

                if (!Validador.numerosPositivos(edadTurista)) {
                    System.out.println("La edad debe ser un número positivo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Se produjo el siguiente error al ingresar la edad: " + e);
                sc.nextLine();
            }

        } while (!Validador.numerosPositivos(edadTurista));

        generoTurista = entrada.solicitarTexto("Digita el género del turista: ","Debes indicar un género.");

        System.out.print("El turista cuenta con un evento asociado (Si/No): ");
        respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            nombreEvento = entrada.solicitarTexto("Muy bien! indícanos el nombre del evento: ","El nombre del evento no puede estar vacío.");

            do {
                try {
                    System.out.print("El número de participantes del evento: ");
                    numeroParticipante = sc.nextInt();
                    sc.nextLine();

                    if (!Validador.numerosPositivos(numeroParticipante)) {
                        System.out.println("El número no cumple el formato.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Se produjo el siguiente error al ingresar n° de participantes: " + e);
                    sc.nextLine();
                }

            } while (!Validador.numerosPositivos(numeroParticipante));

            nombreCalleEvento = entrada.solicitarTexto(
                    "El nombre de la calle en donde es este evento: ",
                    "Debes indicar una calle."
            );

            tipoEdificioEvento = entrada.solicitarTexto(
                    "El edificio tiene un nombre particular o es otro inmueble?: ",
                    "Debes indicar un tipo de edificio."
            );

            numeroEdificio = entrada.solicitarTexto(
                    "El número del edificio: ",
                    "Debes indicar un n° para el edificio."
            );

            direccion = new Direccion(nombreCalleEvento, tipoEdificioEvento, numeroEdificio);
            evento = new Evento(nombreEvento, numeroParticipante, direccion);

        } else if (respuesta.equals("no") || respuesta.equals("n")) {

            System.out.println("Crearemos al turista sin evento asociado.");

            direccion = new Direccion();
            evento = new Evento();

        } else {

            System.out.println("Respuesta inválida.");
            System.out.println("Crearemos al turista sin evento asociado.");

            direccion = new Direccion();
            evento = new Evento();
        }

        return new Turista(nombreTurista, correoTurista, telefonoTurista, edadTurista, generoTurista, evento);
    }
}