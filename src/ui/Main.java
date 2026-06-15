package ui;
import data.GestorDatos;
import model.OperadorLocal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestorDatos gestor = new GestorDatos();


        ArrayList<OperadorLocal> gestorOperadores = gestor.leerOperadoresDesdeArchivo();


        System.out.println("Bienvenido al Gestor de Personal de Llanquihue Tour");
        System.out.println("Por favor, seleccione una de las siguientes opciones: "       + "\n" +
                           "1) Mostrarme todos los registros del personal."                + "\n" +
                           "2) Mostrarme sólo los resultados, en base a datos filtrados."  + "\n" +
                           "3) Salir."
                );
        System.out.print("Elección deseada: ");

        int eleccionUsuario = sc.nextInt();

        switch (eleccionUsuario){
            case 1:
                mostrarTodosLosOperadores(gestorOperadores);
                break;
            case 2:
                subMenuFiltros(gestorOperadores);
                break;

            case 3:
                System.out.println("Hasta luego.");
                break;

            default:
                System.out.println("¡Ups! ¡Esa opción no existe!");
        }

        sc.close();
    }

    public static void mostrarTodosLosOperadores(ArrayList<OperadorLocal> operadores) {

        if (operadores.isEmpty()) {
            System.out.println("No hay registros actuales");
        } else {
            System.out.println("↓↓↓ Listado de operadores registrados ↓↓↓");

            for (OperadorLocal operador : operadores) {
                System.out.println(operador);
            }
        }
    }

    public static void subMenuFiltros(ArrayList<OperadorLocal> operadores) {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Excelente! cuéntanos qué buscas:"               + "\n" +
                "1) Operador a cargo de eventos grandes (≥ 15 asistentes)"   + "\n" +
                "2) Operador a cargo de eventos pequeños (< 15 asistentes)"  + "\n" +
                "3) Buscar datos por nombre de Operador"                     + "\n" +
                "4) Salir."
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
                System.out.println("Hasta luego.");
                break;

            default:
                System.out.println("¡Ups! ¡Esa opción no existe!");
        }
    }

    public static void mostrarOperadoresEventosGrandes(ArrayList<OperadorLocal> operadores) {

        for (OperadorLocal operador : operadores) {

            if (operador.getEvento().getCantidadParticipantes() >= 15) {

                System.out.println(operador);

            }
        }
    }

    public static void mostrarOperadoresEventosPequenos(ArrayList<OperadorLocal> operadores) {

        for (OperadorLocal operador : operadores) {

            if (operador.getEvento().getCantidadParticipantes() < 15) {

                System.out.println(operador);

            }
        }
    }

    public static void buscarOperadoresPorFiltrado(ArrayList<OperadorLocal> operadores) {

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
}