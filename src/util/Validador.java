package util;

/**
 * Contiene métodos estáticos para validar distintos tipos de datos ingresados por el usuario.
 */
public class Validador {

    /**
     * Verifica que un texto no sea nulo ni vacío.
     * @param texto Texto a validar.
     * @return true si el texto es válido; false en caso contrario.
     */
    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Verifica que un número sea mayor que cero.
     * @param numero Número a validar.
     * @return true si el número es positivo; false en caso contrario.
     */
    public static boolean numerosPositivos(int numero) {

        return numero > 0;
    }

    public static boolean numerosPositivos(double numero) {

        return numero > 0;
    }

    /**
     * Verifica que un número telefónico tenga exactamente ocho dígitos.
     * @param telefono Número telefónico a validar.
     * @return true si el teléfono es válido; false en caso contrario.
     */
    public static boolean telefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{8}");
    }


    /**
     * Verifica que un correo electrónico cumpla con un formato válido.
     * @param correo Correo electrónico a validar.
     * @return true si el correo es válido; false en caso contrario.
     */
    public static boolean correoValido(String correo) {
        return correo != null && correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean cedulaIdentidadValida(String cedulaIdentidad){
        return cedulaIdentidad != null && cedulaIdentidad.matches("^[0-9]{7,8}-[0-9Kk]$");
    }

    public static boolean numeroVerificadorValido(String cedulaIdentidad){
        int suma = 0;
        int localizadorGuion = cedulaIdentidad.length() - 2;

        String cuerpoRut = cedulaIdentidad.substring(0, localizadorGuion);
        String cuerpoRutInv = new StringBuilder(cuerpoRut).reverse().toString();
        String numeroVerificador = cedulaIdentidad.substring(cedulaIdentidad.length()-1);



        for(int i = 0; i <cuerpoRutInv.length(); i++){
            char digito = cuerpoRutInv.charAt(i);

            int digitoInt = Character.getNumericValue(digito);

            int factor = (i % 6) + 2;
            int multiplicacion = digitoInt * factor;

            suma += multiplicacion;

        }

        int restoCuerpoRUT = suma % 11;

        int digitoVerificadorBruto = 11 - restoCuerpoRUT;


        String digitoVerificadorBrutoString = String.valueOf(digitoVerificadorBruto);

        if(digitoVerificadorBrutoString.equalsIgnoreCase("10")){
            digitoVerificadorBrutoString = "k";
        }else if(digitoVerificadorBrutoString.equalsIgnoreCase("11")){
            digitoVerificadorBrutoString = "0";
        }

        return digitoVerificadorBrutoString.equalsIgnoreCase(numeroVerificador);
    }

    public static boolean rutValido(String rut) {
        return cedulaIdentidadValida(rut)
                && numeroVerificadorValido(rut);
    }
}
