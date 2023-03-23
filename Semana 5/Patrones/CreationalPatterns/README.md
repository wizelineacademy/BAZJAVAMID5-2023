# Codigo Limpio

### Pre-requisitos de la sesión en vivo
- Tener un IDE instalado como [Visual Studio Code](https://code.visualstudio.com/download) o [IntelliJ](https://www.jetbrains.com/idea/download)

### Actividad
* 1.- Implementacion de un patron creacional dentro de la aplicacion.
* 2.- Identificar el uso de un patron creacional dentro del framework de Spring.

### Desafio para recordar el tema anterior

Los ejemplos que se vieron en clase se pueden encontrar dentro de la carpeta CreationalPatterns en este repositorio

1. La actividad consiste en completar el ejemplo de Abstract Factory que se vio en la clase, agregando los elementos de Logo y Checkbox.
2. Como actividad opcional para practicar, agregar un nuevo prototipo de avion al patron Prototype y crear clones.

## Codigo de los patrones creacionales
- En este repositorio encontraras un folder llamado "CreationalPatterns", el cual contiene la implementacion en codigo de cada patron creacional vistos en sesiones anteriores. 
- En el siguiente path [src/main/java](https://github.com/wizelineacademy/BAZJAVA12022/tree/main/4/PatronesDiseno/CreationalPatterns/src/main/java) encontraras el codigo de los 5 patrones creacionales divididos en carpetas. 

## Estructura de los patrones
- Cada patron de diseño esta estructurado de manera que tiene una clase llamada clase llamada Client.java, esta clase es la encargada de ejecutar el patron deseado.
- Dependiendo de la complejidad de cada patron creacional, puede tener una o mas clases adicionales a la clase principal.

### 1. Singleton (Simulando una conexion a base de datos)
- La clase [DatabaseConfig](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/singleton/DatabaseConfig.java) muestra la implementacion del patron singleton.
- La clase [Client](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/singleton/Client.java) es encargada de obtener la instancia.

### 2. Prototype (Creando clones de aviones)
- A partir de la interfaz [PlaneMold](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/prototype/PlaneMold.java) se implementaran las clases requeridas para hacer los clones/prototipos.
- Se crearon dos aviones [MiG28Plane](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/prototype/MiG28Plane.java) y [MigG29Plane](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/prototype/MigG29Plane.java) que implementan la interfaz indicada en el punto anterior.
- Ahora la clase [Cliente](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/prototype/Client.java) preguntara al usuario la cantidad de clones de tipo MiG28Plane a crear, finalmente se mostrara el hashCode de cada avion para corroborar que se crearon clones.

### 3. Factory Method (Enviando diferente tipo de notificaciones)
- Dentro del folder notifications se encuentra una interfaz llamada [Notification](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/notifications/Notification.java) acompañada de otras clases que indican los tipos de notificaciones disponibles, 
para este ejemplo se utilzan notificaciones de tipo [Email](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/notifications/EmailNotification.java), [Push](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/notifications/PushNotification.java) y [SMS](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/notifications/SMSNotification.java),
todas las notificaciones van a implementar a su padre la interfaz Notification, ya que compartiran cierta funcionalidad.
- Dentro del folder factory se encuentra la clase [NotificationFactory](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/factory/NotificationFactory.java) encargada de crear y regresar al cliente el tipo de notificacion deseada en base a un parametro, para este ejemplo una cadena de texto llamada "channel".
- La clase [Cliente](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/factorymethod/Client.java) simplemente llama a la fabrica de notificaciones indicandole el tipo de notificacion quiere enviar, delegandole a la fabrica todo el proceso de creacion.

### 4. Abstract Factory (Renderizado de una aplicacion)
- En este ejemplo se busca simular la renderizacion de una aplicacion que sea compatible con los diferentes sistemas operativos Linux, Windows y MacOs. Para ello se necesitan crear tres fabricas que a su vez seran encargadas de crear los diferentes tipos de elementos a renderizar.
- Dentro de la carpeta factories se encuentra una interfaz llamda [GUIFactory](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/factories/GUIFactory.java) y sus implementaciones correspondientes para cada OS.
- La idea es que cada factory [LinuxFactory](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/factories/LinuxFactory.java), [MacOSFactory](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/factories/MacOSFactory.java) y [WindowsFactory](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/factories/WindowsFactory.java) implemente de 
la interfaz y sean las encargadas de crear los elementos que corresponden a ese OS.
- Por ejemplo la fabrica de Windows sera encargada de crear el Button, Checkbox y Logo que sea compatible. Asi que se creara un folder llamado buttons con una interfaz [Button](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/buttons/Button.java) la cual contendra la funcionalidad en comun de un boton.
- Por lo que la clase Button tendra tres implementaciones [LinuxButton](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/buttons/LinuxButton.java), [MacOsButton](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/buttons/MacOsButton.java) y [WindowsButton](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/buttons/WindowsButton.java), 
lo mismo se tendra que hacer para el Checkbox y Logo, y demas elementos que se quisieran agregar.
- La clase [Application](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/Application.java) es la encargada de llamar al factory deseado para renderizar la aplicacion, teniendo un constructor parametrizado se le indica el tipo de factory que se desea y se encargara de crear los elementos:
```java
    public Application(GUIFactory factory) {
        button = factory.createButton();
        //Aqui se iran agregando los demas elementos como el checkbox y el logo
    }
```
- Finalmente la clase [Cliente](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/abstractfactory/Client.java) recibe la entrada del usuario sobre la aplicacion que quiere renderizar, para Windows, MacOs o Linux y crea la instancia de esa fabrica para poder crear la aplicacion del tipo de OS deseado y pintarla.

### 5 Builder
- Se consideraron dos implementaciones para el patron de builder, el ejemplo relacionado a la creacion de una casa es una implementacion que agrega un arquitecto o director que se encargara de construir la casa. Y el segundo ejemplo es una creacion de un usuario.

#### 5.1 Builder [(Casa)](https://github.com/wizelineacademy/BAZJAVA12022/tree/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/houseexample)
- Esta implementacion considera el tener un [arquitecto/director](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/houseexample/Director.java) que vas ser el encargado de indicarle al builder que construya la casa.
Por lo que el [Builder](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/houseexample/builders/HouseBuilder.java) funcionara como constructor, ademas de agregar otros builders dependiendo del tipo de casa que se quiera crear.
- Dentro de la carpeta rooms se encontraran los diferentes tipos de cuartos disponibles para construir la casa.
- Finalmente el [Cliente](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/houseexample/Client.java) es quien se encargara de hablarle al director para que construya la casa dependiendo de los requerimientos que se le indiquen.

#### 5.2 Builder [(Usuario)](https://github.com/wizelineacademy/BAZJAVA12022/tree/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/userexample)
- Este ejemplo es mas sencillo y mas utilizado dentro de la programacion, ya que nos indica como podemos crear un usuario con diferentes caracteristicas a partir de un Builder.
- Dentro de la clase [User](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/userexample/User.java) estaran los atributos requeridos para la creacion de este, ademas se encontrara una clase interna llamada Builder, esta es otra manera de implementar el patron builder.
- Ademas de que ya no estamos incluyendo un arquitecto o director para esta implementacion, esto dependera de las necesidades y requerimientos.
- Al final en el [Cliente](https://github.com/wizelineacademy/BAZJAVA12022/blob/main/4/PatronesDiseno/CreationalPatterns/src/main/java/builder/userexample/Client.java) se estan creando diferentes tipos de usuarios, ocultando la complejidad de la creacion.

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