package service;

import model.Evento;
import model.Direccion;
import model.OperadorLocal;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class GestorDatos {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoGestorOperador = Path.of("resources/gestorDatosOperador.txt");

    public void crearArchivoConDatosSemillaSiNoExiste() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoGestorOperador)) {

                List<String> datosSemilla = List.of(
                        "1;jacobo benavides;true;jcob@gmail.com;234567989;guía turístico;puerto montt;tour volcán osorno;25;los alerces;oficina;123",
                        "2;maría gonzález;true;maria.gonzalez@gmail.com;987654321;kayak;puerto varas;travesía lago llanquihue;15;imperial;casa;456",
                        "3;pedro muñoz;true;pedro.munoz@gmail.com;912345678;cabalgatas;frutillar;ruta ecuestre frutillar;12;los castaños;parcela;78",
                        "4;camila soto;false;camila.soto@gmail.com;976543210;gastronomía;calbuco;festival de sabores del mar;80;costanera;restaurant;210",
                        "5;rodrigo pérez;false;rodrigo.perez@gmail.com;998877665;trekking;cochamó;expedición valle cochamó;20;río puelo;refugio;15",
                        "6;valentina rojas;true;valentina.rojas@gmail.com;955443322;navegación;maullín;navegación humedales de maullín;30;o'higgins;oficina;332"
                );

                Files.write(archivoGestorOperador, datosSemilla);
                System.out.println("Archivo gestorDatosOperador.txt creado con datos semilla.");

            } else {

                System.out.println(
                        "El archivo ya existe: " + archivoGestorOperador.toAbsolutePath()
                );

            }

        } catch (IOException e) {

            System.out.println(
                    "Error al crear carpeta o archivo: " + e.getMessage()
            );

        }
    }

    public ArrayList<OperadorLocal> leerOperadoresDesdeArchivo() {

        ArrayList<OperadorLocal> operadoresLocales = new ArrayList<>();

        crearArchivoConDatosSemillaSiNoExiste();

        try {

            List<String> lineas = Files.readAllLines(archivoGestorOperador);

            for (String linea : lineas) {

                String[] datos = linea.split(";");

                if (datos.length == 12) {

                    String codigo                = datos[0].trim(); //Se usará para próximos incrementos, si es que debo relacionar datos
                    String nombreOperador        = datos[1].trim();
                    boolean vigencia             = Boolean.parseBoolean(datos[2].trim());
                    String correoOperador        = datos[3].trim();
                    int telefonoOperador         = Integer.parseInt(datos[4].trim());
                    String tipoServicio          = datos[5].trim();
                    String comunaOperador        = datos[6].trim();
                    String nombreEvento          = datos[7].trim();
                    int numeroAsistenteEvento    = Integer.parseInt(datos[8].trim());
                    String nombreCalleDireccion  = datos[9].trim();
                    String edificacionDireccion  = datos[10].trim();
                    int numeroDireccion          = Integer.parseInt(datos[11].trim());

                    Direccion direccion = new Direccion(nombreCalleDireccion, edificacionDireccion, numeroDireccion);

                    Evento evento = new Evento(nombreEvento, numeroAsistenteEvento, direccion);

                    OperadorLocal operadorLocal = new OperadorLocal(nombreOperador, correoOperador, telefonoOperador, tipoServicio, comunaOperador, evento, vigencia);

                    operadoresLocales.add(operadorLocal);

                } else {

                    System.out.println(

                            "Linea ignorada por formato corrupto: " + linea

                    );
                }
            }

        } catch (IOException e) {

            System.out.println(

                    "Error al leer los operadores Locales: " + e.getMessage()

            );

        }

        return operadoresLocales;
    }
}
