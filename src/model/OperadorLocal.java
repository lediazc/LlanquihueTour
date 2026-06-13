package model;

public class OperadorLocal extends Usuario{

    private String tipoServicio; // Pueden ser: Navegación, Kayak, Cabalgatas, Trekking, Gastronomía, etc.
    private String comuna; // Pueden ser: Puerto Montt, Puerto Varas,Frutillar, Llanquihue, Calbuco, Maullín, Fresia, Los Muermos y Cochamó
    private Evento evento;

    public OperadorLocal(String nombre, String correoElectronico, String tipoServicio, String comuna, Evento evento){

        super(nombre, correoElectronico);
        setTipoServicio(tipoServicio);
        setComuna(comuna);
        this.evento = evento;
    }

    public OperadorLocal(){

        super();
        this.tipoServicio = "No hay servicio registrado";
        this.comuna = "No hay comuna registrada";
        this.evento = new Evento();

    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre del tipo de servicio.
     *
     * @return Nombre del servicio.
     */
    public String getTipoServicio() {

        return tipoServicio;
    }

    /**
     * Obtiene la comuna en donde se brinda el servicio.
     *
     * @return Nombre de la comuna del servicio.
     */
    public String getComuna() {

        return comuna;
    }

    /**
     * Obtiene el evento en donde se brinda el servicio.
     *
     * @return Nombre del evento del servicio.
     */
    public Evento getEvento() {

        return evento;
    }


    //Setters ----------------------------
    /**
     * Establece el nombre del tipo de servicio.
     *
     * @param tipoServicio Nombre del servicio.
     */
    public void setTipoServicio(String tipoServicio) {
        if(tipoServicio== null || tipoServicio.trim().isEmpty()){

            this.tipoServicio = "No se proporcionó Tipo de servicio";

        } else{

            this.tipoServicio = tipoServicio;

        }
    }

    /**
     * Establece la comuna en donde opera el operador local.
     *
     * @param comuna Nombre de la comuna del servicio.
     */
    public void setComuna(String comuna) {
        if(comuna== null || comuna.trim().isEmpty()){

            this.comuna = "No se proporcionó comuna";

        } else{

            this.comuna = comuna;

        }
    }

    /**
     * Establece el evento del operador Local.
     *
     * @param evento Nombre del evento del operador local.
     */
    public void setEvento(Evento evento) {

        this.evento = evento;
    }

    //Metodo toString de formateo -------------------------------------
    /**
     * Devuelve el usuario formateado.
     *
     * @return Cadena formateada con la información del propietario y su dirección.
     */
    @Override
    public String toString() {

        return
                "\n=== INFORMACIÓN DEL OPERADOR LOCAL ===\n" +
                "Nombre del operador: " + getNombre() + "\n" +
                "Correo electrónico: " + getCorreoElectronico() + "\n" +
                "Tipo de servicio ofrecido: " + getTipoServicio() + "\n" +
                "Comuna de operación: " + getComuna() + "\n" +
                "⬇ Información del evento asignado ⬇ " + getEvento();
    }

}
