# Anotaciones

# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)
- [json-20220320.jar](https://repo1.maven.org/maven2/org/json/json/20220320/)

# :pencil: Actividad
## Actualizar método para agregar usuarios
> Esta actividad continua a la descrita en la clase anterior: [README](../FechasTiempos/README.md)
1. Primero vamos a agregar la anotación de Override a nuestro método de concurrencia _run()_
    ```java
    @Override
    public void run(){
        try {
    ``` 
    > Esto evitará que llegaramos a tener algún problema respecto a que no llegara a correr el método.

2. En nuestro método de run() vamos a mover todo lo que esté dentro de nuestro _try_ a algún método que creemos. Y le agregaremos la anotación de *@Deprecated*
   ```java
    @Deprecated(since = "Anotaciones update")
    private void createUsers() {
        try {
            String user = "user";
            String pass = "password";
            JSONArray jsonArray = new JSONArray(textThread);
            JSONObject user1 = new JSONObject(jsonArray.get(0).toString());
            JSONObject user2 = new JSONObject(jsonArray.get(1).toString());
            JSONObject user3 = new JSONObject(jsonArray.get(2).toString());

            ResponseDTO response = createUser(user1.getString(user), user1.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 1: " + responseTextThread);
            Thread.sleep(1000);

            response = createUser(user2.getString(user), user2.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 2: " + responseTextThread);
            Thread.sleep(1000);

            response = createUser(user3.getString(user), user3.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 3: " + responseTextThread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    ```
   > El _since_ dentro de _@Deprecated_ se usa para anotar desde cuando se volvió obsoleto este método.

3. Crearemos un nuevo método llamado _crearUsuarios()_ donde pondremos la nueva lógica para poder agregar usuarios. Este nuevo método aceptará cualquier número de usuarios que pongamos en el request. 
   ```java
    @Override
    public void run(){
        try {
            crearUsuarios();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ExcepcionGenerica(e.getMessage());
        }
    }
    ```

4. Y el nuevo método de crearUsuarios quedaría de la siguiente forma.
    ```java
    private void crearUsuarios() {
        try {
            String user = "user";
            String pass = "password";
            JSONArray jsonArray = new JSONArray(textThread);
            JSONObject userJson;

            ResponseDTO response = null;

            LOGGER.info("jsonArray.length(): " + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++) {
                userJson = new JSONObject(jsonArray.get(i).toString());
                response = createUser(userJson.getString(user), userJson.getString(pass));
                responseTextThread = new JSONObject(response).toString();
                LOGGER.info("Usuario " + (i+1) + ": " + responseTextThread);
                Thread.sleep(1000);
            }
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
- [Anotaciones en JAVA](https://javadesdecero.es/avanzado/anotaciones-annotations/)
- [Java Annotations](https://jenkov.com/tutorials/java/annotations.html)
- [¿Cuál es la función de las anotaciones en Java?](https://es.stackoverflow.com/questions/79397/cu%C3%A1l-es-la-funci%C3%B3n-de-las-anotaciones-en-java)
