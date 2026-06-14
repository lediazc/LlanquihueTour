package data;

import model.Evento;
import model.Direccion;
import model.OperadorLocal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class GestorDatos {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoGestorOperador = Path.of("resources/gestorDatosOperador.txt");
    //private File FILE = new File("resources/gestorDatos.txt");

    public GestorDatos(){

        
    }

    public void crearArchivoConDatosSemillaSiNoExiste() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoGestorOperador)) {

                List<String> datosSemilla = List.of(
                        "1;Jacobo Benavides;jcob@gmail.com;234567989;Guía turístico;Puerto Montt;Tour Volcán Osorno;25;Los Alerces;Oficina;123",
                        "2;María González;maria.gonzalez@gmail.com;987654321;Kayak;Puerto Varas;Travesía Lago Llanquihue;15;Imperial;Casa;456",
                        "3;Pedro Muñoz;pedro.munoz@gmail.com;912345678;Cabalgatas;Frutillar;Ruta Ecuestre Frutillar;12;Los Castaños;Parcela;78",
                        "4;Camila Soto;camila.soto@gmail.com;976543210;Gastronomía;Calbuco;Festival de Sabores del Mar;80;Costanera;Restaurant;210",
                        "5;Rodrigo Pérez;rodrigo.perez@gmail.com;998877665;Trekking;Cochamó;Expedición Valle Cochamó;20;Río Puelo;Refugio;15",
                        "6;Valentina Rojas;valentina.rojas@gmail.com;955443322;Navegación;Maullín;Navegación Humedales de Maullín;30;O'Higgins;Oficina;332"
                );

                Files.write(archivoGestorOperador, datosSemilla);
                System.out.println("Archivo libros.txt creado con datos semilla.");

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

    public ArrayList<OperadorLocal> leerLibrosDesdeArchivo() {

        ArrayList<OperadorLocal> operadoresLocales = new ArrayList<>();

        crearArchivoConDatosSemillaSiNoExiste();

        try {

            List<String> lineas = Files.readAllLines(archivoGestorOperador);

            for (String linea : lineas) {

                String[] datos = linea.split(";");

                if (datos.length == 11) {

                    String codigo                = datos[0].trim();
                    String nombreOperador        = datos[1].trim();
                    String correoOperador        = datos[2].trim();
                    int telefonoOperador         = Integer.parseInt(datos[3].trim());
                    String tipoServicio          = datos[4].trim();
                    String comunaOperador        = datos[5].trim();
                    String nombreEvento          = datos[6].trim();
                    int numeroAsistenteEvento    = Integer.parseInt(datos[7].trim());
                    String nombreCalleDireccion  = datos[8].trim();
                    String edificacionDireccion  = datos[9].trim();
                    int numeroDireccion          = Integer.parseInt(datos[10].trim());

                    Direccion direccion = new Direccion(nombreCalleDireccion, edificacionDireccion, numeroDireccion);

                    Evento evento = new Evento(nombreEvento, numeroAsistenteEvento, direccion);

                    OperadorLocal operadorLocal = new OperadorLocal(nombreOperador, correoOperador, telefonoOperador, tipoServicio, comunaOperador, evento);

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
