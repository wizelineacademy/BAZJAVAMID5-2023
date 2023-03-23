# :computer:  Actividades

## Pre-requisitos de la sesión en vivo :exclamation:

Para realizar este curso es importante tener instalado los siguientes programas::
* [JDK 11](https://www.oracle.com/java/technologies/downloads/)
* [Intellij Idea Community](https://www.jetbrains.com/idea/download/#section=windows)


## Java línea de comando
Una vez que JDK y Intellij fue descargados e instalados, procederemos a validar que este bien instalado para comenzar las pruebas

### PASO 1: Validar entorno
Abrimos una terminal y validamos si reconoce nuestra versión de Java:

``` bash
# Iniciamos validando que nuestra consola reconosca la versión de Java

jonathan.torres@Jonathans-MacBook-Pro LearningJava1.2 % java -version
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.15+8-LTS-149, mixed mode)

```


## Temario

### Clases

Conceptos de clases y estructura.

### Herencia

Definición e implementación de herencia.

### Sobrecarga de métodos

Función e implementación de métodos sobrecargados.

### Sobrecarga de constructores

Función e implementación de constructores sobrecargados.

### Encapsulamiento

Conceptos e implementación de encapsulamiento.

### Clases Internas

Definición e implementación de encapsulamiento.

### Paquetes

Función e implementación de paquetes.

### Interfaces

Función e implementación de interfaces.


## Practica
La practica y ejercicios las podemos encontrar en el directorio de practica

### A continuación, se listaran los pasos para a seguir para la actividad de este módulo.

1 . Comenzamos abriendo nuestro IntelliJ y vamos a la opción de crear nuevo proyecto Java.

![Alt text](./Images/1.CreateProject.png "Creación de Proyecto Java")


2. IntelliJ nos muestra nuestro proyecto Java.

![Alt text](./Images/2.LearningProject.png "Creación de Proyecto Java")


3. Ahora vamos a crear en nuestra carpeta src los siguientes paquetes:
  - com.wizeline.BO
  - com.wizeline.DAO
  - com.wizeline.DTO
  - com.wizeline.utils
  - com.wizeline

![Alt text](./Images/3.PackageJava.png "Creación de Paquetes Java")


4. Comenzaremos a crear los siguientes DTO:
  - UserDTO.java
  - ErrorDTO.java
  - ResponseDTO.java

![Alt text](./Images/5.UserDTO.png "Creación de UserDTO.java")

![Alt text](./Images/6.ResponseDTO.png "Creación de ResponseDTO.java")

![Alt text](./Images/7.ErrorDTO.png "Creación de ErrorDTO.java")


5. Ahora pasamos a generar unas cuantas utilerias que nos ayudaran a realizar valiaciones de datos:

![Alt text](./Images/8.Utils.png "Creación de Utils.java")


6. Pasamos a la creación de Interfaces, en este caso primero comenzaremos con la interface DAO "UserDAO.java":

![Alt text](./Images/9.DAOInterface.png "Creación de UserDAO.java")

7. Creamos ahora la clase de implementación "UserDAOImpl.java" para la interface anterior. Adicional, crearemos algunos métodos para escribir un archivo.

![Alt text](./Images/10.DAOImplements1-3.png "Creación de UserDAOImpl.java")

![Alt text](./Images/11.DAOImplements2-3.png "Creación de UserDAOImpl.java")

![Alt text](./Images/12.DAOImplements3-3.png "Creación de UserDAOImpl.java")



8. Ahora crearemos la interface BO "UserBO.java":

![Alt text](./Images/13.BOInterface.png "Creación de UserBO.java")


9. Ahora también crearemos la clase de implementación "UserBOImpl.java" para la interface anterior:

![Alt text](./Images/14.BOImplements.png "Creación de UserBOImpl.java")



10. Ahora vamos a crear nuestra clase main dentro del paquete com.wizeline cuyo nombre será "LearningJava.java".

11. Crearemos el siguiente método dentro de la clse "LearningJava.java" y lo usaremos para recuperar la URI y parámetros:

![Alt text](./Images/15.GetURI.png "Método para manejar la URI")


12. Ahora vamos a crear un método para vaciar los paramtros recibidos, podemos crear un solo metodo ya que para este ejercicio usaremos el mismo DTO:

![Alt text](./Images/16.GetParameters.png "Set parameters")


13. A continuación, comenzaremos con la parte complicada ya que vamos a crear el servidor para nuestro servico Rest. Definimos nuestro método main:
![Alt text](./Images/17.Main1-5.png "Creación de Proyecto Java")

14. Vamos a crear 2 métodos los cuales serán expuestos para puedan ser consumidos desde Postman:

  - Primero método con URL /api/login:
![Alt text](./Images/18.Main2-5.png "Path api/login.java")

  - Primero método con URL /api/createUser:
![Alt text](./Images/19.Main3-5.png "Path api/createUser")

15. Finalmente creamos las funciones para iniciar nuestro server:
![Alt text](./Images/20.Main4-5.png "Clase Main")


16. Al llegar aquí seguramente tengas un error en la clase Main, esto se debe a que no reconoce la función JSONObject, bien pues esto lo vamos a solucionar ahora:

  - Solo necesitaremos agregar la libreria "json-20220320.jar" a nuestro proyecto java, a continuación tenemos el proceso:
![Alt text](./Images/21.Library.png "Creación de Proyecto Java")

![Alt text](./Images/22.AddLibrary1-4.png "Libreria JSONObject")

![Alt text](./Images/23.AddLibrary2-4.png "Libreria JSONObject")

![Alt text](./Images/24.AddLibrary3-4.png "Libreria JSONObject")

![Alt text](./Images/25.AddLibrary4-4.png "Libreria JSONObject")


17. Pasemos a ejecutar desde IntelliJ nuestra aplicación Java. Si todo lo hicimos correctamente debería poder ver el arranque de nuestra aplicación.

18. Puedes descargar de la siguiente liga la colección de Postman que usaremos para consumir nuestros servicios:

* [LearningJava.postman_collection.json](./Postman/LearningJava.postman_collection.json)


# :books: Para aprender mas
* [Java](https://dev.java)
* [Java Documentation](https://docs.oracle.com/en/java/)
* [Learn Java](https://dev.java/learn/)
* [Frequently Asked Questions (FAQs)](https://dev.java/learn/faq/)
* [Java Youtube](https://www.youtube.com/java)
* [JRE/JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
