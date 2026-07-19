package model;


/**
 * Representa al Usuario general del aplicativo.
 * De él se desprenden todas las clases, tiene atributos cómo "Nombre y correoElectronico".
 */
public class Usuario implements Registrable{

    private String nombre;
    private String rut;
    private String correoElectronico;
    private String numeroTelefonico;


    /**
     * Constructor que permite crear un usuario con todos sus datos.
     *
     * @param nombre Nombre del usuario.
     * @param correoElectronico Dato de contacto del usuario.
     */
    public Usuario(String nombre, String rut, String correoElectronico, String numeroTelefonico){

        setNombre(nombre);
        setRUT(rut);
        setCorreoElectronico(correoElectronico);
        setNumeroTelefonico(numeroTelefonico);
    }

    /**
     * Constructor que permite crear un usuario con valores por defecto.
     */
    public Usuario(){

        this.nombre = "Sin nombre registrado";
        this.rut = "";
        this.correoElectronico = "Sin correo electrónico registrado";
        this.numeroTelefonico = "Sin número de teléfono registrado";

    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {

        return nombre;
    }

    //Getters ----------------------------
    /**
     * Obtiene el RUT del usuario.
     *
     * @return RUT del usuario.
     */
    public String getRUT() {

        return rut;
    }


    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return correo electrónico registrado.
     */
    public String getCorreoElectronico() {

        return correoElectronico;
    }

    /**
     * Obtiene el número telefónico del usuario en formato String.
     *
     * @return número registrado.
     */
    public String getNumeroTelefonico() {

        return numeroTelefonico;

    }


    //Setters ----------------------------
    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            this.nombre = "No se proporcionó ningún nombre";
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Establece el RUT del usuario.
     *
     * @param rut Nombre del usuario.
     */
    public void setRUT(String rut) {
        if(rut == null || rut.trim().isEmpty()){
            this.rut = "No se proporcionó ningún RUT";
        } else {
            this.rut = rut;
        }
    }


    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correoElectronico Dato de contacto electrónico del usuario.
     */
    public void setCorreoElectronico(String correoElectronico) {
        if(correoElectronico== null || correoElectronico.trim().isEmpty()){
            this.correoElectronico = "No se proporcionó ningún correo de contacto";
        } else {
            this.correoElectronico = correoElectronico;
        }
    }

    /**
     * Establece el número telefónico del usuario.
     *
     * @param numeroTelefonico Dato de número telefónico del usuario.
     */
    public void setNumeroTelefonico(String numeroTelefonico) {

        if(numeroTelefonico == null || numeroTelefonico.trim().isEmpty()){
            this.numeroTelefonico = "11111111";
        } else {
            this.numeroTelefonico = numeroTelefonico;
        }

    }

    //Metodos  -------------------------------------
    @Override
    public String mostrarResumen() {
        return "Usuario: " + getNombre();
    }

    @Override
    public String toString() {
        return
                "Nombre: " + getNombre() + "\n" +
                "RUT: " + getRUT() + "\n" +
                "Correo electrónico: " + getCorreoElectronico() + "\n" +
                "Número de contacto: +569" + getNumeroTelefonico() + "\n";
    }
}
