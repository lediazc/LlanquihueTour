![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# Actividad formativa 4 - Creación de jerarquías de clases con herencia simple

## Autor del proyecto

* **Nombre completo:** Luis Emilio Díaz Caroca
* **Sección:** 004A
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## Descripción general del sistema

Este proyecto corresponde a la Actividad Formativa 4: Actividad formativa 4 - Creación de jerarquías de clases con herencia simple - **Desarrollo Orientado a Objetos I**.

La solución desarrollada consiste en un sistema orientado a objetos para la gestión de actividades turísticas en la provincia de Llanquihue. El sistema permite representar operadores turísticos, turistas, eventos y direcciones, aplicando conceptos fundamentales de Programación Orientada a Objetos tales como encapsulamiento, herencia, composición, constructores, getters, setters y reutilización de código.

La aplicación fue implementada en Java y ejecutada mediante consola, permitiendo visualizar la información de los distintos participantes y eventos registrados en el sistema.

---

## Estructura general del proyecto

```plaintext
📁 src/
├── service/
|    ├── ConsultaOperadorLocal.java
|    ├── ConsultaTurista.java
|    ├── FormularioOperadorLocal.java
|    ├── FormularioTurista.java
|    ├── GestorDatosOperador.java
|    ├── GestorDatosTurista.java
|    └── MenuGeneral.java
├── app/
|   └── Main.java
└── model/
|    ├── Usuario.java
|    ├── OperadorLocal.java
|    ├── Turista.java
|    ├── Evento.java //Clase eliminada
|    ├── PaseoLacustre.java
|    ├── RutaGastronomica.java
|    ├── ExcursionCultural.java
|    ├── ServicioTuristico.java
|    └── Direccion.java
└── util/
    ├── EntradaConsola.java
    └── Validador.java
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

1.- Se verifica la existencia del archivo.

2.- Se crean datos semilla si el archivo no existe.

3.- Se leen los registros línea por línea.

4.- Se separan los datos mediante split(";").

5.- Se construyen objetos Direccion.

6.- Se construyen objetos Evento.

7.- Se construyen objetos OperadorLocal.

8.- Los objetos se almacenan en un ArrayList.

### Descripción de clases

**Usuario**

* Clase base del sistema.
* Contiene atributos comunes como nombre, número telefónico y correo electrónico.

**OperadorLocal**

* Hereda de Usuario.
* Representa a los operadores turísticos.
* Contiene información sobre comuna, tipo de servicio, evento asignado y su estado de vigencia.

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

**GestorDatosOperador**

Responsable de:

* Crear archivos de datos.
* Leer información desde archivos.
* Guardar registros en archivos.
* Crear datos semilla.
* Persistir nuevos operadores.

**ConsultaOperadorLocal**

* Permite realizar consultas y filtros sobre los operadores cargados en memoria.

**FormularioOperadorLocal**

* Gestiona el ingreso interactivo de nuevos operadores mediante consola.

**EntradaConsola**

* Centraliza la captura y validación básica de textos ingresados por el usuario.

**Validador**

Contiene métodos estáticos de validación para:

* Texto
* Correos electrónicos
* Números telefónicos
* Valores numéricos

---
##  Funcionalidades implementadas
Esta semana (29/06/2026) se implementaron las siguientes funcionalidades:
* Implementación de herencia y polimorfismo mediante la jerarquía `ServicioTuristico`, incorporando las clases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`.

* Selección dinámica del tipo de servicio turístico durante el registro de operadores y turistas.

* Sobrescritura de `toString()` en las subclases utilizando `super.toString()` para extender la información común del servicio.

* Mejoras en la organización del modelo orientado a objetos, eliminando atributos redundantes y centralizando la información común en la superclase `ServicioTuristico`.

* Se eliminó la clase Evento, debido a solicitud del trabajo semanal.
---
Semana (22/06/2026) se implementaron las siguientes funcionalidades:
* Gestión de registros

* Filtros y búsquedas

* Validaciones

* Manejo de excepciones

---

Semana (15/06/2026) se implementaron las siguientes funcionalidades:

* Mostrar operadores registrados
* Permite recorrer toda la colección y visualizar cada operador junto a la información de su evento asociado.
* Filtrar operadores por cantidad de asistentes(Por eventos grandes o pequeños y por nombre específico)

---

##  Conceptos de Programación Orientada a Objetos aplicados

### Encapsulamiento

Todos los atributos fueron declarados como privados y se accede a ellos mediante getters y setters.

### Herencia

Las clases `OperadorLocal` y `Turista` heredan de la clase `Usuario`.

La clase abstracta `ServicioTuristico` actúa como superclase de `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`, permitiendo aplicar herencia y polimorfismo mediante la sobrescritura del método `toString()`.

### Composición

La clase `ServicioTuristico` contiene un objeto de tipo `Direccion`.

Las clases `OperadorLocal` y `Turista` contienen un objeto de tipo `ServicioTuristico`, representando el servicio turístico asociado a cada usuario.

### Validaciones

Se implementaron validaciones en atributos numéricos para evitar el ingreso de valores negativos.

### Modularización

El proyecto se encuentra organizado en paquetes según su responsabilidad:

* app
* model
* service
* util

### Colecciones

Se utiliza ArrayList para almacenar dinámicamente los operadores cargados desde archivo.

---

##  Instrucciones para ejecutar el proyecto

1. Clonar el repositorio desde GitHub:

```bash
git clone https://github.com/lediazc/LlanquihueTour.git
```

2. Abrir el proyecto en IntelliJ IDEA.

3. Ejecutar la clase:

```plaintext
src/app/Main.java
```

4. Revisar la salida generada en la consola.

---

**Repositorio GitHub:** https://github.com/lediazc/LlanquihueTour

**Fecha de entrega:** 29/06/2026

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT
 