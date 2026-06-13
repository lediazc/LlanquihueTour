package ui;

import model.Direccion;
import model.Evento;
import model.OperadorLocal;
import model.Turista;


public class Main {
    public static void main(String[] args) {

        //Relacionado a eventoLosSaltosDelAlerce
        Direccion direccionLosSaltosDelAlerce = new Direccion("Traumen", "Oficina", 1190);
        Evento eventoLosSaltosDelAlerce = new Evento("Los saltos del Alerce", 30 , direccionLosSaltosDelAlerce);
        //Operador a cargo
        OperadorLocal operadorLocalLosSaltosDelAlerce = new OperadorLocal("Jacobo Benavides","jcobBen@gmail.com", "Guía turístico", "Puerto Montt", eventoLosSaltosDelAlerce);
        //Turista
        Turista turistaLosSaltosDelAlerce = new Turista("Rocío Mena", "ROMENA@gmail.com", 30,"Femenino", eventoLosSaltosDelAlerce);

        //Relacionado a eventoRutaGastronomicaDelLago
        Direccion direccionRutaGastronomicaDelLago = new Direccion("Costanera Vicente Pérez Rosales", "Restaurant", 245);
        Evento eventoRutaGastronomicaDelLago = new Evento("Ruta Gastronómica del Lago", 18 , direccionRutaGastronomicaDelLago);
        //Operador a cargo
        OperadorLocal operadorLocalRutaGastronomicaDelLago = new OperadorLocal("María González","mgonzalez@gmail.com", "Gastronomía", "Puerto Varas", eventoRutaGastronomicaDelLago);
        //Turista
        Turista turistaRutaGastronomicaDelLago = new Turista("Felipe Soto", "fsoto@gmail.com", 42,"Masculino", eventoRutaGastronomicaDelLago);

        //Relacionado a eventoNavegacionLagoLlanquihue
        Direccion direccionNavegacionLagoLlanquihue = new Direccion("Avenida Costanera", "Muelle", 88);
        Evento eventoNavegacionLagoLlanquihue = new Evento("Navegación Lago Llanquihue", 25 , direccionNavegacionLagoLlanquihue);
        //Operador a cargo
        OperadorLocal operadorLocalNavegacionLagoLlanquihue = new OperadorLocal("Carlos Fuentes","cfuentes@gmail.com", "Navegación", "Llanquihue", eventoNavegacionLagoLlanquihue);
        //Turista
        Turista turistaNavegacionLagoLlanquihue = new Turista("Valentina Rojas", "vrojas@gmail.com", 27,"Femenino", eventoNavegacionLagoLlanquihue);


        System.out.println(operadorLocalLosSaltosDelAlerce);
        System.out.println(turistaLosSaltosDelAlerce);
        System.out.println();
        System.out.println("----------------------------");
        System.out.println(operadorLocalRutaGastronomicaDelLago);
        System.out.println(turistaRutaGastronomicaDelLago);
        System.out.println();
        System.out.println("----------------------------");
        System.out.println(operadorLocalNavegacionLagoLlanquihue);
        System.out.println(turistaNavegacionLagoLlanquihue);
    }
}