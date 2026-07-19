package model;

public class OperadorLocal extends Usuario {

    private String comuna; // Pueden ser: Puerto Montt, Puerto Varas,Frutillar, Llanquihue, Calbuco, Maullín, Fresia, Los Muermos y Cochamó
    private ServicioTuristico servicioTuristico;
    private boolean vigente;

    public OperadorLocal(String nombre, String rut, String correoElectronico, String numeroTelefonico, String comuna, ServicioTuristico servicioTuristico, boolean vigente){

        super(nombre, rut, correoElectronico, numeroTelefonico);
        setComuna(comuna);
        setServicioTuristico(servicioTuristico);
        setVigencia(vigente);

    }

    public OperadorLocal(){

        super();
        this.comuna = "No hay comuna registrada";
        this.servicioTuristico = new ExcursionCultural();
        this.vigente = false;

    }

    //Getters ----------------------------

    /**
     * Obtiene la comuna en donde se brinda el servicio.
     *
     * @return Nombre de la comuna del servicio.
     */
    public String getComuna() {

        return comuna;
    }

    /**
     * Obtiene el servicio turístico asociado al operador local.
     *
     * @return Objeto ServicioTuristico asignado al operador.
     */
    public ServicioTuristico getServicioTuristico() {

        return servicioTuristico;
    }

    /**
     * Obtiene el estado actual del operador local.
     *
     * @return El estado de la variable formateada en mensaje amigable al usuario.
     */
     public String getVigencia() {
        if(vigente){
            return "Vigente";
        } else {
            return "No vigente";
        }
    }

    /**
     * Obtiene el estado actual del operador local.
     *
     * @return El estado de la variable booleana vigencia.
     */
    public boolean isVigente() {

        return vigente;
    }


    //Setters ----------------------------

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
     * Establece el servicio turístico asociado al operador local.
     *
     * @param servicioTuristico Servicio turístico que realizará el operador local.
     */
    public void setServicioTuristico(ServicioTuristico servicioTuristico) {
        this.servicioTuristico = servicioTuristico;
    }

    /**
     * Establece el estado de vigencia del operador local.
     *
     * @param vigente Estado de vigencia del operador local.
     */
    public void setVigencia(boolean  vigente) {

        this.vigente = vigente;
    }

    //Métodos de clase -------------------------------------

    @Override
    public String mostrarResumen() {

        return "Operador: "+ getNombre() + " (comuna: "+ getComuna() + ")";
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
                super.toString() +
                "¿Operador presenta servicios vigentes?:" + getVigencia() + "\n" +
                "Comuna de operación: " + getComuna() + "\n" +
                "⬇ Información del evento asignado ⬇ " +
                getServicioTuristico();
    }

}
