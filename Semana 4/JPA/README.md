# :computer:  Actividades

## Pre-requisitos de la sesión en vivo :exclamation:

Para realizar este curso es importante tener instalado los siguientes programas::
* [JDK 11](https://www.oracle.com/java/technologies/downloads/)
* [Intellij Idea Community](https://www.jetbrains.com/idea/download/#section=windows)
* [Maven](https://maven.apache.org/download.cgi)

## Java línea de comando
Una vez que JDK y MAVEN sean instalados y configurados, procederemos a validar que este bien instalado para comenzar con la actividad.

### PASO 1: Validar entorno
Abrimos una terminal y validamos si reconoce nuestra versión de Java:

``` bash
# Iniciamos validando que nuestra consola reconosca la versión de Java

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

## Temario Día 4

### Descarga de mongodb

Descargar e instalar Mongo DB (daemon y mongosh). Instalacion de MongoDB Compass

### Definicion de Repository

Crear un repositorio que nos permita interactuar con Mongo db

### Definicion de MongoTemplate

Mostrar cuales son algunas APIs de MongoTemplate para interactuar con los objetos en Java y manipularlos en la base de datos


## Practica
La practica y ejercicios las podemos encontrar en el directorio de learningjava

### A continuación, se listaran los pasos para a seguir para la actividad de este módulo.

1. Comenzamos con descargar/clonar lo que tengamos en el repositorio https://github.com/wizelineacademy/BAZJAVA12022 y nos vamos a la carpeta de (.5/MVC/LearningJava), que es el mismo ejercicio del SpringBootApplication dia 3 (MVC)

2. Desde IntelliJ importamos el proyecto maven, abriendo el pom.xml que se ubica bajo la carpeta de LearningJava)

3. La primera modificacion debe ser sobre el archivo pom.xml, agregando la siguiente dependencias

``` xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

4. Sobre la clase BankAccountDTO vamos agregar las siguientes annotationes, una arriba de la definicion de la clase, la cual basicamente dice que manejaremos un "Documento" en donde el nombre de la coleccion se llamara bankAccountCollection, y otra a nivel de la propiedad accountNumber que indicará que es el id de nuestra colección:

``` java
@Document("bankAccountCollection")
public class BankAccountDTO {

    @Id
    private Long accountNumber;
...
}
```

5. Lo primero que tenemos que hacer es definir una interfaz que sea de tipo Repository y sea la que implemente
las operaciones que permitan interactuar con MongoDB. Para ello debemos de crear una clase llamada BankingAccountRepository
debajo del package com.wizeline.maven.learningjava.Repository

6. El cuerpo de BankingAccountRepository debera de contener lo siguiente

``` java
package com.wizeline.maven.learningjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wizeline.maven.learningjava.model.BankAccountDTO;


@Repository
public interface BankingAccountRepository extends MongoRepository<BankAccountDTO, Long> {
}
```

7. Dentro de la clase BankAccountServiceImpl debemos de definir dos Autowired. Uno resolvera el Dependency Injection
para BankingAccountRepository y el otro para MongoTemplate

``` java

	@Autowired
    	BankingAccountRepository bankAccountRepository;

	@Autowired
	MongoTemplate mongoTemplate;
```

8. El metodo de getAccounts() dentro de la clase BankAccountServiceImpl debe ser modificado para que pueda hacer las operaciones
de salvar en la db, asi de encontrar todos los records existentes en la coleccion (bankAccountCollection) dentro de Mongo DB.

Asi debe quedar el cuerpo de la clase:

``` java
@Override
public List<BankAccountDTO> getAccounts() {
    // Definicion de lista con la informacion de las cuentas existentes.
    List<BankAccountDTO> accountDTOList = new ArrayList<>();
    BankAccountDTO bankAccountOne = buildBankAccount("user3@wizeline.com", true, Country.MX, LocalDateTime.now().minusDays(7));
    accountDTOList.add(bankAccountOne);

    //Guardar cada record en la db de mongo (en la coleccion bankAccountCollection)
    mongoTemplate.save(bankAccountOne);

    BankAccountDTO bankAccountTwo = buildBankAccount("user1@wizeline.com", false, Country.FR, LocalDateTime.now().minusMonths(2));
    accountDTOList.add(bankAccountTwo);

    //Guardar cada record en la db de mongo (en la coleccion bankAccountCollection)
    mongoTemplate.save(bankAccountTwo);

    BankAccountDTO bankAccountThree = buildBankAccount("user2@wizeline.com" ,false, Country.US, LocalDateTime.now().minusYears(4));
    accountDTOList.add(bankAccountThree);

    //Guardar cada record en la db de mongo (en la coleccion bankAccountCollection)
    mongoTemplate.save(bankAccountThree);

    //Imprime en la Consola cuales son los records encontrados en la coleccion
    //bankAccountCollection de la mongo db
    mongoTemplate.findAll(BankAccountDTO.class).stream().map(bankAccountDTO -> bankAccountDTO.getUserName()).forEach(
            (user) -> {
                    LOGGER.info("User stored in bankAccountCollection " + user );
            });

    //Esta es la respuesta que se retorna al Controlador
    //y que sera desplegada cuando se haga la llamada a los
    //REST endpoints que la invocan (un ejemplo es el endpoint de  getAccounts)
    return accountDTOList;
}
```

9.  Agregaremos un endpoint que permita borrar todos los records de Mongo DB existentes. Esto involucra
cambios en las capas de Controlador y Servicio. Para la clase BankAccountService debemos de incluir lo siguiente:

``` java

void deleteAccounts();

```

En BankAccountServiceImpl debemos de implementar dicha funcion

``` java

@Override
public void deleteAccounts() {
    //Deleting all records inside of bankAccountCollection in the mongo db
    bankAccountRepository.deleteAll();
}

```

Del lado del Controlador, busquemos la clase BankingAccountController e implementemos lo siguiente:

``` java

@DeleteMapping("/deleteAccounts")
public ResponseEntity<String> deleteAccounts() {
	bankAccountService.deleteAccounts();
	return new ResponseEntity<>("All accounts deleted", HttpStatus.OK);
}

```

10. Agreguemos un endpoint mas para mostrar la funcionalidad de hacer un query a traves de la API de MongoTemplate.
Modifiquemos BankAccountService agregando el siguiente snippet

``` java

List<BankAccountDTO> getAccountByUser(String user);

```

11. Implementemos el metodo en la clase BankAccountServiceImpl

``` java
@Override
public List<BankAccountDTO> getAccountByUser(String user) {
	//Buscamos todos aquellos registros de tipo BankAccountDTO
	//que cumplen con la criteria de que el userName haga match
	//con la variable user
	Query query = new Query();
	query.addCriteria(Criteria.where("userName").is(user));
	return mongoTemplate.find(query, BankAccountDTO.class);
}
```

12. Invocaremos el metodo definido en la capa de servicio dentro del Controlador BankingAccountController e igual la logica
en donde solo obtenemos el registro de un solo usuario que se defina en el request.

``` java
@GetMapping("/getAccountByUser")
public ResponseEntity<List<BankAccountDTO>> getAccountByUser(@RequestParam String user) {
       LOGGER.info(msgProcPeticion);
       Instant inicioDeEjecucion = Instant.now();
       LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
       List<BankAccountDTO> accounts = bankAccountService.getAccountByUser(user);

       Instant finalDeEjecucion = Instant.now();

       LOGGER.info("LearningJava - Cerrando recursos ...");
       String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
       LOGGER.info("Tiempo de respuesta: ".concat(total));

       HttpHeaders responseHeaders = new HttpHeaders();
       responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
       return new ResponseEntity<>(accounts, responseHeaders, HttpStatus.OK);
}
```

13. Descargamos mongodb y mongsh, e instalamos acorde a las instrucciones a traves de la siguiente liga:

https://www.mongodb.com/docs/manual/installation/

Hay que asegurarse de que tengamos mongodb corriendo.

14. Descargamos el UI para MongoDB. Una buena aplicacion es MongoDB Compass, a traves de la siguiente liga

https://www.mongodb.com/try/download/compass

15.  A continuación, ejecutemos el proyecto y hagamos una prueba con estos requests:
* Hagamos un request con el endpoint llamado "Get Accounts"
* Abrimos MongoDB Compass y abrimos la conexion en localhost:27017
* Damos click en la Database de test. En dicha db vemos que existe la coleccion bankAccountCollection. Le damos click
al boton de "Find" y vemos que existen tres registros. (Esto comprueba de que los registros se salvaron en la db)

* Hagamos un request con el endpoint llamado "Get Account By User"
(Con el request definido como GET http://localhost:8080/api/getAccountByUser?user=user3@wizeline.com, vemos que
obtenemos el registro para dicho usuario. Ese registro se esta obteniendo desde la db).

* Hagamos un request con el endpoint llamado "Delete accounts"
Verificamos en MongoDB Compass que efectivamente al darle click al boton "Find" no se trae registro. Con esto validamos
de que no existen registros en nuestra db.


* [LearningJAVA-JPA.postman_collection.json](./Postman/LearningJAVA-JPA.postman_collection.json)


16. Un ejercicio extra seria el de reimplementar el repository llamado UserRepository, en donde se interactue
con mongodb, y en vez de crear/leer un archivo se guarde y recupere datos en/desde la db. Dejamos tal practica al
desarrollador en donde se deberian de eliminar los metodos createFile y writeFile.

# :books: Para aprender mas
* [Spring Initializr](https://start.spring.io/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring REST](https://spring.io/projects/spring-restdocs)
* [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Maven Repository](https://mvnrepository.com/)
