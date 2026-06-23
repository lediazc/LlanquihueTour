package service;

import model.Turista;

import java.util.ArrayList;

public class ConsultaTurista {

    /**
     * Muestra todos los turistas registrados.
     *
     * @param turistas Lista de turistas a mostrar.
     */
    public void mostrarTodosLosTuristas(ArrayList<Turista> turistas) {

        if (turistas.isEmpty()) {
            System.out.println("No hay registros actuales");
        } else {
            System.out.println("↓↓↓ Listado de turistas registrados ↓↓↓");

            for (Turista turista : turistas) {
                System.out.println(turista);
            }
        }
    }
}
