package service;

import model.Evento;
import model.Direccion;
import model.OperadorLocal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDatosOperador {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoGestorOperador = Path.of("resources/gestorDatosOperador.txt");
    Scanner sc = new Scanner(System.in);

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

    public OperadorLocal  agregarOperadorLocal(){
        ArrayList<OperadorLocal> operadoresLocales = new ArrayList<>();
        OperadorLocal operadorLocalSingular;
        Evento evento;
        Direccion direccion;

        crearArchivoConDatosSemillaSiNoExiste();
        boolean vigencia = false;

        System.out.println("Agregemos un Operador Local");
        System.out.print("Digita el nombre del operador: ");
        String nombreOperador = sc.nextLine().trim().toLowerCase();

        System.out.print("Digita el correo del operador(xx@xx.cl): ");
        String correoOperador = sc.nextLine().trim().toLowerCase();

        System.out.print("Digita el número de contacto del operador(No agregar +569): ");
        String telefonoOperador = sc.nextLine().trim().toLowerCase();

        System.out.print("Que servicio presta el operador: ");
        String tipoServicio = sc.nextLine().trim().toLowerCase();
        System.out.print("En que comuna: ");
        String comuna = sc.nextLine().trim().toLowerCase();

        System.out.print("El operador cuenta con un evento asociado (Si/No) : ");
        String respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("yes") || respuesta.equals("y")) {

            System.out.print("Muy bien! indicanos el nombre del evento: ");
            String nombreEvento = sc.nextLine().trim().toLowerCase();
            System.out.print("El número de participantes del evento: ");
            int numeroParticipante = sc.nextInt();
            sc.nextLine();
            System.out.print("El nombre de la calle en donde es este evento: ");
            String nombreCalleEvento = sc.nextLine().trim().toLowerCase();
            System.out.print("El edificio tiene un nombre particular o es otro inmueble?: ");
            String tipoEdificioEvento = sc.nextLine().trim().toLowerCase();
            System.out.print("El número del edificio: ");
            int numeroEdificio = sc.nextInt();
            sc.nextLine();
            System.out.println("Excelente! Crearemos al operador con estado: Vigente");


            direccion = new Direccion(nombreCalleEvento, tipoEdificioEvento, numeroEdificio);
            evento = new Evento(nombreEvento, numeroParticipante, direccion);


        } else if (respuesta.equals("no") || respuesta.equals("n")) {

            System.out.println("Excelente! Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            evento = new Evento();

        } else {

            System.out.println("Respuesta inválida");
            System.out.println("Crearemos al operador con estado:No Vigente");

            direccion = new Direccion();
            evento = new Evento();

        }

        vigencia = respuesta.equals("si");

        return new OperadorLocal(nombreOperador, correoOperador, telefonoOperador, tipoServicio, comuna, evento, vigencia);

    }

    public void guardarOperadoresEnArchivo(ArrayList<OperadorLocal> operadoresLocales) {
        ArrayList<String> lineas = new ArrayList<>();

        int codigo = 1;

        for (OperadorLocal operador : operadoresLocales) {

            Evento evento = operador.getEvento();
            Direccion direccion = evento.getDireccion();

            String linea = codigo + ";" +
                    operador.getNombre() + ";" +
                    operador.getVigencia() + ";" +
                    operador.getCorreoElectronico() + ";" +
                    operador.getNumeroTelefonico() + ";" +
                    operador.getTipoServicio() + ";" +
                    operador.getComuna() + ";" +
                    evento.getNombreEvento() + ";" +
                    evento.getCantidadParticipantes() + ";" +
                    direccion.getCalle() + ";" +
                    direccion.getEdificacion() + ";" +
                    direccion.getNumeroHogar();

            lineas.add(linea);
            codigo++;
        }

        try {
            Files.write(archivoGestorOperador, lineas);
            System.out.println("Operadores guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar operadores: " + e.getMessage());
        }
    }

    public void agregarOperadorYGuardar() {
        ArrayList<OperadorLocal> operadoresLocales = leerOperadoresDesdeArchivo();

        OperadorLocal nuevoOperador = agregarOperadorLocal();

        operadoresLocales.add(nuevoOperador);

        guardarOperadoresEnArchivo(operadoresLocales);
    }

    public ArrayList<OperadorLocal> leerOperadoresDesdeArchivo() {

        ArrayList<OperadorLocal> operadoresLocales = new ArrayList<>();

        crearArchivoConDatosSemillaSiNoExiste();

        try {

            BufferedReader lector = new BufferedReader(new FileReader(archivoGestorOperador.toFile()));

            String linea;

            while((linea = lector.readLine()) != null){

                String[] datos = linea.split(";");

                if (datos.length == 12) {

                    String codigo                = datos[0].trim(); //Se usará para próximos incrementos, si es que debo relacionar datos
                    String nombreOperador        = datos[1].trim();
                    boolean vigencia             = Boolean.parseBoolean(datos[2].trim());
                    String correoOperador        = datos[3].trim();
                    String telefonoOperador      = datos[4].trim();
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
