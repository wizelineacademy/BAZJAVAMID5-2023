# Optional

# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)

# :pencil: Actividad
## Buscar el detalle de una cuenta utilizando opcionales
> Esta actividad continua a la descrita en la clase anterior: [README](../Anotaciones/README.md)
1. Dentro de nuestra función main agregamos un nuevo endpoint llamado getAccountsByName
    ```java
     // Consultar todas las cuentas y buscarla por nombre utilizando Optional por si no es encontrada
        server.createContext("/api/getAccountByName", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                // Aquí implementaremos nuestro código de filtrar las cuentas por nombre utilizando optional


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
2. Agregamos una función llamada getParameterValue en el cual buscaremos nuestro parámetro que enviaremos por el url
   ```java
       private static Optional<String> getParameterValue(Map<String, String> param, String paramName) {
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


4. Creamos una variable de tipo Optional<String> en la cual almacenaremos el nombre de la variable y sino existe se inicializará por defecto con un valor de NA
   ```java
            Optional<String> Optionalnombre = getParameterValue(params, "name");
    ```

5. Recorremos las cuentas y buscamos la cuenta por su nombre
    ```java
    String nombre = Optionalnombre.get();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getAccountName().indexOf(nombre) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                        break;
                    }
                }
                JSONArray json = new JSONArray(accountsFiltered);
    ```
5. Nuestro código debe verse de la siguiente manera:
    ```java
   // Consultar todas las cuentas y buscarla por nombre utilizando Optional por si no es encontrada
        server.createContext("/api/getAccountByName", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                // Aquí implementaremos nuestro código de filtrar las cuentas por nombre utilizando optional
                Map<String, String> params = splitQuery(exchange.getRequestURI());
                Optional<String> Optionalnombre = getParameterValue(params, "name");
                String nombre = Optionalnombre.get();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getAccountName().indexOf(nombre) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                        break;
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
localhost:8080/api/getAccountByName?name=Dummy Account 4
```
# :white_check_mark: 200 Response
```json
    {
        "country": "France",
        "accountActive": false,
        "accountName": "Dummy Account 4",
        "accountType": "NOMINA",
        "lastUsage": "2022-10-24T21:24:49.969182",
        "accountNumber": -2960804615309273939,
        "accountBalance": 5941.990576892239,
        "creationDate": "2022-08-24T21:24:49.968520",
        "user": "user1@wizeline.com"
    }
```

# :books: Recursos
- [Opcionales en JAVA](https://www.arquitecturajava.com/que-es-un-java-optional/)
- [Optionals](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
- [Optionals in JAVA](https://www.youtube.com/watch?v=vKVzRbsMnTQ&ab_channel=CodingwithJohn)
