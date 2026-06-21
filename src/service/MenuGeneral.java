package service;

import model.OperadorLocal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGeneral {

    private Scanner sc = new Scanner(System.in);
    private GestorDatosOperador gestor = new GestorDatosOperador();
    private ConsultaOperadorLocal consulta = new ConsultaOperadorLocal();

    public void mostrarMenu() {

        ArrayList<OperadorLocal> gestorOperadores = gestor.leerOperadoresDesdeArchivo();
        int eleccionUsuario = 0;
        do {
            try {
                System.out.println("Bienvenido al Gestor de Personal de Llanquihue Tour");
                System.out.println("Por favor, seleccione una de las siguientes opciones: " + "\n" +
                        "1) Mostrarme todos los registros del personal." + "\n" +
                        "2) Mostrarme sólo los resultados, en base a datos filtrados." + "\n" +
                        "3) Agregar Operador Local." + "\n" +
                        "4) Salir."
                );

                System.out.print("Elección deseada: ");
                eleccionUsuario = sc.nextInt();

                switch (eleccionUsuario) {
                    case 1:
                        consulta.mostrarTodosLosOperadores(gestorOperadores);
                        break;

                    case 2:
                        consulta.subMenuFiltrosOperadores(gestorOperadores);
                        break;

                    case 3:
                        gestor.agregarOperadorYGuardar();
                        gestorOperadores = gestor.leerOperadoresDesdeArchivo();
                        break;

                    case 4:
                        System.out.println("Hasta luego.");
                        break;

                    default:
                        System.out.println("¡Ups! ¡Esa opción no existe!");
                }
            }catch(InputMismatchException e){
                System.out.println("Ocurrió el siguiente error: " + e);
                sc.nextLine();
            }
        } while (eleccionUsuario != 4);
    }
}