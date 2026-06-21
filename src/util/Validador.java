package util;

public class Validador {
    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean numerosPositivos(int numero) {
        return numero > 0;
    }

    public static boolean telefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{8}");
    }
}
