package model;

public class ExcursionCultural extends ServicioTuristico{


    private String lugarHistorico;

    public ExcursionCultural(String nombre, double duracionHoras, String lugarHistorico){
        super(nombre, duracionHoras);
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
    public String toString() {
        return "\n--- Excursión Cultural ---\n" +
                super.toString() +
                "Lugar histórico a visitar: " + lugarHistorico;
    }
}
