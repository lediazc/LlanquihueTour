# # LlanquihueTour

## Descripción

LlanquihueTour es una aplicación desarrollada en Java. El objetivo del proyecto es modelar la gestión de actividades turísticas en la provincia de Llanquihue, permitiendo representar operadores locales, turistas, eventos y direcciones mediante clases relacionadas entre sí.

Durante el desarrollo se aplicaron conceptos fundamentales de POO como encapsulamiento, herencia, composición, constructores, getters, setters y sobrescritura del método `toString()`.

---

## Estructura de paquetes y clases
### Paquete `model`

#### Usuario
Clase base del sistema que almacena información común de los usuarios:

Atributos principales:
* Nombre
* Correo electrónico

#### OperadorLocal
Clase que hereda de `Usuario` y representa a los operadores turísticos.

Atributos principales:
* Tipo de servicio
* Comuna
* Evento asociado

#### Turista
Clase que hereda de `Usuario` y representa a los asistentes de las actividades turísticas.

Atributos principales:
* Edad
* Género
* Evento asociado

#### Evento
Representa una actividad turística.

Atributos principales:
* Nombre del evento
* Cantidad de participantes
* Dirección asociada

#### Direccion
Representa la ubicación física de un evento.

Atributos principales:
* Calle
* Tipo de inmueble
* Número

---

### Paquete `app`
#### Main
Clase principal encargada de crear las instancias del sistema.

---

## Relación entre clases del proyecto y otros conceptos.

### Herencia
Las clases `OperadorLocal` y `Turista` heredan de la clase `Usuario`, reutilizando atributos comunes.

### Composición
La clase `Evento` contiene un objeto de tipo `Direccion`, mientras que las clases `OperadorLocal` y `Turista` contienen un objeto de tipo `Evento`.

### Encapsulamiento
Todos los atributos fueron declarados como `privados` y se accede a ellos mediante métodos `getters` y `setters`.

### Validaciones
Se implementaron validaciones en atributos numéricos para `evitar el ingreso de valores negativos`.

---

## Instrucciones para compilar y ejecutar

### Requisitos

* Java JDK 23 o superior.
* IntelliJ IDEA (recomendado), Apache Netbeans o VSC.

## Compilación

1. Abrir el proyecto en IntelliJ IDEA.
2. Esperar a que IntelliJ cargue y compile el proyecto automáticamente.
3. Verificar que no existan errores de compilación.

## Instrucciones para ejecutar

1. Abrir el proyecto en IntelliJ IDEA.
2. Abrir la clase `Main.java` ubicada en el paquete `app`.
3. Ejecutar el método `main()` presionando el botón Run (Barra de herramientas de la aplicación).
4. Revisar la salida generada en la consola.
---

## Autor

_Luis Díaz C._

Proyecto desarrollado para la asignatura de Programación Orientada a Objetos I.
