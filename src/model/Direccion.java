package model;

public class Direccion {

    private String calle;
    private String edificacion; //Se puede indicar casa o edificio de condominio
    private String numeroHogar;


    /**
     * Constructor que permite crear una dirección con todos sus datos.
     *
     * @param calle Nombre de la calle.
     * @param edificacion Tipo de inmueble (Casa, casona, edificio, restaurant, protectora, etc).
     * @param numeroHogar Número de la edificación.
     */
    public Direccion(String calle, String edificacion, String numeroHogar ) {
        setCalle(calle);
        setEdificacion(edificacion);
        setNumeroHogar(numeroHogar);
    }

    /**
     * Constructor que permite crear una dirección con valores por defecto.
     */
    public Direccion() {
        this.calle = "No presenta calle registrada";
        this.edificacion = "Vive en casa o en condominio de 1 edificio";
        this.numeroHogar = "No proporcionó número de hogar";
    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre de la calle.
     *
     * @return Nombre de la calle registrada.
     */
    public String getCalle() {

        return calle;
    }

    /**
     * Obtiene el nombre del edificio.
     *
     * @return Nombre del edificio registrado.
     */
    public String getEdificacion() {

        return edificacion;
    }

    /**
     * Obtiene el número del edificio.
     *
     * @return Número del edificio.
     */
    public String getNumeroHogar() {

        return numeroHogar;
    }


    //Setters ----------------------------
    /**
     * Establece el nombre de la calle.
     *
     * @param calle Nombre de la calle a registrar.
     */
    public void setCalle(String calle) {
        if(calle== null || calle.trim().isEmpty()){

            this.calle = "No se proporcionó nombre de la calle";

        } else{

            this.calle = calle;

        }
    }

    /**
     * Establece el nombre del edificio.
     *
     * @param edificacion Nombre del edificio a registrar.
     */
    public void setEdificacion(String edificacion) {
        if(edificacion== null || edificacion.trim().isEmpty()){

            this.edificacion = "No se proporcionó nombre del edificio";

        } else{

            this.edificacion = edificacion;

        }
    }

    /**
     * Establece el número del edificio.
     *
     * @param numeroHogar Número del edificio a registrar.
     */
    public void setNumeroHogar(String numeroHogar) {
        if(numeroHogar== null || numeroHogar.trim().isEmpty()){

            this.numeroHogar = "No se proporcionó n° del edificio";

        } else{

            this.numeroHogar = numeroHogar;

        }
    }

    //Metodo toString de formateo -------------------------------------
    /**
     * Devuelve la dirección formateada.
     *
     * @return Cadena con toda la información de dirección
     */
    @Override
    public String toString() {

        return
                "\nCalle: " + getCalle() +
                "\nTipo de inmueble: " + getEdificacion() +
                "\nNúmero: " + getNumeroHogar();
    }

}
