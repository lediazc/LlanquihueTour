package model;

public class Turista extends Usuario  {

    private int edad;
    private String genero;
    private ServicioTuristico servicioTuristico;

    public Turista(String nombre, String correoElectronico, String numeroTelefonico, int edad, String genero, ServicioTuristico servicioTuristico) {

        super(nombre, correoElectronico, numeroTelefonico);
        setEdad(edad);
        setGenero(genero);
        setServicioTuristico(servicioTuristico);

    }

    public Turista() {

        super();
        this.edad = 0;
        this.genero = "Prefiero no decir";
        this.servicioTuristico = new ExcursionCultural();

    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public ServicioTuristico getServicioTuristico() {
        return servicioTuristico;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            this.edad = 0;
        } else {
            this.edad = edad;
        }
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            this.genero = "No se proporcionó ningún genero";
        } else {
            this.genero = genero;
        }
    }

    public void setServicioTuristico(ServicioTuristico servicioTuristico) {
        this.servicioTuristico = servicioTuristico;
    }

    //Métodos de clase -------------------------------------
    @Override
    public String mostrarResumen() {

        return "Turista: " + getNombre() + " (" + getEdad() + " años)";
    }

    @Override
    public String toString() {

        return
                "\n=== INFORMACIÓN DEL TURISTA ===\n" +
                        super.toString() +
                        "Edad: " + getEdad() + "\n" +
                        "Género: " + getGenero() + "\n" +
                        "⬇ Servicio inscrito ⬇ " + "\n" +
                        getServicioTuristico();
    }
}