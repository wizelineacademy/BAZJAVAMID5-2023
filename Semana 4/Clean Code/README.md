# :tv: Video y Presentacion
Videos
- Patrones de Diseño [TBD]
- Patrones Arquitecónicos [TBD]
- Patrones para Microservicios [TBD]

Presentación

- [Design Patterns II](https://docs.google.com/presentation/d/1SlG0b5ykQRk0Tbs1OAukqxFy9u3H8q6e62vfUoL7cl8/edit#slide=id.g24883409b0c_0_220)

# Clean Code - Patrones de Diseño II
  * [Requisitos](#hammerandwrench-requisitos)
  * [Ejercicio Adapter](#pencil-patrón-adaptador)
  * [Ejercicio Bridge](#pencil-patrón-bridge)
  * [Ejercicio Composite](#pencil-patrón-composite)
  * [Ejercicio Decorator](#pencil-patrón-decorator)
  * [Ejercicio Facade](#pencil-patrón-facade)
  * [Ejercicio Flyweight](#pencil-patrón-flyweight)
  * [Ejercicio Proxy](#pencil-patrón-proxy)


# :hammer_and_wrench:  Requisitos
- Compilador
   * Java 11 o superior
- IDE
    * [IntelliJ](https://www.jetbrains.com/idea/download)

# :pencil: Patrón Adaptador

Primero vamos a crear el conjunto de clases que nos proporcionará el escenario para la implementación del patrón Adaptador.

Crear clase Linea con un método draw público que reciba 4 variables

    x1 : Coordenada X del punto 1
    y1 : Coordenada Y del punto 1
    x2 : Coordenada X del punto 2
    y2 : Coordenada Y del punto 2

La función draw deberá imprima en consola el mensaje 

    "Linea del punto A(x1,y2), al punto B(x2,y2)" 

```java
class Line {
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Linea del punto A("+ x1 + "," + y1 +"), al punto B(" + x2 + "," + y2 + ")");
    }
}
```
Crear clase Rectangulo con un método draw público que reciba 4 variables
    x : Coordenada X de la esquina superior izquierda
    y : Coordenada Y de la esquina superior izquierda
    width: ancho del rectángulo
    height : alto del rectángulo.

La función draw deberá imprima en consola el mensaje 

    "Rectángulo con coordenada esquina inferior izquierda en el punto (x,y), ancho: width, alto: heigth"

```java
class Rectangle {
    public void draw(int x, int y, int width, int height) {
        System.out.println("Rectángulo con coordenada inferior superior izquierda en el punto (" + x + "," + y + "), ancho: " + width + ", alto: " + height);
    }
}
```

Crear clase Demo para probar la funcionalidad.

```java
public class Demo {
    public static void main(String[] args) {
        Object[] shapes = {new Line(), new Rectangle()};
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
        int width = 40, height = 40;
        for (Object shape : shapes) {
            if (shape.getClass().getSimpleName().equals(Line.class.getSimpleName())) {
                ((Line)shape).draw(x1, y1, x2, y2);
            } else if (shape.getClass().getSimpleName().equals(Rectangle.class.getSimpleName())) {
                ((Rectangle)shape).draw(x2, y2, width, height);
            }
        }
    }
}
```

La salida en la consola deberá ser la siguiente:

![output_before_adapater_demo.png](images%2Foutput_before_adapater_demo.png)

Ahora implementemos el patrón adapter.

Primero creemos una interface Shape que reciba 4 parámetros enteros que coinciden con la función draw de nuestras clase Line y Rectangle.

```java
interface Shape {
    void draw(int x, int y, int z, int j);
}
```

Crearemos el Adaptador para nuestra clase Linea

```java
class LineAdapter implements Shape {
    private Line adaptee;

    public LineAdapter(Line line) {
        this.adaptee = line;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        adaptee.draw(x1, y1, x2, y2);
    }
}
```

Crearemos el también el Adaptador para nuestra clase Rectangle

```java
class RectangleAdapter implements Shape {
    private Rectangle adaptee;

    public RectangleAdapter(Rectangle rectangle) {
        this.adaptee = rectangle;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        adaptee.draw(x, y, width, height);
    }
}
```
Finalmente creamos el Demo para probar la funcionalidad con los adaptadores.

```java
public class Demo {
    public static void main(String[] args) {
        Shape[] shapes = {new RectangleAdapter(new Rectangle()),
                new LineAdapter(new Line())};
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
        for (Shape shape : shapes) {
            shape.draw(x1, y1, x2, y2);
        }
    }
}
```
![output_after_adapter_demo.png](images%2Foutput_after_adapter_demo.png)

# Challenge:

Intenta crear una nueva clase Circulo, que reciba  3 parámetros enteros x, y, y radio, e imprima el siguiente mensaje:

"Circulo con coordenada central en el punto (x,y), y radio: radio"

La salida esperada en la consola sería:

![output_after_adapter_challenge.png](images%2Foutput_after_adapter_challenge.png)

# :pencil: Patrón Bridge

Crear interfaz DrawAPI.

```java
public interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}
```

Crear clases concretas que implmenten la interfaz DrawAPI

RedCircle:

```java
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}
```

GreenCircle:

```java
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}
```

Crear la clase abstracta Shape que usa la interfaz DrawAPI:

```java
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
```
Crear la clase concreta implementando la interfaz Shape:

```java
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
```

Crear la clase abstracta Shape que usa la interfaz DrawAPI:

```java
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
```

Finalmente creamos el Demo para probar la funcionalidad.

```java
public class Demo {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(100,100, 10, new RedCircle()),
                new Circle(100,100, 10, new GreenCircle())
        };
        for (Shape shape: shapes) {
            shape.draw();
        }
    }
}
```
![output_bridge_demo.png](images%2Foutput_bridge_demo.png)

# Challenge:

Extender la implementación para imprimir el siguiente output.

![output_bridge_challenge.png](images%2Foutput_bridge_challenge.png)

# :pencil: Patrón Composite

Crear clase de Employee con una lista de objetos Employee

```java
public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;
    
    public Employee(String name,String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Empleado :[ Nombre : " + name + ", departamento : " + dept + ", salario :" + salary+" ]");
    }
}
```

Utilice la clase Employee para crear e imprimir la jerarquía de empleados.

```java
public class Demo {
    public static void main(String[] args) {

        Employee CEO = new Employee("Juan","CEO", 30000);

        Employee headSales = new Employee("Roberto","Gerente de Ventas", 20000);

        Employee headMarketing = new Employee("Miguel","Gerente de Mercadotecnía", 20000);

        Employee clerk1 = new Employee("Laura","Mercadotecnía", 10000);
        Employee clerk2 = new Employee("Bob","Mercadotecnía", 10000);

        Employee salesExecutive1 = new Employee("Ricardo","Ventas", 10000);
        Employee salesExecutive2 = new Employee("Rob","Ventas", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //Imprimir todos los empleados de la organización
        System.out.println(CEO);

        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);

            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}
```
![output_composite_demo.png](images%2Foutput_composite_demo.png)

# :pencil: Patrón Decorator

Crear la interfaz Shape

```java
public interface Shape {
   void draw();
}
```

Crear la clase Rectanlge

```java
public class Rectangle implements Shape {

   @Override
   public void draw() {
      System.out.println("Shape: Rectangle");
   }
}
```

Crear la clase Circle

```java
public class Circle implements Shape {

   @Override
   public void draw() {
      System.out.println("Shape: Circle");
   }
}
```

Crear la clase abstracta ShapeDecorator que implementa interfaz Shape

```java
public abstract class ShapeDecorator implements Shape {
   protected Shape decoratedShape;

   public ShapeDecorator(Shape decoratedShape){
      this.decoratedShape = decoratedShape;
   }

   public void draw(){
      decoratedShape.draw();
   }	
}
```

Finalmente creamos el Demo para probar la funcionalidad.

```java
public class Demo {
public static void main(String[] args) {
Shape[] shapes = {new Circle(),  new RedShapeDecorator(new Circle()), new RedShapeDecorator(new Rectangle())};

        for (Shape shape: shapes) {
            shape.draw();
            System.out.print("\n");
        }
    }
}
```
![output_decorator_demo.png](images%2Foutput_decorator_demo.png)

# Challenge:

Extender la implementación para imprimir el siguiente output.

![output_decorator_challenge.png](images%2Foutput_decorator_challenge.png)

# :pencil: Patrón Facade

Crear la interfa Shape

```java
 public interface Shape {
    void draw();
}
```
Crear clases concretas que implementan la interfaz

Rectangle.java
```java
 public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
```
Square.java
```java
 public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
```
Circle.java
```java
 public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
```

Creamos la clase Facade ShapeMaker.java
```java
 public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
```
Finalmente creamos el Demo para probar la funcionalidad.

```java
 public class Demo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
```
La salida esperada es:
![output_facade_demo.png](images%2Foutput_facade_demo.png)


# :pencil: Patrón Flyweight


Crear la interfa Shape

```java
public interface Shape {
    void draw();
}
```

Creamos una clase concreta que implemente la interfz Shape
```java
 public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color){
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
```

Cree una fábrica para generar objetos Circle en función de la información proporcionada.
```java
 import java.util.HashMap;

public class ShapeFactory {
    
    private static final HashMap circleMap = new HashMap();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
```
Finalmente creamos el Demo para probar la funcionalidad.
```java
public class Demo {
    private static final String colors[] = { "Red", "Green", "Blue", "Red", "Black","White", "Black", "Blue", "Green",
            "Black","Red", "Green", "Black", "White", "Black","Red", "Green", "Blue", "White", "Black" };

    public static void main(String[] args) {

        for (String color: colors) {
            Circle circle = (Circle)ShapeFactory.getCircle(color);
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}
```

![output_flyweight_demo.png](images%2Foutput_flyweight_demo.png)

# :pencil: Patrón Proxy

Crear la interfaz Image
```java
public interface Image {
    void display();
}
```
Crear las imagenes concretas:

RealImage
```java
 public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
```
ProxyImage
```java
 public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

Finalmente creamos el Demo para probar la funcionalidad.
```java
 public class Demo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        //La imagen será cargada desde el disco.
        image.display();
        System.out.println("");

        //La imagen no será cargada desde el disco.
        image.display();
    }
}
```
![output_proxy_demo.png](images%2Foutput_proxy_demo.png)
