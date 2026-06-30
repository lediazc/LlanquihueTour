package service;

import model.OperadorLocal;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsultaOperadorLocal {

    /**
     * Muestra el submenú de filtros disponibles para la búsqueda de operadores locales y ejecuta la opción seleccionada por el usuario.
     *  Se activa una vez seleccionada la opción 2 en el menú global del aplicativo
     *
     * @param operadores Lista de operadores sobre la cual se realizarán los filtros y búsquedas.
     */

    public void subMenuFiltrosOperadores(ArrayList<OperadorLocal> operadores) {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Excelente! cuéntanos qué buscas:"               + "\n" +
                "1) Operador a cargo de eventos grandes (≥ 15 asistentes)"   + "\n" +
                "2) Operador a cargo de eventos pequeños (< 15 asistentes)"  + "\n" +
                "3) Buscar datos por nombre de Operador"                     + "\n" +
                "4) Mostrar operadores vigentes"                             + "\n" +
                "5) Mostrar operadores no vigentes"                          + "\n" +
                "6) Salir."
        );
        System.out.print("Elección deseada: ");


        int eleccionUsuario = sc.nextInt();


        sc.nextLine();

        switch (eleccionUsuario){
            case 1:
                mostrarOperadoresEventosGrandes(operadores);
                break;

            case 2:
                mostrarOperadoresEventosPequenos(operadores);
                break;

            case 3:

                buscarOperadoresPorFiltrado(operadores);
                break;

            case 4:
                mostrarOperadoresVigentes(operadores);
                break;

            case 5:
                mostrarOperadoresNoVigentes(operadores);
                break;

            case 6:
                System.out.println("Hasta luego.");
                break;

            default:
                System.out.println("¡Ups! ¡Esa opción no existe!");
        }
    }

    /**
     * Muestra todos los operadores registrados.
     *
     * @param operadores Lista de operadores a mostrar.
     */
    public void mostrarTodosLosOperadores(ArrayList<OperadorLocal> operadores) {

        if (operadores.isEmpty()) {
            System.out.println("No hay registros actuales");
        } else {
            System.out.println("↓↓↓ Listado de operadores registrados ↓↓↓");

            for (OperadorLocal operador : operadores) {
                System.out.println(operador);
            }
        }
    }

    /**
     * Muestra los operadores asociados a eventos con 15 o más asistentes.
     *
     * @param operadores Lista de operadores a revisar.
     */
    public void mostrarOperadoresEventosGrandes(ArrayList<OperadorLocal> operadores) {

        for (OperadorLocal operador : operadores) {

            if (operador.getServicioTuristico().getCantidadParticipantes() >= 15) {

                System.out.println(operador);

            }
        }
    }

    /**
     * Muestra los operadores asociados a eventos con menos de 15 asistentes.
     *
     * @param operadores Lista de operadores a revisar.
     */
    public void mostrarOperadoresEventosPequenos(ArrayList<OperadorLocal> operadores) {

        for (OperadorLocal operador : operadores) {

            if (operador.getServicioTuristico().getCantidadParticipantes() < 15) {

                System.out.println(operador);

            }
        }
    }

    /**
     * Busca operadores por nombre.
     *
     * @param operadores Lista de operadores disponibles.
     */
    public void buscarOperadoresPorFiltrado(ArrayList<OperadorLocal> operadores) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre del operador: ");
        String nombreOperador = sc.nextLine().trim().toLowerCase();

        boolean encontrado = false;

        if(nombreOperador.isEmpty()){
            System.out.println("No escribiste ningún dato de operador");
            System.out.println("Hasta luego.");
        } else{
            for (OperadorLocal operador : operadores) {

                if (operador.getNombre().toLowerCase().contains(nombreOperador)) {
                    System.out.println(operador);
                    encontrado = true;

                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron operadores con ese nombre.");
            }

        }

    }

    public void mostrarOperadoresVigentes(ArrayList<OperadorLocal> operadores) {

        boolean encontrado = false;

        for (OperadorLocal operador : operadores) {

            if (operador.isVigente()) {
                System.out.println(operador);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron operadores vigentes.");
        }
    }

    public void mostrarOperadoresNoVigentes(ArrayList<OperadorLocal> operadores) {

        boolean encontrado = false;

        for (OperadorLocal operador : operadores) {

            if (!operador.isVigente()) {
                System.out.println(operador);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron operadores no vigentes.");
        }
    }
}
