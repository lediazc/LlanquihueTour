package model;

public class RutaGastronomica extends ServicioTuristico{

    private int numeroDeParadas;

    public RutaGastronomica(String nombre, double duracionHoras, int numeroDeParadas, Direccion direccion, int cantidadParticipantes){
        super(nombre, duracionHoras, direccion, cantidadParticipantes);
        setNumeroDeParadas(numeroDeParadas);
    }

    public RutaGastronomica(){
        super();
        this.numeroDeParadas = 0;
    }

    //Getters
    /**
     * Obtiene la duración del servicio en horas.
     *
     * @return duración del servicio en horas.
     */
    public int getNumeroDeParadas(){
        return numeroDeParadas;
    }

    //Setters
    /**
     * Establece la duración del servicio en horas.
     *
     * @param numeroDeParadas Duración del servicio en horas.
     */
    public void setNumeroDeParadas(int numeroDeParadas) {
        if(numeroDeParadas == 0){

            System.out.println("El evento debe durar al menos una parada");

        }else{
            this.numeroDeParadas = Math.abs(numeroDeParadas);
        }

    }

    //Métodos de clase-----------------
    @Override
    public String mostrarResumen() {

        return "Ruta Gastronómica: "
                + getNombre();
    }

    @Override
    public String mostrarInformacion() {
        return
                "\n--- Ruta Gastronómica ---" +
                toString();
    }

    @Override
    public String toString() {
        return super.toString() +
                "Número de paradas: " + numeroDeParadas;
    }
}
