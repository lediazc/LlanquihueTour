package data;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class GestorServicios {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoDatosServicios = Path.of("resources/gestorDatosServicios.txt");

    ArrayList<ServicioTuristico> servicios = new ArrayList<>();
    /**
     * Crea la carpeta resources y el archivo de servicios con datos semilla si estos no existen previamente.
     */
    public void crearArchivoConDatosSemillaServicios() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoDatosServicios)) {

                List<String> datosSemilla = List.of(
                        "1;RutaGastronomica;tour volcán osorno;5.0;25;los alerces;oficina;123;5",
                        "2;PaseoLacustre;travesía lago llanquihue;2.5;15;imperial;casa;456;catamarán",
                        "3;ExcursionCultural;ruta ecuestre frutillar;3.0;12;los castaños;parcela;78;museo colonial",
                        "4;RutaGastronomica;festival de sabores del mar;4.0;80;costanera;restaurant;210;8",
                        "5;ExcursionCultural;expedición valle cochamó;6.0;20;río puelo;refugio;15;valle cochamó",
                        "6;PaseoLacustre;navegación humedales de maullín;2.0;30;o'higgins;oficina;332;lancha",
                        "7;RutaGastronomica;Llanquihue ñam;4.5;6;Calle ñami;Restaurant;2134;6",
                        "8;PaseoLacustre;Lago siempre vivo;2.0;2;Lago siempre vivo;Caseta de guardia;423;lancha motorizada",
                        "9;ExcursionCultural;La historia de un hospital maldito;2.0;2;Avenida Nathan;Hospital;123;Hospital Brookhaven",
                        "10;RutaGastronomica;Almuerzo por la comuna;3.0;1;Calle Siempre viva;Edificio;2134;1",
                        "11;ExcursionCultural;Conociendo la Muni por dentro;2.0;2;Calle Siempre viva;Edificio;2134;Municipalidad Feliz"
                );

                Files.write(archivoDatosServicios, datosSemilla);
                System.out.println("Archivo gestorDatosServicios.txt creado con datos semilla.");

            } else {

                System.out.println(
                        "El archivo ya existe: " + archivoDatosServicios.toAbsolutePath()
                );

            }

        } catch (IOException e) {

            System.out.println(
                    "Error al crear carpeta o archivo: " + e.getMessage()
            );

        }
    }

    /**
     * Lee todos los servicios almacenados en el archivo y los carga en una colección ArrayList.
     */
    public void leerServiciosDesdeArchivos(){

        servicios.clear();

        crearArchivoConDatosSemillaServicios();

        try(BufferedReader lector = new BufferedReader(new FileReader(archivoDatosServicios.toFile()))){

            String linea;

            while ((linea = lector.readLine()) != null) {

                try {

                    String[] datos = linea.split(";");

                    if (datos.length == 9) {

                        String tipoServicioTuristico = datos[1].trim();
                        String nombreEvento = datos[2].trim();
                        double cantidadHoras = Double.parseDouble(datos[3].trim());
                        int cantidadParticipantes = Integer.parseInt(datos[4].trim());
                        String nombreCalleDireccion = datos[5].trim();
                        String edificacionDireccion = datos[6].trim();
                        String numeroDireccion = datos[7].trim();
                        String datoExtra = datos[8].trim();

                        Direccion direccion = new Direccion(
                                nombreCalleDireccion,
                                edificacionDireccion,
                                numeroDireccion
                        );

                        ServicioTuristico servicio;

                        switch (tipoServicioTuristico) {
                            case "RutaGastronomica":
                                servicio = new RutaGastronomica(
                                        nombreEvento,
                                        cantidadHoras,
                                        Integer.parseInt(datoExtra),
                                        direccion,
                                        cantidadParticipantes
                                );
                                break;

                            case "PaseoLacustre":
                                servicio = new PaseoLacustre(
                                        nombreEvento,
                                        cantidadHoras,
                                        datoExtra,
                                        direccion,
                                        cantidadParticipantes
                                );
                                break;

                            case "ExcursionCultural":
                                servicio = new ExcursionCultural(
                                        nombreEvento,
                                        cantidadHoras,
                                        datoExtra,
                                        direccion,
                                        cantidadParticipantes
                                );
                                break;

                            default:
                                System.out.println("Tipo de servicio desconocido. Línea ignorada: " + linea);
                                continue;
                        }

                        servicios.add(servicio);

                    } else {

                        System.out.println("Línea ignorada por formato corrupto: " + linea);
                    }

                } catch (NumberFormatException e) {

                    System.out.println("Línea ignorada por dato numérico inválido: " + linea);
                }
            }
        } catch (IOException e) {

            System.out.println("Error al leer los servicios: " + e.getMessage());

        }

    }

    /**
     * Lee la colección de servicios en el Arraylist.
     */
    public void leerLista(){

        for(ServicioTuristico iteracion : servicios){
            System.out.println(iteracion.mostrarInformacion());
        }
    }
}
