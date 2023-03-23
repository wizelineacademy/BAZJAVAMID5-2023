# :pencil: Actividad
## Agregar usuarios usando concurrencia
> Esta actividad continua a la descrita en la clase anterior: [README](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/FechasTiempos/README.md)
1. Agregar las siguientes variables globales dentro de nuestra clase principal para poder usarlas en nuestros threads.
    ```java
    private static String responseTextThread = "";
    private ResponseDTO response;
    private static String textThread = "";
    ``` 

2. Extendemos la clase Thread en la clase principal para poder usar la concurrencia.
   ```java
   public class LearningJava extends Thread {
    ```
3. Agregar un nuevo _endpoint_ que usaremos para poder agregar tres usuarios.
    ```java
        // Crear usuarios
        server.createContext("/api/createUsers", (exchange -> {
            LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
            ResponseDTO response = new ResponseDTO();
            /** Validates the type of http request  */
            exchange.getRequestBody();
            if ("POST".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST");
                // Obtenemos el request del body que mandamos
                StringBuilder text = new StringBuilder();
                try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
                    while(scanner.hasNext()) {
                        text.append(scanner.next());
                    }
                }
                textThread = text.toString();
                LOGGER.info(textThread);
                // Iniciamos thread
                LearningJava thread = new LearningJava();
                thread.start();
                // Esperamos a que termine el thread
                while(thread.isAlive());
                exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, responseTextThread.getBytes().length);
            } else {
                /** 405 Method Not Allowed */
                exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            output.write(responseTextThread.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));
    ```
   > Lo podemos agregar después del endpoint de /api/createUser
4. Agregamos un nuevo método llamado run, que viene de la clase Thread que extendimos.
   ```java
   public void run(){
        try {
            String user = "user";
            String pass = "password";
            JSONArray jsonArray = new JSONArray(textThread);
            JSONObject user1 = new JSONObject(jsonArray.get(0).toString());
            JSONObject user2 = new JSONObject(jsonArray.get(1).toString());
            JSONObject user3 = new JSONObject(jsonArray.get(2).toString());
            // Creamos usuario 1
            response = createUser(user1.getString(user), user1.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 1: " + responseTextThread);
            Thread.sleep(1000);
            // Creamos usuario 2
            response = createUser(user2.getString(user), user2.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 2: " + responseTextThread);
            Thread.sleep(1000);

            // Creamos usuario 3
            response = createUser(user3.getString(user), user3.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 3: " + responseTextThread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    ```

# :computer: Requests
Usando postman vamos a usar el siguiente endpoint.
``` bash
http://localhost:8080/api/createUsers
```
Y en el apartardo de _Body_ vamos a agregar el siguiente _raw_ en _JSON_.
```json
[{
  "user": "user1@wizeline.com",
  "password": "pass1"
},
  {
    "user": "user2@wizeline.com",
    "password": "pass2"
  },
  {
    "user": "user3@wizeline.com",
    "password": "pass3"
  }]
```
# :white_check_mark: 200 Response
```json
{
  "code": "OK000",
  "errors": {},
  "status": "success"
}
```

# :books: Recursos
- [Java Threads](https://www.geeksforgeeks.org/java-threads/)
- [Creating Threads](https://www.w3schools.com/java/java_threads.asp)