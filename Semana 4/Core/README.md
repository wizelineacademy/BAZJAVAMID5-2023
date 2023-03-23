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

## Temario Día 2

### Definicion de anotacion ConfigurationProperties

Consumo de los valores de los properties files en un Componente

### Definicion de Bean y Component

Función e implementación de un Componente y de un Bean de una Clase Servicio en LearningJavaApplication

### Uso de la anotacion Validated/Autowired/Component

Para fijar constraints a nivel de grupo/clase e inicializar componentes de Spring


## Practica
La practica y ejercicios las podemos encontrar en el directorio de learningjava

### A continuación, se listaran los pasos para a seguir para la actividad de este módulo.

1. Necesitamos agregar las siguientes dependencias de Maven para el ejercicio del día de hoy
``` xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
		<version>2.7.4</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-configuration-processor</artifactId>
		<optional>true</optional>
	</dependency>
```

2. Necesitamos definir el bean de servicio (el objeto que es manejado a traves del contenedor de Spring IoC)
. En este caso el objeto es uno de tipo UserService. Este tiene que ser inyectado antes de nuestra funcion main de
LearningJavaApplication.Java

``` java
@Bean
public static UserService userService() {
    return new UserServiceImpl();
}
```

3. Asimismo vamos a crear una clase llamada EndpointBean que se encuentre dentro de un nuevo paquete com.wizeline.maven.learningjava.config
(Noten que el paquete com.wizeline.maven.learningjava ya existe por lo que solo bastaria crear un Package llamado config dentro de com.wizeline.maven.learningjava)

La clase se definiria como lo siguiente:

``` java
package com.wizeline.maven.learningjava.config;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
@Component
@Validated
@ConfigurationProperties(prefix = "consumers")
public class EndpointBean implements Serializable {
	@NotNull
	@NotBlank
	private String login;
	@NotNull
	@NotBlank
	private String createUser;
	@NotNull
	@NotBlank
	private String createUsers;
	@NotNull
	@NotBlank
	private String userAccount;
	@NotNull
	@NotBlank
	private String accounts;
	@NotNull
	@NotBlank
	private String accountsGroupByType;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUsers() {
		return createUsers;
	}
	public void setCreateUsers(String createUsers) {
		this.createUsers = createUsers;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getAccountsGroupByType() {
		return accountsGroupByType;
	}
	public void setAccountsGroupByType(String accountsGroupByType) {
		this.accountsGroupByType = accountsGroupByType;
	}
}
```

4. La anotacion de ConfigurationProperties en dicha clase tratara de buscar en application.yml el property de consumers y que los atributos
definidos en la clase de EndpointBean consuman lo que se tiene en el application.yml. Para ello, definamos dos archivos en la carpeta de resources
llamados **application.yml** y **application-dev.yml** cada uno teniendo el siguiente contenido:

**application.yml**
``` java
spring:
  application:
    name: LearningJava
  profiles:
    include: dev
```

**application-dev.yml**
``` java
consumers:
  login: '/api/login'
  createUser: '/api/createUser'
  createUsers: '/api/createUsers'
  userAccount: '/api/getUserAccount'
  accounts: '/api/getAccounts'
  accountsGroupByType: '/api/getAccountsGroupByType'
```

5. Definamos el Autowiring en LearningJavaApplication.java

``` java
@Autowired
private EndpointBean endpointBean;
```

6. Al final nuestro LearningJavaApplication.java debe lucir de la siguiente forma:

``` java
@SpringBootApplication
public class LearningJavaApplication extends Thread {

	private static final Logger LOGGER = Logger.getLogger(LearningJavaApplication.class.getName());
	private static final String SUCCESS_CODE = "OK000";
	private static String responseTextThread = "";
	private static String textThread = "";

	@Autowired
	private EndpointBean endpointBean;
	
	@Bean
   	public static UserService userService() {
        	return new UserServiceImpl();
    	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearningJavaApplication.class, args);

		LOGGER.info("LearningJava - Iniciado servicio REST ...");
		/** This class implements a simple HTTP server  */
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion ...";

		// logear usuario
		server.createContext("/api/login", (exchange -> {
			LOGGER.info(msgProcPeticion);
			ResponseDTO response = new ResponseDTO();
			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user = new UserDTO();
				user = user.getParameters(splitQuery(exchange.getRequestURI()));
				response = login(user.getUser(), user.getPassword());
				JSONObject json = new JSONObject(response);
				responseText = json.toString();
				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
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
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

		// crear usuario
		server.createContext("/api/createUser", (exchange -> {
			LOGGER.info(msgProcPeticion);
			ResponseDTO response = new ResponseDTO();
			String responseText = "";
			/** Validates the type of http request  */
			exchange.getRequestBody();
			if ("POST".equals(exchange.getRequestMethod())) {

				StringBuilder text = new StringBuilder();
				try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
					while(scanner.hasNext()) {
						text.append(scanner.next());
					}
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
					throw new ExcepcionGenerica("Fallo al obtener el request del body");
				}
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST - Create user");

				ObjectMapper objectMapper = new ObjectMapper();
				UserDTO user = objectMapper.readValue(text.toString(), UserDTO.class);

				response = createUser(user.getUser(), user.getPassword());
				JSONObject json = new JSONObject(response);
				responseText = json.toString();
				exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
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
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

		// Crear usuarios
		server.createContext("/api/createUsers", (exchange -> {
			LOGGER.info(msgProcPeticion);
			ResponseDTO response = new ResponseDTO();
			/** Validates the type of http request  */
			exchange.getRequestBody();
			if ("POST".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST");

				StringBuilder text = new StringBuilder();
				try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
					while(scanner.hasNext()) {
						text.append(scanner.next());
					}
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
					throw new ExcepcionGenerica("Fallo al obtener el request del body");
				}
				textThread = text.toString();

				LOGGER.info(textThread);
				LearningJavaApplication thread = new LearningJavaApplication();
				thread.start();

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

		// Consultar información de cuenta de un usuario
		server.createContext("/api/getUserAccount", (exchange -> {
			LOGGER.info(msgProcPeticion);
			Instant inicioDeEjecucion = Instant.now();
			ResponseDTO response = new ResponseDTO();

			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user =  new UserDTO();
				Map<String, String> params = splitQuery(exchange.getRequestURI());
				user = user.getParameters(params);
				// Valida formato del parametro fecha (date) [dd-mm-yyyy]
				String lastUsage = params.get("date");
				if (isDateFormatValid(lastUsage)) {
					// Valida el password del usuario (password)
					if (isPasswordValid(user.getPassword())) {
						response = login(user.getUser(), user.getPassword());
						if (response.getCode().equals(SUCCESS_CODE)) {
							BankAccountDTO bankAccountDTO = getAccountDetails(user.getUser(), lastUsage);
							JSONObject json = new JSONObject(bankAccountDTO);
							responseText = json.toString();
							exchange.getResponseHeaders().add("Content-type", "application/json");
							exchange.sendResponseHeaders(200, responseText.getBytes().length);
						}
					} else {
						responseText = "Password Incorrecto";
						exchange.getResponseHeaders().add("Content-type", "application/json");
						exchange.sendResponseHeaders(401, responseText.getBytes().length);
					}
				} else {
					responseText = "Formato de Fecha Incorrecto";
					exchange.getResponseHeaders().add("Content-type", "application/json");
					exchange.sendResponseHeaders(400, responseText.getBytes().length);
				}
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

		// Consultar información de todas las cuentas
		server.createContext("/api/getAccounts", (exchange -> {
			LOGGER.info(msgProcPeticion);
			Instant inicioDeEjecucion = Instant.now();
			BankAccountService bankAccountBO = new BankAccountServiceImpl();
			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
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

		// Consultar todas las cuentas y agruparlas por su tipo utilizando Programación Funcional
		server.createContext("/api/getAccountsGroupByType", (exchange -> {
			LOGGER.info(msgProcPeticion);
			Instant inicioDeEjecucion = Instant.now();
			BankAccountService bankAccountBO = new BankAccountServiceImpl();
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

		/** creates a default executor */
		server.setExecutor(null);
		server.start();
		LOGGER.info("LearningJava - Server started on port 8080");
	}

	@Override
	public void run() {
		try {
			crearUsuarios();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			throw new ExcepcionGenerica(e.getMessage());
		}
	}

	private void crearUsuarios() {
		try {
			String user = "user";
			String pass = "password";
			JSONArray jsonArray = new JSONArray(textThread);
			JSONObject userJson;

			ResponseDTO response = null;

			LOGGER.info("jsonArray.length(): " +  jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				userJson = new JSONObject(jsonArray.get(i).toString());
				response = createUser(userJson.getString(user), userJson.getString(pass));
				responseTextThread = new JSONObject(response).toString();
				LOGGER.info("Usuario " + (i + 1) + ": " + responseTextThread);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

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

	private static ResponseDTO login(String user, String password) {
		UserService UserService = userService();
		return UserService.login(user, password);
	}

	private static ResponseDTO createUser(String user, String password) {
		UserService UserService = userService();
		return UserService.createUser(user, password);
	}

	public static Map<String, String> splitQuery(URI uri) {
		Map<String, String> queryPairs = new LinkedHashMap<String, String>();
		String query = uri.getQuery();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8),
					URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
		}
		return queryPairs;
	}

	private static BankAccountDTO getAccountDetails(String user, String lastUsage) {
		BankAccountService bankAccountBO = new BankAccountServiceImpl();
		return bankAccountBO.getAccountDetails(user, lastUsage);
	}

}
```

7. A continuación, ejecutemos el proyecto y hagamos una prueba con los siguientes request:


* [LearningJavaSpring.postman_collection.json](./Postman/LearningJava.postman_collection.json)




# :books: Para aprender mas
* [Spring Initializr](https://start.spring.io/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring REST](https://spring.io/projects/spring-restdocs)
* [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Maven Repository](https://mvnrepository.com/)
