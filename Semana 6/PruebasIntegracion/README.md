# :computer:  Actividades

## Pre-requisitos de la sesión en vivo :exclamation:

Para realizar este curso es importante tener instalado los siguientes programas:
* [JDK 11](https://www.oracle.com/java/technologies/downloads/)
* [Intellij Idea Community](https://www.jetbrains.com/idea/download/#section=windows)
* [Maven](https://maven.apache.org/download.cgi)

## Java línea de comando
Una vez que JDK y MAVEN sean instalados y configurados, procederemos a validar que estén bien instalados para comenzar con la actividad.

### PASO 1: Validar entorno
Abrimos una terminal y validamos si reconoce nuestra versión de Java:

``` bash
# Iniciamos validando que nuestra consola reconozca la versión de Java

jonathan.torres@Jonathans-MacBook-Pro LearningJava1.2 % java -version
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.15+8-LTS-149, mixed mode)

```

Ahora desde una terminal y validamos si reconoce nuestra versión de Maven:

``` bash
# Iniciamos validando que nuestra consola reconosca la versión de Java

jonathan.torres@Jonathans-MacBook-Pro BAZJAVA12022 % mvn -version
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: /Users/jonathan.torres/Open/apache-maven-3.8.5
Java version: 11.0.15, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-11.0.15.jdk/Contents/Home
Default locale: en_MX, platform encoding: UTF-8
OS name: "mac os x", version: "12.3.1", arch: "x86_64", family: "mac"
jonathan.torres@Jonathans-MacBook-Pro BAZJAVA12022 %
```

## Temario Día 3 y 4

### Descripción del ambiente

Lo que se necesitará para las pruebas de integración

### Pruebas unitarias en SpringBoot

Realizar pruebas unitarias en SpringBoot

### Pruebas de integración en SpringBoot

Realizar pruebas de integración en SpringBoot

### Imitadores y espías

Utilizar imitadores y espías en pruebas de integración con SpringBoot

### Probando controladores

Realizar pruebas con controladores y peticiones REST

### Tipos de pruebas de integración

Los diferentes tipos conocidos y disponibles

### Consideraciones finales

Cierre del tema

## Proyecto ejemplo
Este módulo tiene dos proyectos de ejemplo, uno utilizando una base de datos relacional SQL, 
y otra base de datos NoSQL (MongoDB)

Ambos proyectos se encuentran en la carpeta *PruebasIntegracion* dentro de la semana 6

1. Comenzamos con descargar/clonar lo que tengamos en el repositorio https://github.com/wizelineacademy/BAZJAVA12022 y nos vamos a la carpeta de (.6/PruebasIntegracion)

2. Desde IntelliJ importamos el proyecto maven, abriendo el pom.xml que se ubica bajo la carpeta de SpringBootTest (o SpringBootTestMongo según sea el caso)

3. La ejecución de los módulos de prueba se consigue ejecutando:
```
mvn clean test
```

4. Deberemos ver una salida, al finalizar la ejecución, similar a esta:
```
[INFO] Results:
[INFO]
[INFO] Tests run: 27, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.345 s
[INFO] Finished at: 2022-09-30T14:56:19-05:00
[INFO] ------------------------------------------------------------------------
```

## Instrucciones para proyecto final (Capstone)
Incorporar pruebas de integración, además de pruebas unitarias, para las capas de software que lo ameriten

* Al escribir pruebas de integración, se recomienda seguir la siguiente convención en el nombre del método:
    * Dado*Precondiciones*_Cuando*Acción*_Entonces*ResultadoEsperado*
* De manera similar, el cuerpo de la prueba debe seguir el formato:
    * Organizar
    * Actuar
    * Verificar
* Todas las pruebas deben ir acompañadas de mensajes descriptivos que indiquen qué acciones relevantes
  se están realizando (instrucciones, condiciones, validaciones), así como información valiosa para
  que al leer el reporte de pruebas se pueda saber por qué ha pasado o fallado una prueba

# :books: Para aprender más
* [Pruebas en SpringBoot con JUnit 5](https://danielme.com/2018/11/26/testing-en-spring-boot-con-junit-45-mockito-mockmvc-rest-assured-bases-de-datos-embebidas/)
* [Pruebas SpringBoot y MongoDB (fake) (Inglés)](https://www.baeldung.com/spring-boot-embedded-mongodb)
* [Pruebas de integración con MongoDB y SpringData (Inglés)](https://medium.com/yohan-liyanage/integration-testing-with-mongodb-spring-data-4a82c5345239)

