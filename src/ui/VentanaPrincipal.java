package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import data.GestorDatosOperador;
import data.GestorDatosTurista;
import data.GestorServicios;

public class VentanaPrincipal extends JFrame {

    //Instancias
    GestorDatosOperador gestorOp = new GestorDatosOperador();
    GestorDatosTurista gestorTur = new GestorDatosTurista();
    GestorServicios gestorServ = new GestorServicios();

    //Creamos los elementos relacionados con el input de patente del vehículo
    JTextField plateInput = new JTextField(6);
    JLabel bienvenidaLabel = new JLabel("Bienvenido al Gestor de Personal de Llanquihue Tour");


    JButton botonMostrarTodosOperadores = new JButton("Mostrarme todos los registros de operadores");
    JButton botonFiltrarOperadores = new JButton("Filtrar registros de operadores");
    JButton botonAgregarOperador = new JButton("Agregar Operador Local");
    JButton botonMostrarTodosTuristas = new JButton("Mostrarme todos los registros de turistas");
    JButton botonAgregarTurista = new JButton("Agregar turista");
    JButton botonMostrarTodosServicios = new JButton("Visualizar todos los servicios registrados");

    JTextArea areaResultado = new JTextArea(25, 100);

    //FormularioOperador
    private final JTextField campoNombreOperador = new JTextField(20);
    private final JTextField campoCorreoOperador = new JTextField(20);
    private final JTextField campoTelefonoOperador = new JTextField(20);
    private final JTextField campoComunaOperador = new JTextField(20);

    private final JButton botonGuardarOperador = new JButton("Guardar operador");
    private final JButton botonVolverMenu = new JButton("Volver");



    //Construcción de maquetado
    private final JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
    private final JPanel panelCabecera = new JPanel();
    private final JPanel panelCentral = new JPanel(new GridLayout(1,2,10,10));

    private final CardLayout layoutPanelIzquierdo = new CardLayout();
    private final JPanel panelIzquierdo = new JPanel(layoutPanelIzquierdo);
    private final JPanel panelMenuIzquierdo = new JPanel(new GridLayout(3, 1, 10, 10));
    private final JPanel panelFormularioOperador = new JPanel(new GridLayout(5, 2, 10, 10));

    private final JPanel panelFormularioTurista = new JPanel();

    private final JPanel panelDerecho = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel panelResultado = new JPanel(new BorderLayout());
    private final JPanel panelBotonesResultados = new JPanel(new GridLayout(4,1,10,10));


    public VentanaPrincipal(){

        inicializarDocumentos();
        construirLayout();
        agregarComponentes();
        registrarEventos();
        configurarVentana();

    }

    public void inicializarDocumentos(){
        //Verificar/crear archivos al iniciar el programa
        gestorOp.crearArchivoConDatosSemillaOperadorLocal();
        gestorTur.crearArchivoConDatosSemillaTurista();
        gestorServ.crearArchivoConDatosSemillaServicios();
    }

    private void construirLayout() {
        panelCentral.add(panelIzquierdo);
        panelCentral.add(panelDerecho);

        panelDerecho.add(panelResultado);
        panelDerecho.add(panelBotonesResultados);

        panelIzquierdo.add(panelMenuIzquierdo, "MENU");
        panelIzquierdo.add(panelFormularioOperador, "FORMULARIO_OPERADOR");
        panelIzquierdo.add(panelFormularioTurista, "FORMULARIO_TURISTA");

        panelPrincipal.add(panelCabecera, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);




        add(panelPrincipal);
    }

    private void configurarVentana() {
        setTitle("Llanquihue Tour");
        setSize(1250,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarComponentes() {
        panelCabecera.add(bienvenidaLabel);

        panelMenuIzquierdo.add(botonAgregarOperador);
        panelMenuIzquierdo.add(botonAgregarTurista);

        construirFormularioOperador();
        //construirFormularioTurista();
        panelResultado.add(areaResultado);


        panelBotonesResultados.add(botonMostrarTodosOperadores);
        panelBotonesResultados.add(botonFiltrarOperadores);
        panelBotonesResultados.add(botonMostrarTodosTuristas);
        panelBotonesResultados.add(botonMostrarTodosServicios);
    }


    private void registrarEventos() {
        botonAgregarOperador.addActionListener(e -> {
            layoutPanelIzquierdo.show(panelIzquierdo,"FORMULARIO_OPERADOR");

            bienvenidaLabel.setText("Agreguemos un Operador Local");

        });

        botonAgregarTurista.addActionListener(e ->layoutPanelIzquierdo.show(panelIzquierdo,"FORMULARIO_TURISTA"));

        botonVolverMenu.addActionListener(e -> {
            layoutPanelIzquierdo.show(panelIzquierdo, "MENU");
            bienvenidaLabel.setText(
                    "Bienvenido al Gestor de Personal de Llanquihue Tour"
            );
        });

    }

    private void construirFormularioOperador() {
        panelFormularioOperador.add(new JLabel("Nombre del operador:"));
        panelFormularioOperador.add(campoNombreOperador);

        panelFormularioOperador.add(new JLabel("Correo del operador:"));
        panelFormularioOperador.add(campoCorreoOperador);

        panelFormularioOperador.add(new JLabel("Número de contacto:"));
        panelFormularioOperador.add(campoTelefonoOperador);

        panelFormularioOperador.add(new JLabel("Comuna donde tiene actividad:"));
        panelFormularioOperador.add(campoComunaOperador);

        panelFormularioOperador.add(botonGuardarOperador);
        panelFormularioOperador.add(botonVolverMenu);
    }
}
