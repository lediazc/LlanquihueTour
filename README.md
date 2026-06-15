![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# Actividad Sumativa 1: Realizando validación, excepciones y relaciones entre clases – Desarrollo Orientado a Objetos I

## Autor del proyecto

* **Nombre completo:** Luis Emilio Díaz Caroca
* **Sección:** 004A
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## Descripción general del sistema

Este proyecto corresponde a la Actividad Sumativa 1: Realizando validación, excepciones y relaciones entre clases - **Desarrollo Orientado a Objetos I**.

La solución desarrollada consiste en un sistema orientado a objetos para la gestión de actividades turísticas en la provincia de Llanquihue. El sistema permite representar operadores turísticos, turistas, eventos y direcciones, aplicando conceptos fundamentales de Programación Orientada a Objetos tales como encapsulamiento, herencia, composición, constructores, getters, setters y reutilización de código.

La aplicación fue implementada en Java y ejecutada mediante consola, permitiendo visualizar la información de los distintos participantes y eventos registrados en el sistema.

---

## Estructura general del proyecto

```plaintext
📁 src/
├── data/
|   └── GestorDatos.java
├── ui/
|   └── Main.java
└── model/
    ├── Usuario.java
    ├── OperadorLocal.java
    ├── Turista.java
    ├── Evento.java
    └── Direccion.java

📁 resources/ 
    └── gestorDatosOperador.txt
```
### Descripción del documento utilizado
El sistema utiliza un archivo de texto plano (.txt) llamado:

"gestorDatosOperador"

Cada línea representa un operador local y contiene información separada por punto y coma (;).

Ejemplo:

1;Jacobo Benavides;jcob@gmail.com;234567989;Guía turístico;Puerto Montt;Tour Volcán Osorno;25;Los Alerces;Oficina;123

Cuando se ejecuta el método main, el sistema:

1- Lee el archivo línea por línea.

2- Separa los datos utilizando ".split(";")"

3- Crea objetos Direccion.

4- Crea objetos Evento.

5- Crea objetos OperadorLocal.

6- Almacena los objetos en un ArrayList.

### Descripción de clases

**Usuario**

* Clase base del sistema.
* Contiene atributos comunes como nombre y correo electrónico.

**OperadorLocal**

* Hereda de Usuario.
* Representa a los operadores turísticos.
* Contiene información sobre comuna, tipo de servicio y evento asignado.

**Turista**

* Hereda de Usuario.
* Representa a los asistentes de actividades turísticas.
* Contiene información de edad, género y evento inscrito.

**Evento**

* Representa una actividad turística.
* Contiene nombre, dirección y cantidad de participantes.

**Direccion**

* Representa la ubicación física de un evento.
* Contiene calle, tipo de inmueble y número.

---
##  Funcionalidades implementadas
Esta semana (15/06/2026) se implementaron las siguientes funcionalidades:

- Mostrar operadores registrados
- Permite recorrer toda la colección y visualizar cada operador junto a la información de su evento asociado.
- Filtrar operadores por cantidad de asistentes(Por eventos grandes o pequeños y por nombre específico)

---

##  Conceptos de Programación Orientada a Objetos aplicados

### Encapsulamiento

Todos los atributos fueron declarados como privados y se accede a ellos mediante getters y setters.

### Herencia

Las clases OperadorLocal y Turista heredan de la clase Usuario.

### Composición

La clase Evento contiene un objeto de tipo Direccion.
Las clases OperadorLocal y Turista contienen un objeto de tipo Evento.

### Validaciones

Se implementaron validaciones en atributos numéricos para evitar el ingreso de valores negativos.

---

##  Instrucciones para ejecutar el proyecto

1. Clonar el repositorio desde GitHub:

```bash
git clone https://github.com/lediazc/LlanquihueTour.git
```

2. Abrir el proyecto en IntelliJ IDEA.

3. Ejecutar la clase:

```plaintext
src/ui/Main.java
```

4. Revisar la salida generada en la consola.

---

**Repositorio GitHub:** https://github.com/lediazc/LlanquihueTour

**Fecha de entrega:** 15/06/2026

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT
