package data;

import model.*;
import util.EntradaConsola;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Permite ingresar por consola la información necesaria para crear un nuevo OperadorLocal.
 */

public class FormularioOperadorLocal {

    Scanner sc = new Scanner(System.in);
    private EntradaConsola entrada = new EntradaConsola();

    /**
     * Solicita al usuario los datos de un operador local, valida la información ingresada y construye el nuevo OperadorLocal.
     * @return OperadorLocal creado con los datos ingresados.
     */
    public OperadorLocal agregarOperadorLocal(){

        ServicioTuristico servicioTuristico;
        Direccion direccion = new Direccion();
        String nombreOperador;
        String rutOperador;
        String correoOperador;
        String telefonoOperador;
        String tipoServicio;
        String comuna;
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

        boolean vigencia = false;

        System.out.println("Agregemos un Operador Local");

        nombreOperador = entrada.solicitarTexto("Digita el nombre del operador: ", "El nombre no puede estar vacío.");

        do {
            System.out.print("Digita el RUT del operador(xxxxxxxx-x): ");
            rutOperador = sc.nextLine().trim();

            if (!Validador.rutValido(rutOperador)) {
                System.out.println("El RUT ingresado no es válido.");
            }
        } while (!Validador.rutValido(rutOperador));


        do {

            System.out.print("Digita el correo del operador(xx@xx.cl): ");
            correoOperador = sc.nextLine().trim().toLowerCase();

            if (!Validador.correoValido(correoOperador)) {
                System.out.println("El correo no respeta el formato solicitado xx@xx.cl.");
            }

        } while (!Validador.correoValido(correoOperador));


        do {

            System.out.print("Digita el número de contacto del operador(No agregar +569): ");
            telefonoOperador = sc.nextLine().trim();

            if (!Validador.telefonoValido(telefonoOperador)) {
                System.out.println("El teléfono debe tener exactamente 8 dígitos.");
            }

        } while (!Validador.telefonoValido(telefonoOperador));

        comuna = entrada.solicitarTexto("En que comuna tiene actividad: ", "Debes indicar una comuna.");


        System.out.print("El operador cuenta con un evento asociado (Si/No) : ");
        respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            System.out.println("¡Muy bien!  indicanos el tipo de evento asociado");
            do {
                System.out.println("1. Ruta Gastronomica");
                System.out.println("2. Paseo Lacustre");
                System.out.println("3. Excursion Cultural");
                seleccionEvento = sc.nextInt();
                sc.nextLine();

            } while (seleccionEvento != 1 && seleccionEvento != 2 && seleccionEvento != 3 );

            nombreEvento = entrada.solicitarTexto("Muy bien! indicanos el nombre del evento: ", "El nombre del evento no puede estar vacío.");

            do {
                try{
                    System.out.print("Cuanto durará el evento (en horas, ejem: 45 minutos son 0.75: ");

                    horaTexto = sc.nextLine().trim().replace(",", ".");
                    horaEvento = Double.parseDouble(horaTexto);


                    if (!Validador.numerosPositivos(horaEvento)) {
                        System.out.println("El número no cumple el formato");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Se produjo el siguiente error al ingresar n° de participantes: " + e);
                    sc.nextLine();
                }

            } while (!Validador.numerosPositivos(horaEvento));

            do {
                try{
                    System.out.print("El número de participantes del evento: ");
                    numeroParticipante = sc.nextInt();
                    sc.nextLine();


                    if (!Validador.numerosPositivos(numeroParticipante)) {
                        System.out.println("El número no cumple el formato");
                    }
                }catch(InputMismatchException e){
                    System.out.println("Se produjo el siguiente error al ingresar n° de participantes: " + e);
                    sc.nextLine();
                }

            } while (!Validador.numerosPositivos(numeroParticipante));



            nombreCalleEvento = entrada.solicitarTexto("El nombre de la calle en donde es este evento: ", "Debes indicar una calle.");
            tipoEdificioEvento = entrada.solicitarTexto("El edificio tiene un nombre particular o es otro inmueble?: ", "Debes indicar un tipo de edificio.");
            numeroEdificio = entrada.solicitarTexto("El número del edificio: ", "Debes indicar una n° para el edificio.");
            direccion = new Direccion( nombreCalleEvento,tipoEdificioEvento, numeroEdificio);

            switch (seleccionEvento){
                case 1: //Ruta gastronómica
                    do {
                        try{
                            System.out.print("Selecciona el número de paradas de la ruta Gastronómica: ");
                            numeroParadas = sc.nextInt();
                            sc.nextLine();


                            if (!Validador.numerosPositivos(numeroParadas)) {
                                System.out.println("El número no cumple el formato");
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Se produjo el siguiente error al ingresar n° de paradas: " + e);
                            sc.nextLine();
                        }

                    } while (!Validador.numerosPositivos(numeroParadas));


                    System.out.println("Excelente! Crearemos al operador con estado: Vigente");
                    servicioTuristico = new RutaGastronomica(nombreEvento, horaEvento, numeroParadas, direccion, numeroParticipante);
                    break;

                case 2: //Paseo lacustre
                    tipoEmbarcacion = entrada.solicitarTexto("Que tipo de embarcación se utilizará: ", "Debes indicar un tipo de embarcación.");
                    servicioTuristico = new PaseoLacustre(nombreEvento, horaEvento, tipoEmbarcacion, direccion, numeroParticipante);
                    servicioTuristico.setDireccion(direccion);
                    break;
                case 3: //Excursión cultural
                    lugarHistorico = entrada.solicitarTexto("Que lugar historico se visitara en esta ocasión: ", "Debes indicar un lugar ´histórico.");
                    servicioTuristico = new ExcursionCultural(nombreEvento, horaEvento, lugarHistorico, direccion, numeroParticipante);
                    servicioTuristico.setDireccion(direccion);
                    break;
                default:
                    servicioTuristico = new ExcursionCultural();
                    break;
            }

            vigencia = true;


        } else if (respuesta.equals("no") || respuesta.equals("n")) {

            System.out.println("Excelente! Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            servicioTuristico = new ExcursionCultural();

        } else {

            System.out.println("Respuesta inválida");
            System.out.println("Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            servicioTuristico = new ExcursionCultural();

        }

        return new OperadorLocal( nombreOperador, rutOperador, correoOperador, telefonoOperador, comuna, servicioTuristico, vigencia);

    }

    public OperadorLocal agregarOperadorLocal(
            String nombreOperador,
            String rutOperador,
            String correoOperador,
            String telefonoOperador,
            String comuna
    ) {

        if (nombreOperador == null || nombreOperador.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del operador no puede estar vacío.");
        }

        rutOperador = rutOperador.trim().toLowerCase();

        if (!Validador.rutValido(rutOperador)) {
            throw new IllegalArgumentException("El RUT no respeta el formato solicitado.");
        }

        correoOperador = correoOperador.trim().toLowerCase();

        if (!Validador.correoValido(correoOperador)) {
            throw new IllegalArgumentException("El correo no respeta el formato solicitado.");
        }

        telefonoOperador = telefonoOperador.trim();

        if (!Validador.telefonoValido(telefonoOperador)) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 8 dígitos.");
        }

        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("Debes indicar una comuna.");
        }

        ServicioTuristico servicioTuristico = new ExcursionCultural();
        boolean vigencia = false;

        return new OperadorLocal(
                nombreOperador.trim(),
                rutOperador,
                correoOperador,
                telefonoOperador,
                comuna.trim(),
                servicioTuristico,
                vigencia
        );
    }

    public OperadorLocal agregarOperadorLocal(
            String nombreOperador,
            String rutOperador,
            String correoOperador,
            String telefonoOperador,
            String comuna,
            ServicioTuristico servicioTuristico
    ) {

        if (nombreOperador == null || nombreOperador.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del operador no puede estar vacío.");
        }

        rutOperador = rutOperador.trim().toLowerCase();

        if (!Validador.rutValido(rutOperador)) {
            throw new IllegalArgumentException("El RUT no respeta el formato solicitado.");
        }

        correoOperador = correoOperador.trim().toLowerCase();

        if (!Validador.correoValido(correoOperador)) {
            throw new IllegalArgumentException("El correo no respeta el formato solicitado.");
        }

        telefonoOperador = telefonoOperador.trim();

        if (!Validador.telefonoValido(telefonoOperador)) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 8 dígitos.");
        }

        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("Debes indicar una comuna.");
        }

        return new OperadorLocal(
                nombreOperador.trim(),
                rutOperador,
                correoOperador,
                telefonoOperador,
                comuna.trim(),
                servicioTuristico,
                true
        );
    }
}
