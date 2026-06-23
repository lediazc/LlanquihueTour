package service;

import model.Direccion;
import model.Evento;
import model.Turista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * Gestiona la creación, lectura y almacenamiento de turistas en archivos de texto.
 */
public class GestorDatosTurista {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoGestorTurista = Path.of("resources/gestorDatosTurista.txt");

    private FormularioTurista formulario = new FormularioTurista();


    /**
     * Crea la carpeta resources y el archivo de turistas con datos semilla si estos no existen previamente.
     */
    public void crearArchivoConDatosSemillaTurista() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoGestorTurista)) {

                List<String> datosSemilla = List.of(
                        "1;ana torres;ana.torres@gmail.com;87654321;28;femenino;tour volcán osorno;25;los alerces;oficina;123",
                        "2;carlos perez;carlos.perez@gmail.com;91234567;34;masculino;travesía lago llanquihue;15;imperial;casa;456",
                        "3;marcela rios;marcela.rios@gmail.com;98765432;22;femenino;ruta ecuestre frutillar;12;los castaños;parcela;78",
                        "4;diego salinas;diego.salinas@gmail.com;99887766;41;masculino;festival de sabores del mar;80;costanera;restaurant;210",
                        "5;paula fuentes;paula.fuentes@gmail.com;95544332;30;femenino;expedición valle cochamó;20;río puelo;refugio;15"
                );

                Files.write(archivoGestorTurista, datosSemilla);
                System.out.println("Archivo gestorDatosTurista.txt creado con datos semilla.");

            } else {

                System.out.println("El archivo ya existe: " + archivoGestorTurista.toAbsolutePath());

            }

        } catch (IOException e) {

            System.out.println("Error al crear carpeta o archivo: " + e.getMessage());

        }
    }

    /**
     * Guarda todos los turistas recibidos en el archivo gestorDatosTurista.txt.
     *
     * @param turistas Lista de turistas a guardar.
     */
    public void guardarTuristasEnArchivo(ArrayList<Turista> turistas) {

        ArrayList<String> lineas = new ArrayList<>();

        int codigo = 1;

        for (Turista turista : turistas) {

            Evento evento = turista.getEvento();
            Direccion direccion = evento.getDireccion();

            String linea = codigo + ";" +
                    turista.getNombre() + ";" +
                    turista.getCorreoElectronico() + ";" +
                    turista.getNumeroTelefonico() + ";" +
                    turista.getEdad() + ";" +
                    turista.getGenero() + ";" +
                    evento.getNombreEvento() + ";" +
                    evento.getCantidadParticipantes() + ";" +
                    direccion.getCalle() + ";" +
                    direccion.getEdificacion() + ";" +
                    direccion.getNumeroHogar();

            lineas.add(linea);
            codigo++;
        }

        try {
            Files.write(archivoGestorTurista, lineas);
            System.out.println("Turistas guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar turistas: " + e.getMessage());
        }
    }

    /**
     * Lee todos los turistas almacenados en el archivo y los carga en una colección ArrayList.
     *
     * @return Lista de turistas cargados desde el archivo.
     */
    public ArrayList<Turista> leerTuristasDesdeArchivo() {

        ArrayList<Turista> turistas = new ArrayList<>();

        crearArchivoConDatosSemillaTurista();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoGestorTurista.toFile()))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos.length == 11) {

                    String codigo = datos[0].trim();
                    String nombreTurista = datos[1].trim();
                    String correoTurista = datos[2].trim();
                    String telefonoTurista = datos[3].trim();
                    int edadTurista = Integer.parseInt(datos[4].trim());
                    String generoTurista = datos[5].trim();
                    String nombreEvento = datos[6].trim();
                    int cantidadParticipantes = Integer.parseInt(datos[7].trim());
                    String nombreCalle = datos[8].trim();
                    String edificacion = datos[9].trim();
                    String numeroHogar = datos[10].trim();

                    Direccion direccion = new Direccion(nombreCalle, edificacion, numeroHogar);

                    Evento evento = new Evento(nombreEvento, cantidadParticipantes, direccion);

                    Turista turista = new Turista(
                            nombreTurista,
                            correoTurista,
                            telefonoTurista,
                            edadTurista,
                            generoTurista,
                            evento
                    );

                    turistas.add(turista);

                } else {

                    System.out.println("Línea ignorada por formato corrupto: " + linea);

                }
            }

        } catch (IOException e) {

            System.out.println("Error al leer los turistas: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Error al convertir dato numérico: " + e.getMessage());

        }

        return turistas;
    }

    /**
     * Agrega un nuevo turista mediante formulario y posteriormente actualiza el archivo de almacenamiento.
     */
    public void agregarTuristaYGuardar() {

        ArrayList<Turista> turistas = leerTuristasDesdeArchivo();

        Turista nuevoTurista = formulario.agregarTurista();

        turistas.add(nuevoTurista);

        guardarTuristasEnArchivo(turistas);
    }
}