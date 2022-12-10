# Spa - Spring Boot Backend

## Requerimientos funcionales

### Descripción del negocio:

Los usuarios del Spa Belleza se comunican con la recepción solicitando citas de diferentes tipos, algunas de estas citas requieren de ciertos especialistas para ser atendidas.

### Descripción de la necesidad:

La empresa requiere automatizar su proceso de agendamiento, para que genere la información de citas a cada especialista del SPA

### Información insumo:

#### Cliente:

- Información básica:
  - Nombre
  - Documento
  - Edad
  - Género
- Tipo de membresía (Ej.: platino, oro, plata)

#### Especialista:

- Información básica:
  - Nombre
  - Género
- Especialidad (Ej.: Facial, corporal, uñas, etc.)
- Intensidad horaria de seis (6) u ocho (8) horas

### Comportamiento esperado:

#### Agendamiento de citas:

- Las citas que se solicitan son de un tipo (Ej.: Facial, corporal, uñas, etc.)
- Algunos clientes deciden realizar el agendamiento de más de un tipo (Ej.: Facial, corporal, uñas, etc.)
- Cada sesión tiene una duración de 2 horas
- Los usuarios además tiene la posibilidad de agendar diferentes planes (Ej.: Spa Para parejas, masajes de relajación, tratamientos faciales, spa para empresas, etc)   
- Se requiere agendar todas las citas solicitadas teniendo en cuenta las siguientes premisas:
  - Prioridad de atención por tipo membresía
  - Prioridad de atención por tipo de plan 

#### Pago:

Los usuarios del SPA deben pagar de acuerdo al tipo de membresía (si
cuentan con una) y al tipo de cita solicitada (especialidad).

Ej.: 

- Sesión corporal para membresÍa Platino es $100.000
- Sesión corporal para membresía Oro es $170.000

### Resultado esperado:

El proceso generará información de la agenda por profesional con la citas diarias y el valor a pagar por cada usuario

## Requerimientos no funcionales

- Servicios REST
- Se debe permitir realizar carga y descarga de la información mediante el uso de archivos
- Stack: Angular - PrimeNG, DB Oracle, Spring Boot - Java - Gradle
- Documentar en forma clara las referencias, índices y hints requeridos en la db.
- Al menos uno de los procesos transaccionales, se debe usar JPA.
- Al menos uno de los procesos transaccionales, se debe usar JPQL.
- Al menos uno de los procesos transaccionales, se debe usar JDBC invocando PLSQL.

- Se debe tener por lo menos un objeto PL/SQL con lógica de procesamiento masivo de datos. Debe poder ejecutarse en forma automática (programada) y con una opción de ejecución manual.
- DB: Nombre del esquema: GEST_SPA - Idioma: Inglés

## Modelo Relacional

![relational_diagram](assets\relational_diagram.png)