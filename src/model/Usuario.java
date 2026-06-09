package model;


/**
 * Representa al Usuario general del aplicativo.
 * De él se desprenden todas las clases, tiene atributos cómo "Nombre y correoElectronico".
 */
public class Usuario {

    private String nombre;
    private String correoElectronico;


    /**
     * Constructor que permite crear un usuario con todos sus datos.
     *
     * @param nombre Nombre del usuario.
     * @param correoElectronico Dato de contacto del usuario.
     */
    public Usuario(String nombre, String correoElectronico){

        this.nombre = nombre;
        this.correoElectronico = correoElectronico;

    }

    /**
     * Constructor que permite crear un usuario con valores por defecto.
     */
    public Usuario(){

        this.nombre = "Sin nombre registrado";
        this.correoElectronico = "Sin correo electrónico registrado";

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


    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return correo electrónico registrado.
     */
    public String getCorreoElectronico() {

        return correoElectronico;
    }

    //Setters ----------------------------
    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }


    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correoElectronico Dato de contacto electrónico del usuario.
     */
    public void setCorreoElectronico(String correoElectronico) {

        this.correoElectronico = correoElectronico;
    }

}
