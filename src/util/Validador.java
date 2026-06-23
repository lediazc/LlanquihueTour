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
}
