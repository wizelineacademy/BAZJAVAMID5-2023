# :tv: Video y Presentacion
- [TBD]
- [TBD]
- [TBD]
# Spring Security
# :hammer_and_wrench:  Requisitos
- Java 11
- IDE
    * [Visual Studio Code](https://code.visualstudio.com/download)
    * [IntelliJ](https://www.jetbrains.com/idea/download)
- [Postman](https://www.postman.com/downloads/)

# :pencil: Actividad
> Esta actividad toma como base la última versión del proyecto **LearningJava**
## Habilitar Spring Security
1. Lo primero que tenemos que realizar es agregar las dependencias de _Spring Security_ y _JWT_ en el archivo pom de nuestro proyecto.

    ![img.png](images/spring-security-dependency.png)
    > La dependencia de Swagger no es necesario agregarla para habilitar Spring Security, sin embargo, nos puede ser de mucha utilidad y facilitarnos la documentación y gestión de proyectos.
      En el apartado de recursos se proporciona documentación para su mejor entendimiento.
2. Generar clase de configuración de _JWT_ con los métodos para generar y validar el token de autenticación.
    ```java
   @Component
   public class JwtTokenConfig {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenConfig.class);


   @Value("${jwt.secret}")
   private String secret;
   
   /**
     * Este método genera el token de autenticación.
     * @param userDTO Información del usuario autenticado.
     * @param claims Información adicional del usuario que se agrega al token.
     * @return Regresa el token de autenticación.
     */
    public String generateToken(UserDTO userDTO, Claims claims) {
        return Jwts.builder()
                .setSubject(userDTO.getUser())
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(5).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
   
   /**
     * Validación del token utilizado durante la autenticación.
     * @param token Token de autenticación.
     * @return Regresa verdadero o falso dependiendo si es un token válido.
     */
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT Token expirado", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token es null o vacío", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT no valido", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT no soportado", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Falló la validación de la firma");
        }
            return false;
        }
   }
    ```
   > Recuerda incluir la anotación _@Configuration_ a la clase. En este caso especifico _secret_ se utiliza como llave para generar y descifrar tokens.Esta puede ser
     definida como una constante en la misma clase o bien inyectarla desde el archivo properties.
3. A continuación creamos una clase de tipo _controller_ la cual nos ayudara a generar una solicitud de creación de un token de autenticación. En este punto
   haremos uso de anotaciones (@Tag) de _Swagger_ para ir generando la documentación de la aplicación.
    ```java
   @Tag(name = "Authentication",
        description = "Genera token de autenticación.") 
   public class AuthenticationController {
        
        // Inyectar dependencias
   
        @PostMapping("/authenticate")
        public ResponseEntity<?> getAuthenticationToken(@RequestBody UserDTO userDTO) {
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(userDTO.getUser());
            } catch (UsernameNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
            }
            Claims claims = Jwts.claims().setSubject(userDTO.getUser());
            claims.put("username", userDTO.getUser());
            String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
            claims.put("authorities", authorities);
            claims.put("date", new Date());

            String token = jwtTokenConfig.generateToken(userDTO, claims);
            return ResponseEntity.ok(token);
        }
   }
    ```

4. Lo siguiente sera generar un filtro que se ejecutara en cada request, la función de este filtro sera .
   obtener el token enviado por el usuario durante un request. 
   ```java
       @Component
       public class JwtTokenFilter extends OncePerRequestFilter {

       @Autowired
       private JwtTokenConfig jwtTokenConfig;

       @Value("${jwt.secret}")
       private String secret;

       private final String HEADER = "Authorization";

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
               throws ServletException, IOException {

           if(jwtExists(request)) {
               String token = getAccessToken(request);

               if (jwtTokenConfig.validateAccessToken(token)) {
                   Claims claims = validateToken(token);
                   setUpSpringAuthentication(claims);
               }
           }
           filterChain.doFilter(request, response);
       }

       /**
        * Obtiene el token de acceso desde el header del request.
        * @param request Petición del usuario, debe incluir header Authorization.
        * @return Regresa únicamente el token, sin la palabra Bearer
        */
       private String getAccessToken(HttpServletRequest request) {
           String header = request.getHeader(HEADER);
           String token = header.split(" ")[1].trim();
           return token;
       }

       /**
        * Válida el token ingresado en el request.
        * @param token token ingresado en el header del request.
        * @return Regresa si el token es válido o no.
        */
       private Claims validateToken(String token) {
           return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
       }

       /**
        * Válida si se ha ingresado un token en el header del request.
        * @param request Petición por parte del usuario.
        * @return Regresa si hay un token.
        */
       private boolean jwtExists(HttpServletRequest request) {
           String authenticationHeader = request.getHeader(HEADER);
           if (authenticationHeader == null)
               return false;
           return true;
       }

       /**
        * Genera la autenticación del usuario y agrega sus roles.
        * @param claims Información adicional del usuario (roles).
        */
       private void setUpSpringAuthentication(Claims claims) {
           List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                   .commaSeparatedStringToAuthorityList(claims.get("authorities").toString());

           UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                   grantedAuthorities);
           SecurityContextHolder.getContext().setAuthentication(auth);

       }
   }
   ```
    > El token se envia en el _header_ del request. Si el request se hace desde _Postman_ es necesario anteponer la palabra Bearer seguidade un 
      de un espacio en blanco antes del token. Si el request se hace desde Swagger solo se introduce el token.
    
    > Ejemplo de request desde _postman_: curl --location --request GET 'http://localhost:8080/api/getAccountByUser?user=user1@wizeline.com' \
--header 'Authorization: Bearer token.

5. Crear clase de configuración donde se definen que recursos estarán segurizados y cuáles no, en este ejercicio la clase
    también nos sirve para generar usuarios y sus roles en memoria que nos ayudaran a simular escenarios de recursos con seguridad.
    ``` java
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {
        // Inyectar dependencias
        
        /**
        * Configuración de la seguridad del servicio.
        * @param httpSecurity
        * @return
        * @throws Exception
        */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
           return httpSecurity.cors().and().csrf().disable()
                .authorizeRequests().antMatchers(whiteList).permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }
        
        /**
        * Este Bean genera los usuarios que pueden hacer uso del servicio.
        * @return Regresa y habilita los usuarios asi como su información (user, password, rol).
        */
        @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
           List<UserDetails> userDetailsList = new ArrayList<>();
           userDetailsList.add(User.withUsername("user").password("password")
                .roles("USER").build());
           userDetailsList.add(User.withUsername("admin").password("password")
                .roles("ADMIN", "USER").build());
           userDetailsList.add(User.withUsername("guest").password("password")
                .roles("GUEST").build());

           return new InMemoryUserDetailsManager(userDetailsList);
        }
    }
    ```
   > En esta clase existe una variable llamada whiteList, es un arreglo que contiene la lista de _endpoints_ que no tienen seguridad.
     Esta variable se puede inicializar en la clase misma o bien en el archivo properties.

6. Ahora definamos que _endpoints_ estarán disponibles para usuarios autenticados.
   ``` java
   public class BankingAccountController {
     
     @PreAuthorize("hasRole('USER')")
     @GetMapping(value = "/getAccountByUser", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<List<BankAccountDTO>> getAccountByUser(@RequestParam String user) {
        // Implementación
     }
     
     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping(value = "/getAccountsGroupByType")
     public ResponseEntity<Map<String, List<BankAccountDTO>>> getAccountsGroupByType() throws JsonProcessingException {
        // Implementación
     }
   
     @PreAuthorize("hasRole('GUEST')")
     @GetMapping("/sayHello")
     public ResponseEntity<String> sayHelloGuest() {
        return new ResponseEntity<>("Hola invitado!!", HttpStatus.OK);
     }
   }
   ```
   > El _endpoint_ sayHelloGuest() es nuevo, se agrega con fines demostrativos.

7. Para manejar la exception _AccessDeniedException_ lanzada en la validación del token vamos agregar una clase anotada 
   como _ControllerAdvice_.
   ``` java
    @ControllerAdvice
    public class ExceptionHandlerAdvice {
    
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
            return new ResponseEntity<>("Acceso denegado", HttpStatus.FORBIDDEN);
        }
    
    }
   ```
   > La anotación @ControllerAdvice nos permite manejar exceptions en toda la aplicación.

8. Como se mencionó al inicio se agregó la dependencia de _Swagger_ para documentar nuestra aplicación y poder realizar 
   pruebas de una forma más fácil. El resultado será similar a lo siguiente:
   ![img.png](images/swagger.png)
   > Como se puede observar, el _Swagger_ de nuestra aplicación estará accesible desde http://localhost:8080/swagger-ui/index.html.
   > Para lograr lo anterior es necesario lo siguiente.
   
    Generar una clase de configuración donde incluiremos la información que se mostrara. Algunos valores se obtienen del archivo _properties_. 
   ``` java
      @Configuration
      @OpenAPIDefinition(
        info = @Info(title = "${info.app.name}", version = "${info.app.java.version}",
        contact = @Contact(name = "Developer", email = "developer@wizeline.com",
         url = "https://www.wizeline.com/")),
         servers = {
           @Server(url = "http://localhost:8080", description = "Development"),
         })
      public class OpenAPIConfiguration {

      private final String SECURITY_SCHEME_NAME = "JWT Token";

      @Bean
      public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description(
                                        "Inserta el token generado. Se obtiene en el apartado de autenticación.")
                                .bearerFormat("JWT")));

       }
    }
   ```

9. El archivo _properties_ puede lucir similar al siguiente.
   ![img.png](images/properties.png)


10. A continuación se muestra el resultado de la implementación.

### En Postman
#### :computer: Request
![img.png](images/postman_tkn_guest.png)

#### :white_check_mark: 200 Response
![img.png](images/postman_guest_200.png)

#### :x: 403 Response
![img.png](images/postman_guest_403.png)

### Swagger
#### :computer: Request
![img.png](images/swagger_tkn_guest.png)

#### :white_check_mark: 200 Response
![img.png](images/swagger_guest_200.png)

#### :x: 403 Response
![img.png](images/swagger_guest_403.png)

> **can't parse JSON.  Raw result:** Puede ser solucionado implementando el médodo writeValueAsString()
Algo asi: _Json.mapper().writeValueAsString(response)_
 
