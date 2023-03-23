# :computer:  Actividades

## Pre-requisitos de la sesión en vivo :exclamation:

Para realizar este curso es importante tener instalado los siguientes programas:
* [JDK 11](https://www.oracle.com/java/technologies/downloads/)
* [Intellij Idea Community](https://www.jetbrains.com/idea/download/#section=windows)
* [Maven](https://maven.apache.org/download.cgi)

## Java línea de comando
Una vez que JDK y MAVEN sean instalados y configurados, procederemos a validar que este bien instalado para comenzar con la actividad.

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

## Temario Día 1

### Pruebas Unitarias

Fundamentos sobre las pruebas unitarias (Unit Testing)

### JUnit

El framework por excelencia para pruebas unitarias en Java

### JUnit en práctica

Ejemplos de los diferentes componentes de JUnit y cómo se utilizan en la práctica


## Temario Día 2

### Dobles de prueba

Sustitutos de elementos reales para una prueba

### Mockito

Una librería muy utilizada para implementar dobles de prueba en Java

### Dobles de prueba en Mockito

Ejemplos de cómo incorporarlos a nuestras pruebas unitarias

### Definiendo el comportamiento

Ejemplos para manipular a voluntad el comportamiento de los dobles de prueba


## Proyecto ejemplo
El proyecto de ejemplo de este día se encuentran en la carpeta *JUnit* dentro de la semana 6

1. Comenzamos con descargar/clonar lo que tengamos en el repositorio https://github.com/wizelineacademy/BAZJAVA12022 y nos vamos a la carpeta de (.6/PruebasUnitarias)

2. Desde IntelliJ importamos el proyecto maven, abriendo el pom.xml que se ubica bajo la carpeta de JUnit

3. La ejecución de los módulos de prueba se consigue ejecutando:
```
mvn clean test
```

4. Deberemos ver una salida, al finalizar la ejecución, similar a esta:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.939 s
[INFO] Finished at: 2022-09-30T14:05:40-05:00
[INFO] ------------------------------------------------------------------------
```

## Instrucciones para proyecto final (Capstone)
Incorporar pruebas unitarias para todas las clases que se hayan escrito en el mismo, siguiendo estas recomendaciones:

* Al escribir pruebas unitarias, se recomienda seguir la siguiente convención en el nombre del método:
    * Dado*Precondiciones*_Cuando*Acción*_Entonces*ResultadoEsperado*
* De manera similar, el cuerpo de la prueba debe seguir el formato:
    * Organizar
    * Actuar
    * Verificar
* Todas las pruebas deben ir acompañadas de mensajes descriptivos que indiquen qué acciones relevantes
  se están realizando (instrucciones, condiciones, validaciones), así como información valiosa para
  que al leer el reporte de pruebas se pueda saber por qué ha pasado o fallado una prueba

# :books: Para aprender más
* [Pruebas unitarias en JUnit 5](https://danielme.com/2021/04/15/curso-jakarta-ee-pruebas-automaticas-junit5/)
* [Guía de usuario de JUnit (Inglés)](https://junit.org/junit5/docs/current/user-guide/)
* [Fake/Stub/Mock (Inglés)](https://happydevops.com/2020/06/22/que-es-fake-stub-y-mock-en-unit-testing/)
* [Tutorial de dobles de pruebas](https://danielme.com/2017/07/24/tutorial-test-doubles-mockito/)
* [Documentación de Mockito y su evolución (Inglés)](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)

