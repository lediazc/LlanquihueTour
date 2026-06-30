package model;

public class PaseoLacustre extends ServicioTuristico{

    private String tipoEmbarcacion;

    public PaseoLacustre(String nombre, double duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        setTipoEmbarcacion(tipoEmbarcacion);

    }

    public PaseoLacustre(){
        super();
        this.tipoEmbarcacion = "Sin tipo de embarcación";
    }


    //Getters

    /**
     * Obtiene el tipo de embarcación.
     *
     * @return Tipo de embarcación.
     */
    public String getTipoEmbarcacion(){

        return tipoEmbarcacion;

    }



    //Setters

    /**
     * Establece el tipo de embarcación.
     *
     * @param tipoEmbarcacion tipo de embarcación.
     */
    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return "\n--- Paseo Lacustre ---\n" +
                super.toString() +
                "Tipo de Embarcación: " + tipoEmbarcacion;
    }
}
