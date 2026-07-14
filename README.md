![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# Actividad Sumativa 3: Interfaces e integración con colecciones genéricas

## Autor del proyecto

* **Nombre completo:** Luis Emilio Díaz Caroca
* **Sección:** 004A
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## Descripción general del sistema

Este proyecto corresponde a la Actividad Sumativa 3: Interfaces e integración con colecciones genéricas - **Desarrollo Orientado a Objetos I**.

La solución desarrollada consiste en un sistema orientado a objetos para la gestión de actividades turísticas en la provincia de Llanquihue. El sistema permite representar operadores turísticos, turistas, eventos y direcciones, aplicando conceptos fundamentales de Programación Orientada a Objetos tales como encapsulamiento, herencia, composición, constructores, getters, setters y reutilización de código.

La aplicación fue implementada en Java utilizando Swing para proporcionar una interfaz gráfica de usuario (GUI), permitiendo registrar, consultar y filtrar operadores locales, turistas y servicios turísticos mediante formularios y componentes visuales.

---

## Estructura general del proyecto

```plaintext
📁 resources/ 
    ├── gestorDatosOperador.java
    ├── gestorDatosServicios.java
    └── gestorDatosTurista.txt
📁 src/
├── app/
|   └── Main.java
├── data/
|    ├── ConsultaOperadorLocal.java
|    ├── ConsultaTurista.java
|    ├── FormularioOperadorLocal.java
|    ├── FormularioTurista.java
|    ├── GestorDatosOperador.java
|    ├── GestorDatosTurista.java
|    ├── GestorEntidades.java
|    ├── GestorServicios.java
|    └── MenuGeneral.java
└── model/
|    ├── Direccion.java
|    ├── OperadorLocal.java
|    ├── PaseoLacustre.java
|    ├── Registrable.java
|    ├── RutaGastronomica.java
|    ├── ExcursionCultural.java
|    ├── ServicioTuristico.java
|    ├── Turista.java
|    └── Usuario.java
└── ui/
|    └── VentanaPrincipal.java
└── util/
    ├── EntradaConsola.java
    └── Validador.java

```
### Descripción de los documentos utilizados
El sistema utiliza tres archivos de texto plano (.txt):

* gestorDatosOperador.txt: almacena los operadores locales junto con su servicio turístico asociado.
* gestorDatosTurista.txt: almacena la información de los turistas registrados y el servicio turístico inscrito.
* gestorDatosServicios.txt: almacena los distintos servicios turísticos utilizados para demostrar herencia y polimorfismo.

Al iniciar la aplicación, el sistema:

1) Verifica la existencia de la carpeta resources.
2) Crea los archivos con datos semilla si no existen.
3) Lee cada archivo línea por línea.
4) Reconstruye los objetos correspondientes mediante split(";").
5) Almacena los objetos en colecciones ArrayList.

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

**Direccion**

* Representa la ubicación física de un evento.
* Contiene calle, tipo de inmueble y número.


**ServicioTuristico**

* Clase abstracta del sistema. 
* Contiene los atributos comunes a todos los servicios turísticos:
  - nombre
  - duración
  - dirección
  - cantidad de participantes
* Declara el método abstracto ```mostrarInformacion()```.

**RutaGastronomica**

* Representa un recorrido gastronómico e incorpora el atributo `numeroDeParadas`.

**PaseoLacustre**

* Representa un recorrido lacustre e incorpora el atributo `tipoEmbarcacion`.

**ExcursionCultural**

* Representa una actividad cultural e incorpora el atributo `lugarHistorico`.

**GestorServicios**

* Gestiona la lectura de los servicios turísticos desde archivo y demuestra el uso de polimorfismo mediante una colección ArrayList<ServicioTuristico>.

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

**Registrable**

*Interfaz que define el contrato mostrarResumen().


**GestorEntidades**

* Reúne operadores y turistas en una colección ArrayList<Registrable>.
* Recorre la colección utilizando instanceof.

**VentanaPrincipal**

* Implementa la interfaz gráfica mediante Swing.
* Gestiona formularios, navegación con CardLayout y visualización de registros.
---
##  Funcionalidades implementadas
Esta semana (13/07/2026) se implementaron las siguientes funcionalidades:

* Implementación de la interfaz Registrable.
* Incorporación del gestor GestorEntidades.
* Uso de ArrayList<Registrable>.
* Diferenciación de objetos mediante instanceof.
* Desarrollo de una interfaz gráfica con Swing.
* Registro de operadores y turistas mediante formularios.
* Navegación mediante CardLayout.
* Visualización de registros desde la interfaz gráfica.

---
Semana (06/07/2026) se implementaron las siguientes funcionalidades:

* Implementación de una jerarquía de clases mediante la clase abstracta `ServicioTuristico`.
* Aplicación de herencia simple con las clases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`.
* Implementación del método abstracto `mostrarInformacion()` y su sobrescritura en las subclases.
* Implementación de polimorfismo utilizando una colección `ArrayList<ServicioTuristico>`.
* Incorporación del `GestorServicios` para leer servicios turísticos desde archivo.
* Creación del archivo `gestorDatosServicios.txt` con datos semilla.
* Integración de la visualización de servicios turísticos en el menú principal.

---

Semana (29/06/2026) se implementaron las siguientes funcionalidades:
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
**Compatibilidad con la versión por consola**

Como parte de la evolución del proyecto, la aplicación fue migrada desde una interfaz basada en consola hacia una interfaz gráfica desarrollada con Swing. Por ello, se conservaron las clases y métodos correspondientes a la versión por consola (por ejemplo, formularios, consultas y menú principal) con el objetivo de mantener compatibilidad con las entregas anteriores, facilitar la trazabilidad de la evolución del sistema y disponer de una alternativa para futuras evaluaciones o comparaciones de funcionamiento.

La lógica principal del sistema continúa siendo reutilizada por la interfaz gráfica, evitando duplicación de código y permitiendo una transición gradual entre ambas formas de interacción.


---

##  Conceptos de Programación Orientada a Objetos aplicados

### Encapsulamiento

Todos los atributos fueron declarados como privados y se accede a ellos mediante getters y setters.

### Herencia

Las clases `OperadorLocal` y `Turista` heredan de la clase `Usuario`.

La clase abstracta `ServicioTuristico` actúa como superclase de `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`, permitiendo aplicar herencia y polimorfismo mediante la sobrescritura del método abstracto mostrarInformacion(). Cada subclase reutiliza además super.toString() para complementar la información común del servicio.

Usuario implementa la interfaz Registrable, heredando este comportamiento OperadorLocal y Turista.
### Composición

La clase `ServicioTuristico` contiene un objeto de tipo `Direccion`.

Las clases `OperadorLocal` y `Turista` contienen un objeto de tipo `ServicioTuristico`, representando el servicio turístico asociado a cada usuario.

### Validaciones

Se implementaron validaciones en atributos numéricos para evitar el ingreso de valores negativos.

### Modularización

El proyecto se encuentra organizado en paquetes según su responsabilidad:

* app
* model
* data
* util

### Colecciones

Se utilizan colecciones ArrayList para almacenar operadores locales, turistas y servicios turísticos cargados desde archivos de texto. La colección ArrayList<ServicioTuristico> permite demostrar el uso de polimorfismo recorriendo objetos de distintas subclases mediante una referencia común.

Se implementó una colección ArrayList<Registrable> para almacenar entidades de distintos tipos bajo un contrato común, recorriéndolas mediante polimorfismo y diferenciándolas con el operador instanceof.

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

4. Se abrirá la interfaz gráfica principal, desde la cual es posible registrar operadores, turistas, consultar registros y visualizar los servicios turísticos.

---

**Repositorio GitHub:** https://github.com/lediazc/LlanquihueTour

**Fecha de entrega:** 13/07/2026

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT
 