# Genericos

# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)

# :pencil: Actividad
## Buscar todas las cuentas creadas por un usuario usando Genéricos
> Esta actividad continua a la descrita en la clase anterior: [README](../Optional/README.md)
1. Dentro de nuestra función main agregamos un nuevo endpoint llamado getAccountsByUser
    ```java
    // Consultar todas las cuentas y filtrarlas por usuario
        server.createContext("/api/getAccountsByUser", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();

                // Aquí implementaremos nuestro código de filtrar las cuentas por usuario usando genericos
               
                JSONArray json = new JSONArray(accountsFiltered);
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
2. Creamos una función llamada getParameterValueObject la cual regresará cualquier tipo de valor que tengamos como parametro de entrada por la url
   ```java
        private static Optional<Object> getParameterValueObject(Map<String, String> param, String paramName) {
        String val = param.get(paramName);
        if (val != null && val != "") {
            return Optional.ofNullable(val);
        }
        return Optional.ofNullable("NA");
    }
    ```

3. Creamos una variable la cual será un mapa hash en donde almacenaremos todos nuestros parametros de entrada
   ```java
        Map<String, String> params = splitQuery(exchange.getRequestURI());
    ```


4. Creamos una variable de tipo Optional<Object> en la cual almacenaremos el nombre de la variable y sino existe se inicializará por defecto con un valor de NA
   ```java
            Optional<Object> Optionaluser= getParameterValueObject(params, "user");
    ```

5. Recorremos las cuentas y buscamos la cuenta por usuario
    ```java
                Object user = Optionaluser.get();
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUser().indexOf(user.toString()) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                    }
                }
    ```
5. Nuestro código debe verse de la siguiente manera:
    ```java
   // Consultar todas las cuentas y filtrarlas por usuario
        server.createContext("/api/getAccountsByUser", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();

                // Aquí implementaremos nuestro código de filtrar las cuentas por usuario
                Map<String, String> params = splitQuery(exchange.getRequestURI());
                Optional<Object> Optionaluser = getParameterValueObject(params, "user");
                Object user = Optionaluser.get();
               for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUser().indexOf(user.toString()) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                    }
                }
                JSONArray json = new JSONArray(accountsFiltered);
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
# :computer: Requests
Usando postman vamos a usar el siguiente endpoint.
``` bash
localhost:8080/api/getAccountsByUser?user=user3@wizeline.com
```
# :white_check_mark: 200 Response
```json
    {
        "country": "Mexico",
        "accountActive": true,
        "accountName": "Dummy Account 8",
        "accountType": "AHORRO",
        "lastUsage": "2022-10-24T21:59:31.362217",
        "accountNumber": -3842697179278231263,
        "accountBalance": 8684.415704926496,
        "creationDate": "2022-10-17T21:59:31.361696",
        "user": "user3@wizeline.com"
    }
```

# :books: Recursos
- [Opcionales en JAVA](https://www.arquitecturajava.com/que-es-un-java-optional/)
- [Optionals](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
- [Optionals in JAVA](https://www.youtube.com/watch?v=vKVzRbsMnTQ&ab_channel=CodingwithJohn)
