# Tipos de Datos

# Requisitos

- Java 11 o superior
- [IntelliJ](https://www.jetbrains.com/idea/download)
- [Cuenta en sonarcloud](https://sonarcloud.io/)
- Crear un nuevo repositorio en una cuenta personal con base al código de este ejercicio (carpeta demo)

# :pencil: Actividad

## Configurar cuenta en SonarCloud

- Importar proyecto desde GitHub
- Crear nueva organización si es necesario
- Seleccionar el proyecto a importar (Dar permisos si es necesario, únicamente al proyecto que acaban de crear)

Por defecto SonarCloud no permite tener proyectos privados, para efectos de este ejercicio vamos a manejar un proyecto público

- Ingresar al proyecto y en el menú lateral podrán ver “Information” allí encontrarán el “Project Key” y “Organization Key” (Se usarán más adelante)
- Acceder a la opción del menú lateral “Administration” > “Analysis method” allí ingresar al enlace “With other CI tools” y obtener “SONAR_TOKEN”
- Modificar el archivo build.gradle del proyecto y cambiar estas propiedades “sonar.projectKey” “sonar.organization” según corresponda
- Agregar “SONAR_TOKEN” como variable de entorno

## Configurar Jenkins

- Abrir un terminal y ubicarse en la carpeta donde estén guardando su ejercicio
- Ejecutar los siguientes comandos

```
docker pull jenkins/jenkins
mkdir jenkins_home
docker run -d -p 8080:8080 -p 50000:50000 -v $PWD/jenkins_home:/var/jenkins_home --name jenkins_container jenkins/jenkins
```

- Obtener la clave de acceso a jenkins ejecutando

```
docker exec jenkins_container cat /var/jenkins_home/secrets/initialAdminPassword
```

- Acceder a http://localhost:8080 y pegar el valor obtenido, con esto ya hay acceso a jenkins
- Una vez abierto jenkins instalar los plugins Git, SonarQube Scanner, Pipeline (Manage Jenkins > plugins)
- Reiniciar Jenkins (detener y volver a iniciar el contenedor de docker)
- Agregar credencial “SONAR_TOKEN” (Manage Jenkins > credential)

## Crear el pipeline

- En el menu lateral acceder a “New Item” y seleccionar pipeline
- Se abrirán las opciones de configuración y en la sección pipeline en definition seleccionar "Pipeline script from SCM"
- En SCM seleccionar Git(Si no se ha instalado el plugin de Git no va a ser visible esta opción)
- Especificar la rama en la que están trabajando o a la que le quieren configurar el pipeline
- En la sección Script Path colocar “Jenkinsfile” (Apunta a su archivo de Jenkins en el proyecto en la carpeta raíz).

En este punto ya deberían ver en el dashboard de jenkins el pipeline que acaban de crear.

## Ejecutar el pipeline

- Acceder al pipeline podrán ver un menú lateral en donde está la opción "Build Now", esta opción les permitirá ejecutar el pipeline

El objetivo de este ejercicio es aprender a como configurar un pipeline con Jenkins y SonarCloud como parte de un ejercicio típico de Integración continua en la que basandose en un archivo de jenkins va a ser posible ejecutar las pruebas que realicen en su código de forma automática. Es una buena práctica ya que el fin de las pruebas es demostrar el funcionamiento esperado del código en algunos escenarios. Así se asegura que la implementación de nuevos features o refactors en el código no van a dañar la funcionalidad actual.

# :books: Recursos

- [Inicio de sesión en SonarCloud](https://www.sonarsource.com/products/sonarcloud/signup/)
- [Jenkins Extension for SonarCloud](https://docs.sonarcloud.io/advanced-setup/ci-based-analysis/jenkins-extension-for-sonarcloud/)
