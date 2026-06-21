package service;

import model.Direccion;
import model.Evento;
import model.OperadorLocal;
import util.EntradaConsola;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FormularioOperadorLocal {

    Scanner sc = new Scanner(System.in);
    private EntradaConsola entrada = new EntradaConsola();

    public OperadorLocal agregarOperadorLocal(){

        Evento evento;
        Direccion direccion;
        String nombreOperador;
        String correoOperador;
        String telefonoOperador;
        String tipoServicio;
        String comuna;
        String respuesta;

        String nombreEvento;
        int numeroParticipante = 0;
        String nombreCalleEvento;
        String tipoEdificioEvento;
        String numeroEdificio;

        boolean vigencia = false;

        System.out.println("Agregemos un Operador Local");

        nombreOperador = entrada.solicitarTexto("Digita el nombre del operador: ", "El nombre no puede estar vacío.");

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


        tipoServicio = entrada.solicitarTexto("Que servicio presta el operador: ", "Debes indicar un tipo de servicio.");
        comuna = entrada.solicitarTexto("En que comuna: ", "Debes indicar una comuna.");


        System.out.print("El operador cuenta con un evento asociado (Si/No) : ");
        respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            nombreEvento = entrada.solicitarTexto("Muy bien! indicanos el nombre del evento: ", "El nombre del evento no puede estar vacío.");

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
            System.out.println("Excelente! Crearemos al operador con estado: Vigente");


            direccion = new Direccion(nombreCalleEvento, tipoEdificioEvento, numeroEdificio);
            evento = new Evento(nombreEvento, numeroParticipante, direccion);

            vigencia = true;


        } else if (respuesta.equals("no") || respuesta.equals("n")) {

            System.out.println("Excelente! Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            evento = new Evento();

        } else {

            System.out.println("Respuesta inválida");
            System.out.println("Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            evento = new Evento();

        }

        return new OperadorLocal(nombreOperador, correoOperador, telefonoOperador, tipoServicio, comuna, evento, vigencia);

    }
}
