package model;

public class Evento {

    private String nombreEvento;
    private Direccion direccion;
    private int cantidadParticipantes;

    public Evento(String nombreEvento, int cantidadParticipantes, Direccion direccion){

        setNombreEvento(nombreEvento);
        this.direccion = direccion;
        setCantidadParticipantes(cantidadParticipantes);

    }

    public Evento(){

        this.nombreEvento = "Sin nombre de evento registrado";
        this.cantidadParticipantes = 0;
        this.direccion = new Direccion();


    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre del evento.
     *
     * @return Nombre del evento.
     */
    public String getNombreEvento() {

        return nombreEvento;
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

    //Setters ----------------------------
    /**
     * Establece el nombre del evento.
     *
     * @param nombreEvento Nombre del evento.
     */
    public void setNombreEvento(String nombreEvento) {
        if(nombreEvento== null || nombreEvento.trim().isEmpty()){

            this.nombreEvento = "No se proporcionó nombre del evento";

        } else{

            this.nombreEvento = nombreEvento;

        }
    }

    /**
     * Establece la cantidad de participantes del evento.
     *
     * @param cantidadParticipantes valor numérico de cantidad de turistas.
     */
    public void setCantidadParticipantes(int cantidadParticipantes) {
        if(cantidadParticipantes < 0){
            this.cantidadParticipantes = 0;
        } else{
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

    //Metodo toString de formateo -------------------------------------
    /**
     * Devuelve el evento formateado.
     *
     * @return Cadena formateada con la información del evento.
     */
    @Override
    public String toString() {

        return
                "\nNombre del evento: " + getNombreEvento() +
                "\nDirección del evento: " + getDireccion() +
                "\nCantidad de asistentes: " + getCantidadParticipantes();
    }



}
