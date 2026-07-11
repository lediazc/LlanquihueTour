package data;

import model.*;
import util.EntradaConsola;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FormularioTurista {

    Scanner sc = new Scanner(System.in);
    private EntradaConsola entrada = new EntradaConsola();

    public Turista agregarTurista() {

        ServicioTuristico servicioTuristico;
        Direccion direccion = new Direccion();

        String nombreTurista;
        String correoTurista;
        String telefonoTurista;
        int edadTurista = 0;
        String generoTurista;
        String respuesta;

        String nombreEvento;
        double horaEvento = 0.0;
        String horaTexto;

        int numeroParticipante = 0;
        String nombreCalleEvento;
        String tipoEdificioEvento;
        String numeroEdificio;

        int seleccionEvento = 0;
        int numeroParadas = 0;
        String tipoEmbarcacion;
        String lugarHistorico;

        System.out.println("Agreguemos un Turista");

        nombreTurista = entrada.solicitarTexto("Digita el nombre del turista: ", "El nombre no puede estar vacío.");

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

        generoTurista = entrada.solicitarTexto("Digita el género del turista: ", "Debes indicar un género.");

        System.out.print("El turista cuenta con un evento asociado (Si/No): ");
        respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            System.out.println("Indica el tipo de evento asociado:");
            do {
                System.out.println("1. Ruta Gastronómica");
                System.out.println("2. Paseo Lacustre");
                System.out.println("3. Excursión Cultural");
                seleccionEvento = sc.nextInt();
                sc.nextLine();
            } while (seleccionEvento != 1 && seleccionEvento != 2 && seleccionEvento != 3);

            nombreEvento = entrada.solicitarTexto("Indica el nombre del evento: ", "El nombre del evento no puede estar vacío.");

            do {
                try {
                    System.out.print("Cuánto durará el evento en horas. Ej: 45 minutos = 0,75: ");
                    horaTexto = sc.nextLine().trim().replace(",", ".");
                    horaEvento = Double.parseDouble(horaTexto);

                    if (!Validador.numerosPositivos(horaEvento)) {
                        System.out.println("La duración debe ser mayor a 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debes ingresar un número válido. Ejemplo: 1,5 o 1.5");
                    horaEvento = 0;
                }
            } while (!Validador.numerosPositivos(horaEvento));

            do {
                try {
                    System.out.print("El número de participantes del evento: ");
                    numeroParticipante = sc.nextInt();
                    sc.nextLine();

                    if (!Validador.numerosPositivos(numeroParticipante)) {
                        System.out.println("El número no cumple el formato.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Se produjo el siguiente error al ingresar n° de participantes: " + e);
                    sc.nextLine();
                }
            } while (!Validador.numerosPositivos(numeroParticipante));

            nombreCalleEvento = entrada.solicitarTexto("El nombre de la calle en donde es este evento: ", "Debes indicar una calle.");
            tipoEdificioEvento = entrada.solicitarTexto("El edificio tiene un nombre particular o es otro inmueble?: ", "Debes indicar un tipo de edificio.");
            numeroEdificio = entrada.solicitarTexto("El número del edificio: ", "Debes indicar un n° para el edificio.");

            direccion = new Direccion(nombreCalleEvento, tipoEdificioEvento, numeroEdificio);

            switch (seleccionEvento) {
                case 1:
                    do {
                        try {
                            System.out.print("Selecciona el número de paradas de la ruta gastronómica: ");
                            numeroParadas = sc.nextInt();
                            sc.nextLine();

                            if (!Validador.numerosPositivos(numeroParadas)) {
                                System.out.println("El número no cumple el formato");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Se produjo el siguiente error al ingresar n° de paradas: " + e);
                            sc.nextLine();
                        }
                    } while (!Validador.numerosPositivos(numeroParadas));

                    servicioTuristico = new RutaGastronomica(nombreEvento, horaEvento, numeroParadas, direccion, numeroParticipante);
                    break;

                case 2:
                    tipoEmbarcacion = entrada.solicitarTexto("Qué tipo de embarcación se utilizará: ", "Debes indicar un tipo de embarcación.");
                    servicioTuristico = new PaseoLacustre(nombreEvento, horaEvento, tipoEmbarcacion, direccion, numeroParticipante);
                    break;

                case 3:
                    lugarHistorico = entrada.solicitarTexto("Qué lugar histórico se visitará: ", "Debes indicar un lugar histórico.");
                    servicioTuristico = new ExcursionCultural(nombreEvento, horaEvento, lugarHistorico, direccion, numeroParticipante);
                    break;

                default:
                    servicioTuristico = new ExcursionCultural();
                    break;
            }


        } else {
            System.out.println("Crearemos al turista sin evento asociado.");
            servicioTuristico = new ExcursionCultural();
        }

        return new Turista(nombreTurista, correoTurista, telefonoTurista, edadTurista, generoTurista, servicioTuristico);
    }
}