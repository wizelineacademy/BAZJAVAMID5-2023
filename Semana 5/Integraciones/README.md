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

### Implementacion de Feign Client

Definir una interfaz que nos permita tener un template declarativo de llamadas REST

### Contenerizacion de nuestra aplicacion Sprigboot

Definir un contenedor a traves de un Dockerfile de nuestra aplicacion de Springboot

### Manejo de Ribbon

Manejo de load balancing del lado de cliente para manejo de peticiones en diferentes replicas/servidores.

### Implementacion de Kafka

Implementaremos lo que es el Producer y el Consumer en un caso sencillo a traves de un Request


## Practica
La practica y ejercicios las podemos encontrar en el directorio de learningjava

### A continuación, se listaran los pasos para a seguir para la actividad de este módulo.

1. La primera modificacion debe ser sobre el archivo pom.xml, agregando el siguiente snippet que define el dependencyManagement

``` xml
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>2021.0.3</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

Asimismo agregamos la siguiente dependencia:

``` xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

(Nota: hay que agregar la anotacion EnableFeignclients en SpringBootApplication)

``` java
package com.wizeline.maven.learningjava;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableFeignClients
public class LearningJavaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearningJavaApplication.class, args);
	}

}
```

4. Vamos a crear una interfaz dentro de un paquete que llamaremos com.wizeline.maven.learningjava.client llamada AccountsJSONClient la cual
tendra el siguiente contenido

``` java
package com.wizeline.maven.learningjava.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wizeline.maven.learningjava.model.Post;

@FeignClient(value="getAccountsClient", url="https://jsonplaceholder.typicode.com/")
public interface AccountsJSONClient {
    @GetMapping(value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);
}
```
5. Con ello lo que hacemos es definir un template que permitira hacer una llamada de tipo REST usando el HTTP Method GET
para obtener un objeto tipo Post que ahorita mismo definiremos.

Crearemos una clase Post dentro del package de model

``` java
package com.wizeline.maven.learningjava.model;

public class Post {
    private String userId;
    private Long id;
    private String title;
    private String body;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
```

6. Ahora definiremos un endpoint en la clase de BankingAccountController que se encarge de usar el metodo
que declaramos en la interfaz de FeignClient

``` java
	//The usage of FeignClient for demo purposes
	 @GetMapping("/getExternalUser/{userId}")
	 public ResponseEntity<Post> getExternalUser(@PathVariable Long userId) {

			 Post postTest = accountsJSONClient.getPostById(userId);
			 LOGGER.info("Getting post userId..." +postTest.getUserId());
			 LOGGER.info("Getting post body..." +postTest.getBody());
			 LOGGER.info("Getting post title..." +postTest.getTitle());
			 postTest.setUserId("External user "+randomAcountNumber());
			 postTest.setBody("No info in accountBalance since it is an external user");
			 postTest.setTitle("No info in title since it is an external user");
			 LOGGER.info("Setting post userId..." +postTest.getUserId());
			 LOGGER.info("Setting post body..." +postTest.getBody());
			 LOGGER.info("Setting post title...."+postTest.getTitle());
			 HttpHeaders responseHeaders = new HttpHeaders();
			 responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
			 return new ResponseEntity<>(postTest, responseHeaders, HttpStatus.OK);
	 }
```

7. Lo que procedera sera correr la aplicacion usando un container (usando el Dockerfile)
que implementaremos. Crearemos un archivo desde la raiz del proyecto y que se llame Dockerfile

``` bash

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

```

8. Ejecutaremos las siguientes secuencias de comandos desde la Terminal apuntando en el directorio raiz de la aplicacion (donde se encuentra
nuestro Dockerfile):
(Prerequisito: necesitamos instalar Docker Desktop. La version para Windows se encuentra en: https://docs.docker.com/desktop/install/windows-install/.
Despues de instalarlo necesitaremos correrlo)

Con los siguientes comandos crearemos y se correra un MongoDB en un container

``` bash
docker pull mongo

docker run -d --name mongo-on-docker -p 27017:27017 mongo
```

9. Iremos al application.properties de nuestra aplicacion y habilitaremos la siguiente linea de codigo que se encuentra comentada.

``` bash
spring.data.mongodb.host=host.docker.internal
```

10.  Ejecutamos las siguientes sencuencias de comandos desde la Terminal apuntando en el directorio raiz de la aplicacion:

Con los siguientes comandos crearemos y se correra nuestra aplicacion LearningJava en un container llamado springapplication-on-docker

``` bash

docker build -t learningjava-jpa .

docker run -d --name springapplication-on-docker -p 8080:8080 learningjava-jpa
```

(Nota: Abrimos Docker Desktop y vemos que en el apartado de Containers/Apps aparecen los dos containers corriendo. Si hacemos
click en uno de ellos aparecera la terminal de cada container y los logs correspondientes de cada app.)

11. Vayamos a Postman, y tratemos de hacer los requests que ya estabamos habituados en hacer y es de notarse, que aun las apps estando corriendo en
el contenedor, los request se ejecutan sin mayor problema.


12. Ejecutemos el request http://localhost:8080/api/getExternalUser/1 desde Postman. Este hace uso del metodo definido en FeignClient.
Deberia de obtenerse un status de 200 en la peticion y una respuesta similar a la siguiente:

``` json
{
    "userId": "External user <random number>",
    "id": 1,
    "title": "No info in title since it is an external user",
    "body": "No info in accountBalance since it is an external user"
}
```



### Manejo de Ribbon

1. Ahora estudiaremos el uso de Ribbon, para el uso de load-balancing del lado del cliente. Pongamos la siguiente linea dentro del metodo getAccounts()

``` java
	@GetMapping("/getAccounts")
	public ResponseEntity<List<BankAccountDTO>> getAccounts() {
			LOGGER.info("The port used is "+ port);
			....
```

y dentro de la definicion de atributos dentro de la misma clase:

``` java
	@Value("${server.port}")
	private String port;
```

2. Paremos el servicio de los dos contenedores que aparecen en el Docker Desktop. Hay un simbolo de "bote de basura" que podemos hacerle click para detenerlos.


3. Comentemos de nuevo la linea de application.properties (ya que apartir de ahora ejecutaremos la app de forma convencional):

``` bash
#spring.data.mongodb.host=host.docker.internal
```

4. Corramos en tres instancias diferentes la aplicacion LearningJava. Una instancia debe correr en el puerto 8080, la otra en 8081, y la otra en 8082.
En IntelliJ hay una opcion en Run > Edit Configurations > Modify Options > Allow multiple instances. Hacemos la modificacion respectiva en application.properties
en server.port asignandole por cada corrida su puerto correspondiente

5. Ejecutemos la aplicacion LoadBalancer. Esta correra en el puerto 8989.

6. Ahora en Postman corramos el Request Load Balancer (http://localhost:8989/loadBalancingWithRibbon) y vemos que podemos obtener la informacion de cuentas de los usuarios. Solo que un request lo hara sobre el server con puerto 8080, el otro request con el server del puerto 8081, y uno tercero sobre el server del puerto 8082. En los logs de cada instancia podemos ver la leyenda
"The port used is <XXX> " cuando se haga la llamada del request al server correspondiente.

7. Paremos las 3 instancias de LearningJava y la instancia de LoadBalancer.



### Implementacion de Kafka

1. Actualizaremos el pom.xml del LearningJava.

``` xml
<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka-test</artifactId>
	<scope>test</scope>
</dependency>
```

Nota: Implementaremos la anotacion de EnableKafka en SpringBootApplication
	
``` java
	
package com.wizeline.maven.learningjava;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class LearningJavaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearningJavaApplication.class, args);
	}

}
```

2.  Implementaremos ahora Kafka en nuestra aplicacion.
Crearemos una clase llamada KafkaConsumer dentro de la carpeta com.wizeline.maven.learningjava la cual tendra el siguiente contenido.

``` java
package com.wizeline.maven.learningjava.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.wizeline.maven.learningjava.model.BankAccountDTO;

@Component
public class KafkaConsumer {

    @KafkaListener(id = "sampleGroup", topics = "useraccount-topic", containerFactory = "jsonKafkaListenerContainerFactory")
    public void consumeMessage(ConsumerRecord<String, List<BankAccountDTO>> cr, @Payload BankAccountDTO account) {
        System.out.println("Received: " + account.getUserName());
    }
}
```

3. Crearemos la clase **KafkaConfiguration** en el paquete **com.wizeline.maven.learningjava.configuration**

``` java
package com.wizeline.maven.learningjava.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<Object, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic sampleTopic() {
        return new NewTopic("useraccount-topic", 1, (short) 1);
    }

    // String Deserializer
    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Object, Object>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // Json Deserializer
    @Bean
    public ConsumerFactory<String, Object> jsonConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonConsumerFactory());
        return factory;
    }
}

```

4. En **BankingAccountController** hacemos autowiring de KafkaTemplate

``` java
@Autowired
private KafkaTemplate<Object, Object> template;
```

E incluiremos el siguiente snippet que es un endpoint que mandara el mensaje usando KafkaTemplate

``` java
@PostMapping(path = "/send/{userId}")
public void sendUserAccount(@PathVariable Integer userId) {
	List<BankAccountDTO> accounts = bankAccountService.getAccounts();
	BankAccountDTO account = accounts.get(userId);
	this.template.send("useraccount-topic", account);
}
```

5. Agregamos la clase **KafkaConsumer** dentro del package **com.wizeline.maven.learningjava.consumer**

``` java
package com.wizeline.maven.learningjava.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.wizeline.maven.learningjava.model.BankAccountDTO;


@Component
public class KafkaConsumer {

    @KafkaListener(id = "sampleGroup", topics = "useraccount-topic", containerFactory = "jsonKafkaListenerContainerFactory")
    public void consumeMessage(ConsumerRecord<String, List<BankAccountDTO>> cr, @Payload BankAccountDTO account) {
        System.out.println("Received: " + account.getUserName());
    }
}
```

6. Corremos nuestra aplicacion de manera convencional (sin docker) y ejecutamos el request que se llama "Kafka". Vemos que en los logs
de la aplicacion de Springboot se imprime "Received: userX@wizeline.com".

# :books: Para aprender mas
* [Spring Initializr](https://start.spring.io/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring REST](https://spring.io/projects/spring-restdocs)
* [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Maven Repository](https://mvnrepository.com/)
