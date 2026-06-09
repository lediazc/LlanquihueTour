package model;

public class Turista extends Usuario {

    private int edad;
    private String genero;
    private Evento evento;

    public Turista(String nombre, String correoElectronico, int edad, String genero, Evento evento) {

        super(nombre, correoElectronico);
        setEdad(edad);
        this.genero = genero;
        this.evento = evento;

    }

    public Turista() {

        super();
        this.edad = 0;
        this.genero = "Prefiero no decir";
        this.evento = new Evento();

    }

    //Getters ----------------------------

    /**
     * Obtiene la edad del turista.
     *
     * @return Edad del turista.
     */
    public int getEdad() {

        return edad;
    }

    /**
     * Obtiene el género del turista.
     *
     * @return Género del turista.
     */
    public String getGenero() {

        return genero;
    }

    /**
     * Obtiene el evento de asistencia.
     *
     * @return Nombre del evento de asistencia.
     */
    public Evento getEvento() {

        return evento;
    }


    //Setters ----------------------------

    /**
     * Establece la edad del turista.
     *
     * @param edad Edad turista.
     */
    public void setEdad(int edad) {
        if(edad < 0){
            this.edad = 0;
        } else{
            this.edad = edad;
        }
    }

    /**
     * Establece el género(sexo) del turista.
     *
     * @param genero Género(sexo) del turista.
     */
    public void setGenero(String genero) {

        this.genero = genero;
    }

    /**
     * Establece el evento de asistencia.
     *
     * @param evento Nombre del evento de asistencia.
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
                "\n=== INFORMACIÓN DEL TURISTA ===\n" +
                "Nombre del turista: " + getNombre() + "\n" +
                "Correo electrónico: " + getCorreoElectronico() + "\n" +
                "Edad: " + getEdad() + "\n" +
                "Género: " + getGenero() + "\n" +
                "⬇ Evento inscrito ⬇ " + getEvento();
    }
}