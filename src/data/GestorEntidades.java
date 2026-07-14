package data;

import model.OperadorLocal;
import model.Registrable;
import model.Turista;

import java.util.ArrayList;

public class GestorEntidades {

    private GestorDatosOperador gestorOperador = new GestorDatosOperador();
    private GestorDatosTurista gestorTurista = new GestorDatosTurista();

    public ArrayList<Registrable> obtenerRegistros() {

        ArrayList<Registrable> registros = new ArrayList<>();

        registros.addAll(gestorOperador.leerOperadoresDesdeArchivo());

        registros.addAll(gestorTurista.leerTuristasDesdeArchivo());

        return registros;
    }


    public String recorrerRegistros() {

        ArrayList<Registrable> registros = obtenerRegistros();

        StringBuilder resultado = new StringBuilder();

        for (Registrable registro : registros) {

            if (registro instanceof OperadorLocal) {

                resultado.append("[OPERADOR LOCAL]\n");

            } else if (registro instanceof Turista) {

                resultado.append("[TURISTA]\n");
            }

            resultado.append(registro.mostrarResumen());
            resultado.append("\n----------------------------------------\n");
        }

        return resultado.toString();
    }


}