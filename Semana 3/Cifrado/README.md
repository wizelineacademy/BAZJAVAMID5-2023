# Cifrado

# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)
- [bcprov-jdk18on-172.jar](https://www.bouncycastle.org/download/bcprov-jdk18on-172.jar)

# :pencil: Actividad
## Crear un endpoint en donde obtengamos las cuentas de manera cifrada
> Esta actividad continua a la descrita en la clase anterior: [README](../Genericos/README.md)
1. Agregamos la librería Security y BouncyCastle la cual utilizaremos en este ejercicio:
    ```java
        import java.security.Security;
        import org.bouncycastle.jce.provider.BouncyCastleProvider;
    ``` 

2. Dentro de nuestra función main agregamos un nuevo endpoint llamado getEncryptedAccounts:
    ```java
   // Consultar todas las cuentas y regresarselas al usuario de manera cifrada
        server.createContext("/api/getEncryptedAccounts", (exchange -> {
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
                // Aquí implementaremos nuestro código de cifrar nuestras cuentas y regresarselas al usuario de manera cifrada

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
3. Agregamos dos arreglos de bytes que corresponderan a las llaves que utilizaremos para cifrar nuestra información:
   ```java
                    byte[] keyBytes = new byte[]{
                            0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
                    };
                    byte[] ivBytes = new byte[]{
                            0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01
                    };
    ```

4. Inicializamos el Provider de Security y establecemos que sera BouncyCastle como proveedor
   ```java
        Security.addProvider(new BouncyCastleProvider());
    ```


5. Inicializamos las llaves y establecemos el algoritmo "DES" de cifrado:
   ```java
           SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
           IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
           Cipher cipher = null;
    ```

6. Inicializamos nuestro cifrador utilizando como transformador "DES/CTR/NoPadding" y estableciendole que utilizaremos Bouncy Castle. Además añaderemos las excepciones que deben ser tomadas en cuenta al utilizar cifrado:
    ```java
                 try {
                        cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
                        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchProviderException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidAlgorithmParameterException e) {
                        throw new RuntimeException(e);
                    } catch (ShortBufferException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalBlockSizeException e) {
                        throw new RuntimeException(e);
                    } catch (BadPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
    ```
7. Recorremos todas las cuentas y ciframos tanto el nombre como el pais de la misma (Se pueden encriptar todos los campos de ser requerido):
    ```java
        // Cifraremos solamente el nombre y el country (pueden cifrar todos los parámetros que gusten)
                        for (int i = 0; i < accounts.size(); i++) {
                            String accountName = accounts.get(i).getAccountName();
                            byte[] arrAccountName = accountName.getBytes();
                            byte [] accountNameCipher = new byte[cipher.getOutputSize(arrAccountName.length)];
                            int ctAccountNameLength = cipher.update(arrAccountName, 0, arrAccountName.length, accountNameCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountNameCipher, ctAccountNameLength);
                            accounts.get(i).setAccountName(accountNameCipher.toString());

                            String accountCountry = accounts.get(i).getCountry();
                            byte[] arrAccountCountry = accountCountry.getBytes();
                            byte[] accountCountryCipher = new byte[cipher.getOutputSize(arrAccountCountry.length)];
                            int ctAccountCountryLength = cipher.update(arrAccountCountry, 0, arrAccountCountry.length, accountCountryCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountCountryCipher, ctAccountCountryLength);
                            accounts.get(i).setCountry(accountCountryCipher.toString());

                        }
    ```  

8. Si todo está correcto nuestro código debe de verse de la siguiente manera:
    ```java
        // Consultar todas las cuentas y regresarselas al usuario de manera cifrada
        server.createContext("/api/getEncryptedAccounts", (exchange -> {
                LOGGER.info(msgProcPeticion);
                Instant inicioDeEjecucion = Instant.now();
                BankAccountBO bankAccountBO = new BankAccountBOImpl();
                String responseText = "";
                /** Validates the type of http request  */
                if ("GET".equals(exchange.getRequestMethod())) {
                    LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                    List<BankAccountDTO> accounts = bankAccountBO.getAccounts();

                    // Aquí implementaremos nuestro código de cifrar nuestras cuentas y regresarselas al usuario de manera cifrada
                    byte[] keyBytes = new byte[]{
                            0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
                    };
                    byte[] ivBytes = new byte[]{
                            0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01
                    };
                    Security.addProvider(new BouncyCastleProvider());
                    SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
                    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                    Cipher cipher = null;
                    try {
                        cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
                        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                            // Cifraremos solamente el nombre y el country (pueden cifrar todos los parámetros que gusten)
                        for (int i = 0; i < accounts.size(); i++) {
                            String accountName = accounts.get(i).getAccountName();
                            byte[] arrAccountName = accountName.getBytes();
                            byte [] accountNameCipher = new byte[cipher.getOutputSize(arrAccountName.length)];
                            int ctAccountNameLength = cipher.update(arrAccountName, 0, arrAccountName.length, accountNameCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountNameCipher, ctAccountNameLength);
                            accounts.get(i).setAccountName(accountNameCipher.toString());

                            String accountCountry = accounts.get(i).getCountry();
                            byte[] arrAccountCountry = accountCountry.getBytes();
                            byte[] accountCountryCipher = new byte[cipher.getOutputSize(arrAccountCountry.length)];
                            int ctAccountCountryLength = cipher.update(arrAccountCountry, 0, arrAccountCountry.length, accountCountryCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountCountryCipher, ctAccountCountryLength);
                            accounts.get(i).setCountry(accountCountryCipher.toString());

                        }
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchProviderException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidAlgorithmParameterException e) {
                        throw new RuntimeException(e);
                    } catch (ShortBufferException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalBlockSizeException e) {
                        throw new RuntimeException(e);
                    } catch (BadPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }


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
# :computer: Requests
Usando postman vamos a usar el siguiente endpoint.
``` bash
localhost:8080/api/getEncryptedAccounts
```
# :white_check_mark: 200 Response
```json
    [
    {
        "country": "[B@4d071e63",
        "accountActive": true,
        "accountName": "[B@5f1ff213",
        "accountType": "AHORRO",
        "lastUsage": "2022-10-26T13:47:00.346937",
        "accountNumber": -8531992398598841045,
        "accountBalance": 7799.540568670154,
        "creationDate": "2022-10-19T13:47:00.126826",
        "user": "user3@wizeline.com"
    },
    {
        "country": "[B@b6fe4b0",
        "accountActive": false,
        "accountName": "[B@51ce9285",
        "accountType": "AHORRO",
        "lastUsage": "2022-10-26T13:47:00.355078",
        "accountNumber": -202202866158038940,
        "accountBalance": 6000.368156517895,
        "creationDate": "2022-08-26T13:47:00.133685",
        "user": "user1@wizeline.com"
    },
    {
        "country": "[B@5ec1f7b9",
        "accountActive": false,
        "accountName": "[B@2bc010a4",
        "accountType": "NOMINA",
        "lastUsage": "2022-10-26T13:47:00.355392",
        "accountNumber": 1728692448877676176,
        "accountBalance": 1304.808244213044,
        "creationDate": "2018-10-26T13:47:00.133766",
        "user": "user2@wizeline.com"
    }
]
```

# :books: Recursos
- [My Academy Learn Java Cryptography](https://myacademy.wizeline.com/#/online-courses/74b26496-7d58-4e44-94dd-efb64bdcfaca)
- [Bouncy Castle](https://www.bouncycastle.org/)
- [Java Security](https://docs.oracle.com/javase/9/security/java-security-overview1.htm#JSSEC-GUID-2EF91196-D468-4D0F-8FDC-DA2BEA165D10)
