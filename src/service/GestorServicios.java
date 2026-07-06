package service;

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
     * Crea la carpeta resources y el archivo de operadores con datos semilla si estos no existen previamente.
     */
    public void crearArchivoConDatosSemillaOperadorLocal() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoDatosServicios)) {

                List<String> datosSemilla = List.of(
                    "1;RutaGastronomica;Llanquihue ñam;4.5;Calle ñami;Restaurant;2134;6",
                    "2;PaseoLacustre;Lago siempre vivo;2.0;Lago siempre vivo;Caseta de guardia;423;lancha motorizada",
                    "3;ExcursionCultural;La historia de un hospital maldito;2.0;Avenida Nathan;Hospital;123;Hospital Brookhaven",
                    "4;RutaGastronomica;Almuerzo por la comuna;3.0;Calle Siempre viva;Edificio;2134;1",
                    "5;ExcursionCultural;Conociendo la Muni por dentro;2.0;Calle Siempre viva;Edificio;2134;Municipalidad Feliz"
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

    public void leerServiciosDesdeArchivos(){

        servicios.clear();

        crearArchivoConDatosSemillaOperadorLocal();

        try(BufferedReader lector = new BufferedReader(new FileReader(archivoDatosServicios.toFile()))){

            String linea;

            while((linea = lector.readLine()) != null){

                String[] datos = linea.split(";");
                    if (datos.length == 8) {
                        //1;RutaGastronomica;Llanquihue ñam;4.5;Calle ñami;Restaurant;2134;6",
                        String id = datos[0].trim();
                        String tipoServicioTuristico = datos[1].trim();
                        String nombreEvento = datos[2].trim();
                        double cantidadHoras = Double.parseDouble(datos[3]);
                        String nombreCalleDireccion = datos[4].trim();
                        String edificacionDireccion = datos[5].trim();
                        String numeroDireccion = datos[6].trim();
                        String datoExtra = datos[7].trim();

                        Direccion direccion = new Direccion(nombreCalleDireccion, edificacionDireccion, numeroDireccion);

                        ServicioTuristico servicio;

                        switch (tipoServicioTuristico) {
                            case "RutaGastronomica":
                                servicio = new RutaGastronomica(nombreEvento, cantidadHoras, Integer.parseInt(datoExtra), direccion);
                                break;

                            case "PaseoLacustre":
                                servicio = new PaseoLacustre(nombreEvento, cantidadHoras, datoExtra, direccion);
                                break;

                            case "ExcursionCultural":
                                servicio = new ExcursionCultural(nombreEvento, cantidadHoras, datoExtra, direccion);
                                break;

                            default:
                                servicio = new ExcursionCultural();
                                break;
                        }

                        servicios.add(servicio);

                    } else {

                System.out.println(

                        "Linea ignorada por formato corrupto: " + linea

                );
            }

            }
        } catch (IOException e) {

            System.out.println("Error al leer los servicios: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Error al convertir dato numérico: " + e.getMessage());

        }

    }

    public void leerLista(){

        for(ServicioTuristico iteracion : servicios){
            System.out.println(iteracion.mostrarInformacion());
        }
    }
}
