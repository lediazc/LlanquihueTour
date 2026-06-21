package app;

import service.MenuGeneral;

public class Main {

    /**
     * Punto de entrada principal del aplicativo.
     *
     * @param args Argumento de ejecución.
     */
    public static void main(String[] args) {
        MenuGeneral menuGeneral = new MenuGeneral();
        menuGeneral.mostrarMenu();

    }
}