package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import data.GestorDatosOperador;
import data.GestorDatosTurista;
import data.GestorServicios;
import data.FormularioOperadorLocal;
import model.OperadorLocal;
import data.FormularioTurista;
import model.Turista;
import util.Validador;
import model.Direccion;
import model.ServicioTuristico;
import model.RutaGastronomica;
import model.PaseoLacustre;
import model.ExcursionCultural;


public class VentanaPrincipal extends JFrame {

    //Instancias
    GestorDatosOperador gestorOp = new GestorDatosOperador();
    GestorDatosTurista gestorTur = new GestorDatosTurista();
    GestorServicios gestorServ = new GestorServicios();
    FormularioOperadorLocal formularioOperador = new FormularioOperadorLocal();
    FormularioTurista formularioTurista = new FormularioTurista();


    //Creamos los elementos relacionados con el input de patente del vehículo
    JTextField plateInput = new JTextField(6);
    JLabel bienvenidaLabel = new JLabel("Bienvenido al Gestor de Personal de Llanquihue Tour");


    JButton botonMostrarTodosOperadores = new JButton("Mostrarme todos los registros de operadores");
    JButton botonFiltrarOperadores = new JButton("Filtrar registros de operadores");
    JButton botonAgregarOperador = new JButton("Agregar Operador Local");
    JButton botonMostrarTodosTuristas = new JButton("Mostrarme todos los registros de turistas");
    JButton botonAgregarTurista = new JButton("Agregar turista");
    JButton botonMostrarTodosServicios = new JButton("Visualizar todos los servicios registrados");

    ImageIcon icono = new ImageIcon(getClass().getResource("/icono.png"));
    JLabel logo = new JLabel(icono);

    JTextArea areaResultado = new JTextArea(25, 100);

    //FormularioOperador
    private final JTextField campoNombreOperador = new JTextField(20);
    private final JTextField campoCorreoOperador = new JTextField(20);
    private final JTextField campoTelefonoOperador = new JTextField(20);
    private final JTextField campoComunaOperador = new JTextField(20);
    private final JRadioButton rbEventoOperadorSi = new JRadioButton("Sí");
    private final JRadioButton rbEventoOperadorNo = new JRadioButton("No");

    private final JButton botonGuardarOperador = new JButton("Guardar operador");
    private final JButton botonVolverMenu = new JButton("Volver");

    //FormularioTurista
    private final JTextField campoNombreTurista = new JTextField(20);
    private final JTextField campoCorreoTurista =  new JTextField(20);
    private final JTextField campoTelefonoTurista = new JTextField(20);
    private final JTextField campoEdadTurista = new JTextField(20);

    private final JComboBox<String> comboGeneroTurista =
            new JComboBox<>(new String[]{
                    "Masculino",
                    "Femenino",
                    "Otro"
            });

    private final JRadioButton rbEventoTuristaSi = new JRadioButton("Sí");
    private final JRadioButton rbEventoTuristaNo = new JRadioButton("No");
    private final JButton botonGuardarTurista = new JButton("Guardar turista");
    private final JButton botonVolverMenuTurista = new JButton("Volver");


    // Formulario Evento

    private enum OrigenEvento { OPERADOR,TURISTA}

    private OrigenEvento origenEvento;

    private final JTextField campoNombreEvento = new JTextField(20);
    private final JTextField campoDuracionEvento = new JTextField(20);
    private final JTextField campoParticipantesEvento = new JTextField(20);
    private final JTextField campoCalleEvento = new JTextField(20);
    private final JTextField campoTipoEdificioEvento = new JTextField(20);
    private final JTextField campoNumeroEdificioEvento = new JTextField(20);

    private final JComboBox<String> comboTipoEvento =
            new JComboBox<>(new String[]{
                    "Ruta Gastronómica",
                    "Paseo Lacustre",
                    "Excursión Cultural"
            });


    private final JButton botonGuardarEvento = new JButton("Guardar evento");

    private final JButton botonVolverOperador = new JButton("Volver");

    // Componentes específicos de cada tipo de evento

    private final CardLayout layoutDetalleEvento = new CardLayout();

    private final JPanel panelDetalleEvento = new JPanel(layoutDetalleEvento);

    private final JPanel panelRutaGastronomica = new JPanel(new FlowLayout());

    private final JPanel panelPaseoLacustre = new JPanel(new FlowLayout());

    private final JPanel panelExcursionCultural =  new JPanel(new FlowLayout());

    private final JTextField campoNumeroParadas = new JTextField(15);

    private final JTextField campoTipoEmbarcacion = new JTextField(15);

    private final JTextField campoLugarHistorico = new JTextField(15);

    //Construcción de maquetado
    private final JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
    private final JPanel panelCabecera = new JPanel();
    private final JPanel panelCentral = new JPanel(new GridLayout(1,2,10,10));

    private final CardLayout layoutPanelIzquierdo = new CardLayout();
    private final JPanel panelIzquierdo = new JPanel(layoutPanelIzquierdo);
    private final JPanel panelMenuIzquierdo = new JPanel(new GridLayout(3, 1, 10, 10));
    private final JPanel panelFormularioOperador = new JPanel(new GridLayout(7, 1, 10, 10));

    private final JPanel panelFormularioTurista = new JPanel(new GridLayout(7, 1, 10, 10));

    private final JPanel panelFormularioEvento = new JPanel(new GridLayout(9, 1, 10, 10));

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

        panelIzquierdo.add(panelFormularioEvento,"FORMULARIO_EVENTO");

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

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonAgregarOperador);
        panelBotones.add(botonAgregarTurista);
        panelMenuIzquierdo.add(panelBotones);
        panelMenuIzquierdo.add(logo);

        construirFormularioOperador();
        construirFormularioTurista();
        construirFormularioEvento();


        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        panelResultado.add(scrollResultado, BorderLayout.CENTER);


        panelBotonesResultados.add(botonMostrarTodosOperadores);
        panelBotonesResultados.add(botonFiltrarOperadores);
        panelBotonesResultados.add(botonMostrarTodosTuristas);
        panelBotonesResultados.add(botonMostrarTodosServicios);
    }


    private void registrarEventos() {
        botonAgregarOperador.addActionListener(e -> {

            limpiarFormularioOperador();

            layoutPanelIzquierdo.show(panelIzquierdo, "FORMULARIO_OPERADOR");

            bienvenidaLabel.setText("Agreguemos un Operador Local");
        });

        botonAgregarTurista.addActionListener(e -> {
            layoutPanelIzquierdo.show(panelIzquierdo,"FORMULARIO_TURISTA");

            bienvenidaLabel.setText("Agreguemos un Turista");
        });

        botonVolverMenu.addActionListener(e -> {
            layoutPanelIzquierdo.show(panelIzquierdo, "MENU");

            bienvenidaLabel.setText("Bienvenido al Gestor de Personal de Llanquihue Tour");
        });

        botonVolverMenuTurista.addActionListener(e -> {
            layoutPanelIzquierdo.show(panelIzquierdo, "MENU");

            bienvenidaLabel.setText("Bienvenido al Gestor de Personal de Llanquihue Tour");
        });

        botonGuardarOperador.addActionListener(e -> {

            if (!validarDatosOperador()) {
                return;
            }

            if (rbEventoOperadorSi.isSelected()) {

                origenEvento = OrigenEvento.OPERADOR;

                layoutPanelIzquierdo.show(panelIzquierdo, "FORMULARIO_EVENTO");

                bienvenidaLabel.setText("Complete los datos del evento.");

            } else if (rbEventoOperadorNo.isSelected()) {

                crearOperadorSinEvento();

            } else {

                JOptionPane.showMessageDialog(this, "Debes indicar si el operador tiene un evento asociado.", "Dato obligatorio", JOptionPane.WARNING_MESSAGE);
            }
        });

        comboTipoEvento.addActionListener(e -> {

            String tipoSeleccionado = (String) comboTipoEvento.getSelectedItem();

            if ("Ruta Gastronómica".equals(tipoSeleccionado)) {

                layoutDetalleEvento.show(panelDetalleEvento,"RUTA");

            } else if ("Paseo Lacustre".equals(tipoSeleccionado)) {

                layoutDetalleEvento.show(panelDetalleEvento,"PASEO");

            } else if ("Excursión Cultural".equals(tipoSeleccionado)) {

                layoutDetalleEvento.show( panelDetalleEvento,"EXCURSION");
            }
        });

        botonGuardarEvento.addActionListener(e -> {

            if (!validarDatosEvento()) {
                return;
            }

            try {

                ServicioTuristico servicio = crearServicioTuristico();

                if (origenEvento == OrigenEvento.OPERADOR) {

                    OperadorLocal operador = formularioOperador.agregarOperadorLocal(
                            campoNombreOperador.getText(),
                            campoCorreoOperador.getText(),
                            campoTelefonoOperador.getText(),
                            campoComunaOperador.getText(),
                            servicio
                    );

                    gestorOp.agregarOperadorYGuardar(operador);

                    areaResultado.setText(operador.toString());

                    JOptionPane.showMessageDialog(this, "Operador registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } else if (origenEvento == OrigenEvento.TURISTA) {

                    int edad = Integer.parseInt(campoEdadTurista.getText().trim());
                    String genero = (String) comboGeneroTurista.getSelectedItem();

                    Turista turista = formularioTurista.agregarTurista(
                            campoNombreTurista.getText(),
                            campoCorreoTurista.getText(),
                            campoTelefonoTurista.getText(),
                            edad,
                            genero,
                            servicio
                    );

                    gestorTur.agregarTuristaYGuardar(turista);

                    areaResultado.setText(turista.toString());

                    JOptionPane.showMessageDialog(this, "Turista registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }

                layoutPanelIzquierdo.show(panelIzquierdo, "MENU");
                bienvenidaLabel.setText("Bienvenido al Gestor de Personal de Llanquihue Tour");

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonGuardarTurista.addActionListener(e -> {

            if (!validarDatosTurista()) {
                return;
            }

            if (rbEventoTuristaSi.isSelected()) {

                origenEvento = OrigenEvento.TURISTA;

                layoutPanelIzquierdo.show(panelIzquierdo, "FORMULARIO_EVENTO");

                bienvenidaLabel.setText("Complete los datos del evento.");

            } else if (rbEventoTuristaNo.isSelected()) {

                crearTuristaSinEvento();

            } else {

                JOptionPane.showMessageDialog(this, "Debes indicar si el turista tiene un evento asociado.", "Dato obligatorio", JOptionPane.WARNING_MESSAGE);

            }

        });

        botonVolverOperador.addActionListener(e -> {

            if (origenEvento == OrigenEvento.OPERADOR) {
                layoutPanelIzquierdo.show(panelIzquierdo, "FORMULARIO_OPERADOR");
                bienvenidaLabel.setText("Agreguemos un Operador Local");

            } else if (origenEvento == OrigenEvento.TURISTA) {
                layoutPanelIzquierdo.show(panelIzquierdo, "FORMULARIO_TURISTA");
                bienvenidaLabel.setText("Agreguemos un Turista");
            }
        });

        botonMostrarTodosOperadores.addActionListener(e -> mostrarTodosOperadores());

        botonMostrarTodosTuristas.addActionListener(e -> mostrarTodosTuristas());

        botonMostrarTodosServicios.addActionListener(e -> mostrarTodosServicios());

        botonFiltrarOperadores.addActionListener(e -> filtrarOperadores());
    }

    private void construirFormularioOperador() {

        JPanel panelNombre = new JPanel(new FlowLayout());

        panelNombre.add(new JLabel("Nombre del operador:"));
        panelNombre.add(campoNombreOperador);

        JPanel panelCorreo = new JPanel(new FlowLayout());

        panelCorreo.add(new JLabel("Correo del operador:"));
        panelCorreo.add(campoCorreoOperador);

        JPanel panelTelefono = new JPanel(new FlowLayout());

        panelTelefono.add(new JLabel("Número de contacto (No agregar +569):"));
        panelTelefono.add(campoTelefonoOperador);

        JPanel panelComuna = new JPanel(new FlowLayout());

        panelComuna.add(new JLabel("Comuna donde tiene actividad:"));
        panelComuna.add(campoComunaOperador);

        JPanel panelBotones = new JPanel(new FlowLayout());

        panelBotones.add(botonGuardarOperador);
        panelBotones.add(botonVolverMenu);

        ButtonGroup grupoServicios = new ButtonGroup();

        JPanel panelcheckbox = new JPanel(new FlowLayout());
        panelcheckbox.add(new JLabel("¿El operador cuenta con un evento asociado?"));
        grupoServicios.add(rbEventoOperadorSi);
        grupoServicios.add(rbEventoOperadorNo);
        panelcheckbox.add(rbEventoOperadorSi);
        panelcheckbox.add(rbEventoOperadorNo);


        panelFormularioOperador.add(panelNombre);
        panelFormularioOperador.add(panelCorreo);
        panelFormularioOperador.add(panelTelefono);
        panelFormularioOperador.add(panelComuna);
        panelFormularioOperador.add(panelcheckbox);
        panelFormularioOperador.add(panelBotones);
    }

    private void construirFormularioTurista() {

        JPanel panelNombre = new JPanel(new FlowLayout());

        panelNombre.add(new JLabel("Digita el nombre del turista:"));
        panelNombre.add(campoNombreTurista);

        JPanel panelCorreo = new JPanel(new FlowLayout());

        panelCorreo.add(new JLabel("Correo del turista(xx@xx.cl):"));
        panelCorreo.add(campoCorreoTurista);

        JPanel panelTelefono = new JPanel(new FlowLayout());

        panelTelefono.add(new JLabel("Número de contacto (No agregar +569):"));
        panelTelefono.add(campoTelefonoTurista);

        JPanel panelComuna = new JPanel(new FlowLayout());

        panelComuna.add(new JLabel("Edad:"));
        panelComuna.add(campoEdadTurista);

        JPanel panelGenero = new JPanel(new FlowLayout());

        panelGenero.add(new JLabel("Género:"));
        panelGenero.add(comboGeneroTurista);

        JPanel panelBotones = new JPanel(new FlowLayout());

        panelBotones.add(botonGuardarTurista);
        panelBotones.add(botonVolverMenuTurista);

        ButtonGroup grupoServicios = new ButtonGroup();

        JPanel panelcheckbox = new JPanel(new FlowLayout());
        panelcheckbox.add(new JLabel("¿El turista cuenta con un evento asociado?"));
        grupoServicios.add(rbEventoTuristaSi);
        grupoServicios.add(rbEventoTuristaNo);
        panelcheckbox.add(rbEventoTuristaSi);
        panelcheckbox.add(rbEventoTuristaNo);


        panelFormularioTurista.add(panelNombre);
        panelFormularioTurista.add(panelCorreo);
        panelFormularioTurista.add(panelTelefono);
        panelFormularioTurista.add(panelComuna);
        panelFormularioTurista.add(panelGenero);
        panelFormularioTurista.add(panelcheckbox);
        panelFormularioTurista.add(panelBotones);
    }

    private void construirFormularioEvento() {

        JPanel panelTipo = new JPanel(new FlowLayout());
        panelTipo.add(new JLabel("Tipo de evento:"));
        panelTipo.add(comboTipoEvento);

        JPanel panelNombre = new JPanel(new FlowLayout());
        panelNombre.add(new JLabel("Nombre del evento:"));
        panelNombre.add(campoNombreEvento);

        JPanel panelDuracion = new JPanel(new FlowLayout());
        panelDuracion.add(new JLabel("Duración en horas:"));
        panelDuracion.add(campoDuracionEvento);

        JPanel panelParticipantes = new JPanel(new FlowLayout());
        panelParticipantes.add(new JLabel("Número de participantes:"));
        panelParticipantes.add(campoParticipantesEvento);

        JPanel panelCalle = new JPanel(new FlowLayout());
        panelCalle.add(new JLabel("Calle del evento:"));
        panelCalle.add(campoCalleEvento);

        JPanel panelEdificio = new JPanel(new FlowLayout());
        panelEdificio.add(new JLabel("Tipo de edificio o inmueble:"));
        panelEdificio.add(campoTipoEdificioEvento);

        JPanel panelNumero = new JPanel(new FlowLayout());
        panelNumero.add(new JLabel("Número del edificio:"));
        panelNumero.add(campoNumeroEdificioEvento);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonGuardarEvento);
        panelBotones.add(botonVolverOperador);

        panelRutaGastronomica.add( new JLabel("Número de paradas:"));
        panelRutaGastronomica.add(campoNumeroParadas);

        panelPaseoLacustre.add(new JLabel("Tipo de embarcación:"));
        panelPaseoLacustre.add(campoTipoEmbarcacion);

        panelExcursionCultural.add(new JLabel("Lugar histórico:"));
        panelExcursionCultural.add(campoLugarHistorico);

        panelDetalleEvento.add(panelRutaGastronomica,"RUTA");

        panelDetalleEvento.add(panelPaseoLacustre,"PASEO");

        panelDetalleEvento.add( panelExcursionCultural,"EXCURSION");

        panelFormularioEvento.add(panelTipo);
        panelFormularioEvento.add(panelNombre);
        panelFormularioEvento.add(panelDuracion);
        panelFormularioEvento.add(panelParticipantes);
        panelFormularioEvento.add(panelCalle);
        panelFormularioEvento.add(panelEdificio);
        panelFormularioEvento.add(panelNumero);
        panelFormularioEvento.add(panelDetalleEvento);
        panelFormularioEvento.add(panelBotones);
    }

    private boolean validarDatosOperador() {

        String nombre = campoNombreOperador.getText().trim();
        String correo = campoCorreoOperador.getText().trim().toLowerCase();
        String telefono = campoTelefonoOperador.getText().trim();
        String comuna = campoComunaOperador.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,"El nombre del operador no puede estar vacío.","Dato inválido",JOptionPane.WARNING_MESSAGE);

            campoNombreOperador.requestFocusInWindow();
            return false;
        }

        if (!Validador.correoValido(correo)) {
            JOptionPane.showMessageDialog(this,"El correo no respeta el formato solicitado.","Dato inválido",JOptionPane.WARNING_MESSAGE);

            campoCorreoOperador.requestFocusInWindow();
            return false;
        }

        if (!Validador.telefonoValido(telefono)) {
            JOptionPane.showMessageDialog(this,"El teléfono debe tener exactamente 8 dígitos.","Dato inválido",JOptionPane.WARNING_MESSAGE);

            campoTelefonoOperador.requestFocusInWindow();
            return false;
        }

        if (comuna.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Debes indicar una comuna.","Dato inválido",JOptionPane.WARNING_MESSAGE);

            campoComunaOperador.requestFocusInWindow();
            return false;
        }

        return true;
    }

    private void crearOperadorSinEvento() {

        try {

            OperadorLocal operador = formularioOperador.agregarOperadorLocal(
                    campoNombreOperador.getText(),
                    campoCorreoOperador.getText(),
                    campoTelefonoOperador.getText(),
                    campoComunaOperador.getText()
            );

            gestorOp.agregarOperadorYGuardar(operador);

            areaResultado.setText(operador.toString());

            JOptionPane.showMessageDialog(this, "Operador creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean validarDatosEvento() {

        String nombreEvento = campoNombreEvento.getText().trim();
        String duracionTexto = campoDuracionEvento.getText().trim().replace(",", ".");
        String participantesTexto = campoParticipantesEvento.getText().trim();
        String calle = campoCalleEvento.getText().trim();
        String edificio = campoTipoEdificioEvento.getText().trim();
        String numero = campoNumeroEdificioEvento.getText().trim();

        if (nombreEvento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del evento no puede estar vacío.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            double duracion = Double.parseDouble(duracionTexto);

            if (!Validador.numerosPositivos(duracion)) {
                JOptionPane.showMessageDialog(this, "La duración debe ser un número positivo.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número válido.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int participantes = Integer.parseInt(participantesTexto);

            if (!Validador.numerosPositivos(participantes)) {
                JOptionPane.showMessageDialog(this, "La cantidad de participantes debe ser positiva.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad de participantes debe ser un número entero.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (calle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes indicar una calle.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (edificio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes indicar un tipo de edificio o inmueble.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes indicar el número del edificio.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String tipoEvento = (String) comboTipoEvento.getSelectedItem();

        if ("Ruta Gastronómica".equals(tipoEvento)) {

            try {
                int numeroParadas = Integer.parseInt(campoNumeroParadas.getText().trim());

                if (!Validador.numerosPositivos(numeroParadas)) {
                    JOptionPane.showMessageDialog(this, "El número de paradas debe ser positivo.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de paradas debe ser un número entero.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } else if ("Paseo Lacustre".equals(tipoEvento)) {

            if (campoTipoEmbarcacion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes indicar el tipo de embarcación.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } else if ("Excursión Cultural".equals(tipoEvento)) {

            if (campoLugarHistorico.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes indicar el lugar histórico.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private ServicioTuristico crearServicioTuristico() {

        String tipoEvento = (String) comboTipoEvento.getSelectedItem();

        String nombreEvento = campoNombreEvento.getText().trim();
        double duracion = Double.parseDouble(
                campoDuracionEvento.getText().trim().replace(",", ".")
        );

        int participantes = Integer.parseInt(
                campoParticipantesEvento.getText().trim()
        );

        Direccion direccion = new Direccion(
                campoCalleEvento.getText().trim(),
                campoTipoEdificioEvento.getText().trim(),
                campoNumeroEdificioEvento.getText().trim()
        );

        switch (tipoEvento) {

            case "Ruta Gastronómica":
                int numeroParadas = Integer.parseInt(
                        campoNumeroParadas.getText().trim()
                );

                return new RutaGastronomica(
                        nombreEvento,
                        duracion,
                        numeroParadas,
                        direccion,
                        participantes
                );

            case "Paseo Lacustre":
                return new PaseoLacustre(
                        nombreEvento,
                        duracion,
                        campoTipoEmbarcacion.getText().trim(),
                        direccion,
                        participantes
                );

            case "Excursión Cultural":
                return new ExcursionCultural(
                        nombreEvento,
                        duracion,
                        campoLugarHistorico.getText().trim(),
                        direccion,
                        participantes
                );

            default:
                throw new IllegalArgumentException(
                        "El tipo de evento seleccionado no es válido."
                );
        }
    }

    private void limpiarFormularioOperador() {

        campoNombreOperador.setText("");
        campoCorreoOperador.setText("");
        campoTelefonoOperador.setText("");
        campoComunaOperador.setText("");

        rbEventoOperadorSi.setSelected(false);
        rbEventoOperadorNo.setSelected(false);

        campoNombreEvento.setText("");
        campoDuracionEvento.setText("");
        campoParticipantesEvento.setText("");
        campoCalleEvento.setText("");
        campoTipoEdificioEvento.setText("");
        campoNumeroEdificioEvento.setText("");

        campoNumeroParadas.setText("");
        campoTipoEmbarcacion.setText("");
        campoLugarHistorico.setText("");

        comboTipoEvento.setSelectedIndex(0);
    }

    private boolean validarDatosTurista() {

        String nombre = campoNombreTurista.getText().trim();
        String correo = campoCorreoTurista.getText().trim().toLowerCase();
        String telefono = campoTelefonoTurista.getText().trim();
        String edadTexto = campoEdadTurista.getText().trim();
        String genero = (String) comboGeneroTurista.getSelectedItem();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del turista no puede estar vacío.");
            return false;
        }

        if (!Validador.correoValido(correo)) {
            JOptionPane.showMessageDialog(this, "El correo no respeta el formato solicitado.");
            return false;
        }

        if (!Validador.telefonoValido(telefono)) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener exactamente 8 dígitos.");
            return false;
        }

        try {

            int edad = Integer.parseInt(edadTexto);

            if (!Validador.numerosPositivos(edad)) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número positivo.");
                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "La edad debe ser un número entero.");
            return false;
        }

        if (genero == null || genero.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes indicar un género.");
            return false;
        }

        return true;
    }

    private void crearTuristaSinEvento() {

        try {

            int edad = Integer.parseInt(campoEdadTurista.getText().trim());

            String genero = (String) comboGeneroTurista.getSelectedItem();

            Turista turista = formularioTurista.agregarTurista(
                    campoNombreTurista.getText(),
                    campoCorreoTurista.getText(),
                    campoTelefonoTurista.getText(),
                    edad,
                    genero
            );

            gestorTur.agregarTuristaYGuardar(turista);

            areaResultado.setText(turista.toString());

            JOptionPane.showMessageDialog(this, "Turista creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarTodosOperadores() {

        ArrayList<OperadorLocal> operadores = gestorOp.leerOperadoresDesdeArchivo();

        StringBuilder resultado = new StringBuilder();

        for (OperadorLocal operador : operadores) {

            resultado.append(operador.toString());
            resultado.append("\n----------------------------------------\n");
        }

        areaResultado.setText(resultado.toString());
    }

    private void mostrarTodosTuristas() {

        ArrayList<Turista> turistas = gestorTur.leerTuristasDesdeArchivo();

        StringBuilder resultado = new StringBuilder();

        for (Turista turista : turistas) {

            resultado.append(turista.toString());
            resultado.append("\n----------------------------------------\n");
        }

        areaResultado.setText(resultado.toString());
    }

    private void mostrarTodosServicios() {

        ArrayList<OperadorLocal> operadores = gestorOp.leerOperadoresDesdeArchivo();
        ArrayList<Turista> turistas = gestorTur.leerTuristasDesdeArchivo();

        StringBuilder resultado = new StringBuilder();

        resultado.append("===== SERVICIOS DE OPERADORES =====\n\n");

        for (OperadorLocal operador : operadores) {

            resultado.append(operador.getServicioTuristico());
            resultado.append("\n----------------------------------------\n");
        }

        resultado.append("\n===== SERVICIOS DE TURISTAS =====\n\n");

        for (Turista turista : turistas) {

            resultado.append(turista.getServicioTuristico());
            resultado.append("\n----------------------------------------\n");
        }

        areaResultado.setText(resultado.toString());
    }
}
