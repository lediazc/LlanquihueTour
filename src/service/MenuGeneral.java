package service;

import model.OperadorLocal;
import model.Turista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Controla el menú principal de navegación del sistema y coordina las funcionalidades disponibles.
 */

public class MenuGeneral {

    private Scanner sc = new Scanner(System.in);
    private GestorDatosOperador gestorOperador = new GestorDatosOperador();
    private ConsultaOperadorLocal consultaOperador = new ConsultaOperadorLocal();
    private ConsultaTurista consultaTurista = new ConsultaTurista();
    private GestorDatosTurista gestorTurista = new GestorDatosTurista();
    int eleccionUsuario = 0;

    /**
     * Muestra el menú principal del sistema y procesa las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        ArrayList<OperadorLocal> gestorOperadores = gestorOperador.leerOperadoresDesdeArchivo();
        ArrayList<Turista> gestorTuristas = gestorTurista.leerTuristasDesdeArchivo();
        do {
            try {
                System.out.println("Bienvenido al Gestor de Personal de Llanquihue Tour");
                System.out.println("Por favor, seleccione una de las siguientes opciones: "        + "\n" +
                                    "1) Mostrarme todos los registros de operadores."              + "\n" +
                                    "2) Mostrarme todos los registros de turistas."                + "\n" +
                                    "3) Filtrar registros de operadores."                          + "\n" +
                                    "4) Agregar Operador Local."                                   + "\n" +
                                    "5) Agregar turista."                                          + "\n" +
                                    "6) Salir."
                );

                System.out.print("Elección deseada: ");
                eleccionUsuario = sc.nextInt();
                sc.nextLine();

                switch (eleccionUsuario) {
                    case 1:
                        consultaOperador.mostrarTodosLosOperadores(gestorOperadores);
                        confirmacionSalida();
                        break;

                    case 2:
                        consultaTurista.mostrarTodosLosTuristas(gestorTuristas);
                        confirmacionSalida();
                        break;

                    case 3:
                        consultaOperador.subMenuFiltrosOperadores(gestorOperadores);
                        confirmacionSalida();
                        break;

                    case 4:
                        gestorOperador.agregarOperadorYGuardar();
                        gestorOperadores = gestorOperador.leerOperadoresDesdeArchivo();
                        confirmacionSalida();
                        break;

                    case 5:
                        gestorTurista.agregarTuristaYGuardar();
                        gestorTuristas = gestorTurista.leerTuristasDesdeArchivo();
                        confirmacionSalida();
                        break;

                    case 6:
                        System.out.println("Hasta luego.");
                        break;

                    default:
                        System.out.println("¡Ups! ¡Esa opción no existe!");
                }
            }catch(InputMismatchException e){
                System.out.println("Ocurrió el siguiente error: " + e);
                sc.nextLine();
            }
        } while (eleccionUsuario != 6);
    }

    /**
     * Solicita confirmación al usuario para continuar utilizando el sistema o finalizar la ejecución.
     */
    public void confirmacionSalida(){

        System.out.println("¿Deseas Otra operación?");

        String respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            System.out.println("¡Muy bien! cuentanos que más tienes en mente");

        } else if (respuesta.equals("no") || respuesta.equals("n")) {

            System.out.println("Hasta Luego");
            eleccionUsuario = 6;

        } else {

            System.out.println("Respuesta inválida.");
            System.out.println("¡Cerraremos el programa!");
            eleccionUsuario = 6;
        }


    }
}