# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)

# :pencil: Actividad
> Esta actividad continua a la descrita en la clase anterior: [README](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/Anotaciones/README.md)
## Obtener todas las cuentas y esten agrupadas por su tipo

1. Primero importamos la librerias a usar:
    ```java
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Collectors;
    import java.util.function.Function;
    ``` 
1. Dentro de nuestra función main agregamos un nuevo endpoint llamado getAccountsGroupByType:
    ```java
    // Consultar todas las cuentas y agruparlas por su tipo utilizando Programación Funcional
        server.createContext("/api/getAccountsGroupByType", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                // Aqui implementaremos la programación funcional


                JSONArray json = new JSONArray(accounts);
                responseText = json.toString();
                exchange.getResponseHeaders().add("Content-type", "application/json");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
                /** 405 Method Not Allowed */
                exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            Instant finalDeEjecucion = Instant.now();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
            LOGGER.info("Tiempo de respuesta: ".concat(total));
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));
    ``` 
1. Después de nuestro comment donde implementaremos la programación funcional agregaremos nuestra variable de resultado como un mapa de Strings como llave y una Lista de BankAccountDTO como valor:
    ```java
                // Aqui implementaremos la programación funcional
                Map<String, List<BankAccountDTO>> groupedAccounts;
    ```
1. Posteriormente creamos una función y la asignaremos a la variable groupFunction la cual retornará el tipo de cuenta de cada elemento por la cual agruparemos:
    ```java
                Function<BankAccountDTO, String> groupFunction = (account) -> account.getAccountType().toString();
    ``` 
1. Aplicamos programación funcional aplicando los 3 pasos de conversión de origen de datos a stream de datos, transformanción y su representación:
    ```java
                groupedAccounts = accounts.stream().collect(Collectors.groupingBy(groupFunction));
    ```  
1. Ahora nuestra salida en vez de ser accounts será groupedAccounts por lo que modificaremos el json resultando por un JSONObject:
    ```java
                JSONObject json = new JSONObject(groupedAccounts);
    ```    
1. Nuestro código debe de verse como se muestra acontinuación:
    ```java
         // Consultar todas las cuentas y agruparlas por su tipo utilizando Programación Funcional
         server.createContext("/api/getAccountsGroupByType", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                
                // Aqui implementaremos la programación funcional
                Map<String, List<BankAccountDTO>> groupedAccounts;
                Function<BankAccountDTO, String> groupFunction = (account) -> account.getAccountType().toString();
                groupedAccounts = accounts.stream().collect(Collectors.groupingBy(groupFunction));

                JSONObject json = new JSONObject(groupedAccounts);
                responseText = json.toString();
                exchange.getResponseHeaders().add("Content-type", "application/json");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
                /** 405 Method Not Allowed */
                exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            Instant finalDeEjecucion = Instant.now();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
            LOGGER.info("Tiempo de respuesta: ".concat(total));
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));
    ```     

Usando postman vamos a usar el siguiente endpoint.
``` bash
curl --location --request GET 'http://localhost:8080/api/getAccountsGroupByType'
```
Si todo sale bien obtendremos la siguiente respuesta del endpoint:

```json
    {
  "PLATINUM":[
    {
      "country":"Mexico",
      "accountActive":true,
      "accountName":"Dummy Account 9",
      "accountType":"PLATINUM",
      "lastUsage":"2022-09-21T17:36:53.218508",
      "accountNumber":-848425873548591661,
      "accountBalance":5124.53122226487,
      "creationDate":"2022-09-14T17:36:53.192135",
      "user":"user3@wizeline.com"
    }
  ],
  "AHORRO":[
    {
      "country":"France",
      "accountActive":false,
      "accountName":"Dummy Account 5",
      "accountType":"AHORRO",
      "lastUsage":"2022-09-21T17:36:53.204673",
      "accountNumber":-1031229951231195137,
      "accountBalance":1554.6753011957016,
      "creationDate":"2022-07-21T17:36:53.195349",
      "user":"user1@wizeline.com"
    },
    {
      "country":"United States",
      "accountActive":false,
      "accountName":"Dummy Account 6",
      "accountType":"AHORRO",
      "lastUsage":"2022-09-21T17:36:53.218085",
      "accountNumber":4347313336355804877,
      "accountBalance":4836.935485075421,
      "creationDate":"2018-09-21T17:36:53.195431",
      "user":"user2@wizeline.com"
    }
  ]
}

```
# :books: Recursos
- [Ejemplos de Programación Funcional usando Stream de datos](https://www.belikesoftware.com/java-8-streams/#:~:text=Los%20Streams%20en%20java%20son,en%20la%20etapa%20de%20transformaci%C3%B3n.)
- [Programación Funcional con Java](https://myacademy.wizeline.com/#/online-courses/2554fbe3-0c94-4d97-871b-3b72239830b3)
- [Operador ParalellStream](https://www.arquitecturajava.com/java-parallel-stream-y-rendimiento/)
- [Programación Funcional con Java](https://myacademy.wizeline.com/#/online-courses/2554fbe3-0c94-4d97-871b-3b72239830b3)
- [Operador flatMap](https://www.arquitecturajava.com/java-8-flatmap/)
- [Operador Distinct](https://howtodoinjava.com/java8/java-stream-distinct-examples/)
