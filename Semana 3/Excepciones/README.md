# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)

# :pencil: Actividad
## Agregar usuarios usando concurrencia
> Esta actividad continua a la descrita en la clase anterior: [README](../Concurrencia/README.md)
1. Agregar un nuevo paquete llamado _Exceptions_ dentro del paquete de _utils_. Dentro vamos a crear una nueva clase llamada _ExceptionGenerica.java_
    ```java
    package com.wizeline.utils.exceptions;

    public class ExcepcionGenerica extends RuntimeException {
        public ExcepcionGenerica(String mensajeError) {
            super(mensajeError);
        }
    }
    ``` 

2. En nuestro método de run() vamos a modificar nuestro catch que anteriormente habíamos usado para usar nuestra excepcion generica creada por nosotros, también vamos a
   usar el _LOGGER_ para poder logear la información que obtengamos de nuestro error.
   ```java
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ExcepcionGenerica(e.getMessage());
        }
    ```

3. Podemos probar estos nuevos cambios mandando 2 usuarios en lugar de tres en el request body.
   ```java
    SEVERE: JSONArray[2] not found.
    Exception in thread "Thread-1" com.wizeline.utils.exceptions.ExcepcionGenerica: JSONArray[2] not found.
    at com.wizeline.LearningJava.run(LearningJava.java:277)
    ```

4. También agregamos un catch en nuestro método de _/api/createUsers_
    ```java
    try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
        while(scanner.hasNext()) {
            text.append(scanner.next());
        }
    } catch (Exception e) {
        LOGGER.severe(e.getMessage());
        throw new ExcepcionGenerica("Fallo al obtener el request del body");
    }
    ```

# :books: Recursos
- [Java Excepciones personalizadas](https://www.baeldung.com/java-new-custom-exception)
- [Introducción a las excepciones](http://www.it.uc3m.es/java/prog/resources/excepciones/index_es.html)
- [Best (and Worst) Java Exception Handling Practices](https://able.bio/DavidLandup/best-and-worst-java-exception-handling-practices--18h55kh)
