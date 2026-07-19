package data;

import model.*;

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
                        "1;ana torres;40478812-4;ana.torres@gmail.com;87654321;28;femenino;RutaGastronomica;tour volcán osorno;5.0;25;los alerces;oficina;123;5",
                        "2;carlos perez;1404636-4;carlos.perez@gmail.com;91234567;34;masculino;PaseoLacustre;travesía lago llanquihue;2.5;15;imperial;casa;456;catamarán",
                        "3;marcela rios;89522362-K;marcela.rios@gmail.com;98765432;22;femenino;ExcursionCultural;ruta ecuestre frutillar;3.0;12;los castaños;parcela;78;museo colonial",
                        "4;diego salinas;3978260-K;diego.salinas@gmail.com;99887766;41;masculino;RutaGastronomica;festival de sabores del mar;4.0;80;costanera;restaurant;210;8",
                        "5;paula fuentes;14590799-3;paula.fuentes@gmail.com;95544332;30;femenino;ExcursionCultural;expedición valle cochamó;6.0;20;río puelo;refugio;15;valle cochamó"
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

            ServicioTuristico servicio = turista.getServicioTuristico();
            Direccion direccion = servicio.getDireccion();

            String tipoServicioTuristico = servicio.getClass().getSimpleName();
            String datoExtra = "";

            if (servicio instanceof RutaGastronomica) {
                datoExtra = String.valueOf(((RutaGastronomica) servicio).getNumeroDeParadas());
            } else if (servicio instanceof PaseoLacustre) {
                datoExtra = ((PaseoLacustre) servicio).getTipoEmbarcacion();
            } else if (servicio instanceof ExcursionCultural) {
                datoExtra = ((ExcursionCultural) servicio).getLugarHistorico();
            }

            String linea = codigo                       + ";" +
                    turista.getNombre()                 + ";" +
                    turista.getRUT()                    + ";" +
                    turista.getCorreoElectronico()      + ";" +
                    turista.getNumeroTelefonico()       + ";" +
                    turista.getEdad()                   + ";" +
                    turista.getGenero()                 + ";" +
                    tipoServicioTuristico               + ";" +
                    servicio.getNombre()                + ";" +
                    servicio.getDuracionHoras()         + ";" +
                    servicio.getCantidadParticipantes() + ";" +
                    direccion.getCalle()                + ";" +
                    direccion.getEdificacion()          + ";" +
                    direccion.getNumeroHogar()          + ";" +
                    datoExtra;

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

                try {
                    String[] datos = linea.split(";");

                    if (datos.length == 15) {

                        String nombreTurista = datos[1].trim();
                        String rutTurista = datos[2].trim();
                        String correoTurista = datos[3].trim();
                        String telefonoTurista = datos[4].trim();
                        int edadTurista = Integer.parseInt(datos[5].trim());
                        String generoTurista = datos[6].trim();
                        String tipoServicioTuristico = datos[7].trim();
                        String nombreEvento = datos[8].trim();
                        double cantidadHoras = Double.parseDouble(datos[9].trim());
                        int cantidadParticipantes = Integer.parseInt(datos[10].trim());
                        String nombreCalle = datos[11].trim();
                        String edificacion = datos[12].trim();
                        String numeroHogar = datos[13].trim();
                        String datoExtra = datos[14].trim();

                        Direccion direccion = new Direccion(nombreCalle, edificacion, numeroHogar);

                        ServicioTuristico servicio;

                        switch (tipoServicioTuristico) {
                            case "RutaGastronomica":
                                servicio = new RutaGastronomica(nombreEvento, cantidadHoras, Integer.parseInt(datoExtra), direccion, cantidadParticipantes);
                                break;
                            case "PaseoLacustre":
                                servicio = new PaseoLacustre(nombreEvento, cantidadHoras, datoExtra, direccion, cantidadParticipantes);
                                break;
                            case "ExcursionCultural":
                                servicio = new ExcursionCultural(nombreEvento, cantidadHoras, datoExtra, direccion, cantidadParticipantes);
                                break;
                            default:
                                servicio = new ExcursionCultural();
                                break;
                        }

                        Turista turista = new Turista(
                                nombreTurista,
                                rutTurista,
                                correoTurista,
                                telefonoTurista,
                                edadTurista,
                                generoTurista,
                                servicio
                        );

                        turistas.add(turista);
                    } else {

                        System.out.println("Línea ignorada por formato corrupto: " + linea);

                    }
                } catch (NumberFormatException e) {

                    System.out.println("Línea ignorada por dato numérico inválido: " + linea);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al leer los turistas: " + e.getMessage());

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

    public void agregarTuristaYGuardar(Turista turista) {

        ArrayList<Turista> turistas = leerTuristasDesdeArchivo();

        turistas.add(turista);

        guardarTuristasEnArchivo(turistas);
    }

    public boolean editarTurista(
            int numeroRegistro,
            Turista turistaEditado
    ) {
        ArrayList<Turista> turistas =
                leerTuristasDesdeArchivo();

        int indice = numeroRegistro - 1;

        if (indice < 0 || indice >= turistas.size()) {
            return false;
        }

        turistas.set(indice, turistaEditado);
        guardarTuristasEnArchivo(turistas);

        return true;
    }
}