package model;

public class ExcursionCultural extends ServicioTuristico{


    private String lugarHistorico;

    public ExcursionCultural(String nombre, double duracionHoras, String lugarHistorico, Direccion direccion){
        super(nombre, duracionHoras, direccion);
        setLugarHistorico(lugarHistorico);
    }

    public ExcursionCultural(){
        super();
        this.lugarHistorico = "Sin lugar histórico registrado";
    }

    //Getters

    /**
     * Obtiene el nombre de lugarHistorico.
     *
     * @return nombre de lugarHistorico.
     */
    public String getLugarHistorico(){

        return lugarHistorico;

    }



    //Setters

    /**
     * Establece el nombre de lugarHistorico.
     *
     * @param lugarHistorico nombre de lugarHistorico.
     */
    public void setLugarHistorico(String lugarHistorico) {

        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public String mostrarInformacion() {
        return "\n--- Excursión Cultural ---" +
                toString();
    }

    @Override
    public String toString() {
        return super.toString() +
                "Lugar histórico a visitar: " + lugarHistorico;
    }

}


