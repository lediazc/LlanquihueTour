package model;

public class RutaGastronomica extends ServicioTuristico{

    private int numeroDeParadas;

    public RutaGastronomica(String nombre, double duracionHoras, int numeroDeParadas, Direccion direccion){
        super(nombre, duracionHoras, direccion);
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
