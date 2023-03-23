# Codigo Limpio 3 (Patrones de arquitectura)

### Pre-requisitos de la sesión en vivo
- Tener un IDE instalado como [Visual Studio Code](https://code.visualstudio.com/download) o [IntelliJ](https://www.jetbrains.com/idea/download)

### Actividad (Caso 1. Limitar el numero de peticiones)
* Hacer la implementacion de un throttling basico para uno de los endpoints de tipo GET.
* Para implementar el patron throttling se usara una estrategia llamada Rate Limit, que limita el numero de llamadas o peticiones. Se utilizara una libreria llamada "Bucket4j".
* 1.- Lo primero es agregar la dependencia para maven o gradle:

Maven
```
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
        <artifactId>bucket4j-core</artifactId>
    <version>7.6.0</version>
</dependency>
```

Gradle
```
implementation group: 'com.github.vladimir-bukhtoyarov', name: 'bucket4j-core', version: '7.6.0'
```

* 2.- Despues en el controller se construye el bucket que nos ayudara a limitar el numero de peticiones, para la actividad queremos limitar a que se hagan 5 peticiones como maximo para el endpoint.
* Con esto, la API debería permitir solo 5 solicitudes en un minuto. Por lo tanto, después de la llamada API número 5 dentro de esa ventana de un minuto, la API rechaza la llamada.
```java
@RestController
@RequestMapping("/api")
public class UserController {

    private final Bucket bucket;

    public UserController() {
        Refill refill = Refill.intervally(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
```

* 3.- Ahora se necesita indicar al endpoint al que queremos limitar lo siguiente:  y responder con el codigo 429 "TOO MANY REQUESTS"
```java
    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        if (bucket.tryConsume(1)) {
            //Aqui va la logica para obtener la informacion
            //Se regresa la respuesta normalmente
            return ResponseEntity.ok("It's ok");
        }
        
        //En caso de que se hayan hecho mas de 5 peticiones en 1 minuto respondera con este status
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
```

* 4.- Finalmente probamos el endpoint, y despues de 5 solicitudes en menos de 1 minuto la respuesta sera:
```
429 Too Many Requests
```

### :books: Para aprender mas
* Book: Design Patterns: Elements of Reusable Object-Oriented Software

### Material Autoestudio

#### - TDD vs BDD
* https://netmind.net/es/bdd-y-tdd-en-el-mundo-real-i/
* https://netmind.net/es/bdd-y-tdd-en-el-mundo-real-ii/
* https://netmind.net/es/bdd-y-tdd-en-el-mundo-real-iii/

#### - SOLID
* https://devexperto.com/principios-solid/
* Book: Clean Architecture: A Craftsman's Guide to Software Structure and Design (Robert C. Martin)