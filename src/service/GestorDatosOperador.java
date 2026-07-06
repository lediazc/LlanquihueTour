package service;

import model.Direccion;
import model.OperadorLocal;
import model.ServicioTuristico;
import model.RutaGastronomica;
import model.PaseoLacustre;
import model.ExcursionCultural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;


/**
 * Gestiona la creación, lectura y almacenamiento de operadores locales en archivos de texto.
 */
public class GestorDatosOperador {

    private final Path carpetaResources = Path.of("resources");
    private final Path archivoGestorOperador = Path.of("resources/gestorDatosOperador.txt");

    private FormularioOperadorLocal formulario = new FormularioOperadorLocal();


    /**
     * Crea la carpeta resources y el archivo de operadores con datos semilla si estos no existen previamente.
     */
    public void crearArchivoConDatosSemillaOperadorLocal() {

        try {

            if (!Files.exists(carpetaResources)) {
                Files.createDirectories(carpetaResources);
                System.out.println("Carpeta resources creada.");
            }

            if (!Files.exists(archivoGestorOperador)) {

                List<String> datosSemilla = List.of(
                        "1;jacobo benavides;true;jcob@gmail.com;23456798;puerto montt;RutaGastronomica;tour volcán osorno;5.0;25;los alerces;oficina;123;5",
                        "2;maría gonzález;true;maria.gonzalez@gmail.com;98765432;puerto varas;PaseoLacustre;travesía lago llanquihue;2.5;15;imperial;casa;456;catamarán",
                        "3;pedro muñoz;true;pedro.munoz@gmail.com;91234567;frutillar;ExcursionCultural;ruta ecuestre frutillar;3.0;12;los castaños;parcela;78;museo colonial",
                        "4;camila soto;true;camila.soto@gmail.com;97654321;calbuco;RutaGastronomica;festival de sabores del mar;4.0;80;costanera;restaurant;210;8",
                        "5;rodrigo pérez;true;rodrigo.perez@gmail.com;99887766;cochamó;ExcursionCultural;expedición valle cochamó;6.0;20;río puelo;refugio;15;valle cochamó",
                        "6;valentina rojas;true;valentina.rojas@gmail.com;95544332;maullín;PaseoLacustre;navegación humedales de maullín;2.0;30;o'higgins;oficina;332;lancha"
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


    /**
     * Guarda todos los operadores recibidos en el archivo gestorDatosOperador.txt.
     * @param operadoresLocales Lista de operadores a guardar.
     */

    public void guardarOperadoresEnArchivo(ArrayList<OperadorLocal> operadoresLocales) {
        ArrayList<String> lineas = new ArrayList<>();

        int codigo = 1;


        for (OperadorLocal operador : operadoresLocales) {

            ServicioTuristico servicio = operador.getServicioTuristico();
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

            String linea = codigo                        + ";" +
                    operador.getNombre()                 + ";" +
                    operador.isVigente()                 + ";" +
                    operador.getCorreoElectronico()      + ";" +
                    operador.getNumeroTelefonico()       + ";" +
                    operador.getComuna()                 + ";" +
                    tipoServicioTuristico                + ";" +
                    servicio.getNombre()                 + ";" +
                    servicio.getDuracionHoras()          + ";" +
                    servicio.getCantidadParticipantes()  + ";" +
                    direccion.getCalle()                 + ";" +
                    direccion.getEdificacion()           + ";" +
                    direccion.getNumeroHogar()           + ";" +
                    datoExtra;

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


    /**
     * Agrega un nuevo operador local mediante formulario y posteriormente actualiza el archivo de almacenamiento.
     */
    public void agregarOperadorYGuardar() {
        ArrayList<OperadorLocal> operadoresLocales = leerOperadoresDesdeArchivo();

        OperadorLocal nuevoOperador = formulario.agregarOperadorLocal();

        operadoresLocales.add(nuevoOperador);

        guardarOperadoresEnArchivo(operadoresLocales);
    }


    /**
     * Lee todos los operadores almacenados en el archivo y los carga en una colección ArrayList.
     * @return Lista de operadores cargados desde el archivo.
     */
    public ArrayList<OperadorLocal> leerOperadoresDesdeArchivo() {

        ArrayList<OperadorLocal> operadoresLocales = new ArrayList<>();

        crearArchivoConDatosSemillaOperadorLocal();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoGestorOperador.toFile()))){

            String linea;

            while((linea = lector.readLine()) != null){

                String[] datos = linea.split(";");

                if (datos.length == 14) {

                    String codigo                = datos[0].trim(); //Se usará para próximos incrementos, si es que debo relacionar datos
                    String nombreOperador        = datos[1].trim();
                    boolean vigencia             = Boolean.parseBoolean(datos[2].trim());
                    String correoOperador        = datos[3].trim();
                    String telefonoOperador      = datos[4].trim();
                    String comunaOperador        = datos[5].trim();
                    String tipoServicioTuristico = datos[6].trim();
                    String nombreEvento          = datos[7].trim();
                    double cantidadHoras         = Double.parseDouble(datos[8].trim());
                    int numeroAsistenteEvento    = Integer.parseInt(datos[9].trim());
                    String nombreCalleDireccion  = datos[10].trim();
                    String edificacionDireccion  = datos[11].trim();
                    String numeroDireccion       = datos[12].trim();
                    String datoExtra             = datos[13].trim();

                    Direccion direccion = new Direccion(nombreCalleDireccion, edificacionDireccion, numeroDireccion);

                    ServicioTuristico servicio;

                    switch (tipoServicioTuristico) {
                        case "RutaGastronomica":
                            servicio = new RutaGastronomica(nombreEvento, cantidadHoras, Integer.parseInt(datoExtra), direccion, numeroAsistenteEvento);
                            break;

                        case "PaseoLacustre":
                            servicio = new PaseoLacustre(nombreEvento, cantidadHoras, datoExtra, direccion, numeroAsistenteEvento);
                            break;

                        case "ExcursionCultural":
                            servicio = new ExcursionCultural(nombreEvento, cantidadHoras, datoExtra, direccion, numeroAsistenteEvento);
                            break;

                        default:
                            servicio = new ExcursionCultural();
                            break;
                    }

                    OperadorLocal operadorLocal = new OperadorLocal(nombreOperador, correoOperador, telefonoOperador, comunaOperador, servicio, vigencia);
                    operadoresLocales.add(operadorLocal);

                } else {

                    System.out.println(

                            "Linea ignorada por formato corrupto: " + linea

                    );
                }
            }

        } catch (IOException e) {

            System.out.println("Error al leer los operadores Locales: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Error al convertir dato numérico: " + e.getMessage());

        }

        return operadoresLocales;
    }
}
