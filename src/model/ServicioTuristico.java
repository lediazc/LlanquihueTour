package model;

public abstract class ServicioTuristico {

    private String nombre;
    private double duracionHoras;
    private Direccion direccion;
    private int cantidadParticipantes;

    ServicioTuristico(String nombre, double duracionHoras, Direccion direccion, int cantidadParticipantes) {

        setNombre(nombre);
        setDuracionHoras(duracionHoras);
        setDireccion(direccion);
        setCantidadParticipantes(cantidadParticipantes);

    }

    public ServicioTuristico() {

        this.nombre = "Sin nombre registrado";
        this.duracionHoras = 0.0;
        this.direccion = new Direccion();
        this.cantidadParticipantes = 0;

    }

    //Getters

    /**
     * Obtiene el nombre del servicio.
     *
     * @return Nombre del servicio.
     */
    public String getNombre() {

        return nombre;

    }


    /**
     * Obtiene la duración del servicio en horas.
     *
     * @return duración del servicio en horas.
     */
    public double getDuracionHoras() {

        return duracionHoras;
    }

    /**
     * Obtiene la cantidad de participantes por evento.
     *
     * @return Cantidad de participantes por evento.
     */
    public int getCantidadParticipantes() {

        return cantidadParticipantes;
    }

    /**
     * Obtiene la dirección del evento.
     *
     * @return dirección del evento.
     */
    public Direccion getDireccion() {

        return direccion;
    }

    //Setters

    /**
     * Establece el nombre del servicio.
     *
     * @param nombre Nombre del servicio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Establece la duración del servicio en horas.
     *
     * @param duracionHoras Duración del servicio en horas.
     */
    public void setDuracionHoras(double duracionHoras) {
        if (duracionHoras <= 0) {
            this.duracionHoras = 0.0;
        } else {
            this.duracionHoras = duracionHoras;
        }
    }

    /**
     * Establece la cantidad de participantes del evento.
     *
     * @param cantidadParticipantes valor numérico de cantidad de turistas.
     */
    public void setCantidadParticipantes(int cantidadParticipantes) {
        if (cantidadParticipantes < 0) {
            this.cantidadParticipantes = 0;
        } else {
            this.cantidadParticipantes = cantidadParticipantes;
        }
    }

    /**
     * Establece la dirección del evento.
     *
     * @param direccion dirección del evento.
     */
    public void setDireccion(Direccion direccion) {

        this.direccion = direccion;
    }

    //Métodos de clase-----------------
    /**
     * Obtiene la información del servicio turístico.
     * Este método debe ser implementado por las subclases para
     * mostrar la información específica de cada tipo de servicio.
     *
     * @return Cadena con la información del servicio turístico.
     */
    public abstract String mostrarInformacion();

    @Override
    public String toString() {

        return
                "\nNombre del evento: " + getNombre() +
                "\nDirección del evento: " + getDireccion() +
                "\nDuración estimada: " + getDuracionHoras() +
                "\nCantidad de asistentes: " + getCantidadParticipantes() + "\n";
    }
}
