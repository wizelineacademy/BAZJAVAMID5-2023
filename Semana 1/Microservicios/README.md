# :computer:  Actividades

## Docker

### Antes de empezar :exclamation::exclamation:
*Nota: Antes de comenzar deberemos tener instalados docker, docker compose, kubectl y colima, o en su defecto el ambiente docker de nuestra preserencia 

``` 
MAC:
brew install colima
brew install docker
brew install kubectl
brew install docker-compose
```

```
WINDOWS
Install docker desktop or rancher
https://www.docker.com/products/docker-desktop/
https://docs.rancherdesktop.io/getting-started/installation/
```

```
LINUX
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
sudo docker run hello-world
```
para verificar que se instalaron correctamente puedes utilizar los siguientes comando.
```
colima -v
docker --version
docker-compose --version
```
### 1. Crea una carpeta vacia.
*Nota: puedes hacerlo desde tu interfaz grafica o utlizar el siguiente comando en la terminal*
```
mkdir <nombre-de-la-carpeta>
```
### 2. Crear el archivo de aplicación app.py

En la carpeta que acabas de crear crea el archivo app.py con el siguiente contenido
```
from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello():
    return 'Hello World! I have been seen'
```


### 3. Definimos el archivo de requerimientos 
Dentro de la misma carpeta crearemos un archivo llamado `requirements.txt`

*Nota: Estos archivos puedes hacerlos desde una interfaz grafica o puedes hacerlo desde tu terminal.*

```
touch requirements.txt
```
```
nano requirements.txt
```

Dentro de `requirements.txt` agregamos:
```
flask
```

### 4. Definimos el archivo `Dockerfile`

```
nano Dockerfile
```
Y ponemos lo siguiente dentro de `Dockerfile`

```
# syntax=docker/dockerfile:1
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
COPY . .
CMD ["flask", "run"]
```

### 5. Construimos la imagen en docker
```
docker build --tag python-docker .
```
En este punto el motor de docker abra definido el plano para la nueva imagen usando las instrucciones en el archivo DOckerfile

### 6. Listamos las imagenes que existen en nuestro ambiente local.

``` 
docker images
```
Estas son todas las imagenes que es posible instanciar en tu ambiente local como contenedores, si instalaste Rancher o Docker Desktop es probable que te encuentres con más imagenes de la que hemos construido, son parte del ambiente de dichos programas.

### 7. Creamos una etiqueta de la imagen
```
docker tag python-docker:latest python-docker:v1.0.0
```
Listamos de nuevo las imagenes, noten que ahora hay 2 etiquetas de las imagenes.

Importante: Etiquetar una imagen no crea una nueva imagen, solo nos permite referenciar a la misma imagen con un nombre distinto, al volver a lista las imágenes podremos ver que el id de las imágenes en ambas etiquetas es el mismo.

### 8. Removemos la etiqueta que acabamos de crear

```
docker rmi python-docker:v1.0.0
```

### 8. Instanciamos un contenedor de la imagen que creamos 

``` 
docker run python-docker
```

### 9. Intentamos llamar  ala imagen que creamos, ya sea de la terminal o del navegador

```
curl --request GET \
--url http://localhost:8080/
```

Notemos que la llamada ha sido rechazada, esto se debe a que la aplicacion intenta llamar un puerto que no está expuesto al host del contenedor

### 10. Listamos los contenedores

```
docker ps
```

### 11. Detenemos al contenedor, recordemos que el nombre puede variar dependiendo del resultado del comando de listado

``` 
docker stop trusting_beaver

# docker stop <nombre_contenedor>
```

### 12. Volvemos a instanciar, ahora exponiendo el puerto en el que está corriendo la aplicación

``` 
docker run --publish 8080:5000 python-docker

# docker run --publish <puerto_maquina_local>:<puerto_contenedor_docker>
```

Volvemos a probar la aplicacion

```
curl --request GET \
--url http://localhost:8080/

# o abrir el navegador en http://localhost:8080/
```

Detenemos de nuevo el contenedor

``` 
docker stop trusting_beaver

docker stop <nombre_contenedor>
```

### 12. Ejecutamos el contenedor en modo deatached 

Correr el contenedor en `segundo plano` 

``` 
docker run -d -p 8080:5000 python-docker
```

En este modo la sesion de terminal no quedará asociada al proceso de docker ejecutando el contenedor

Volvemos a detener el contenedor `docker stop <nombre_contenedor>`, listando primero para obtener el nombre

### 13. Reiniciaremos un contenedor

Listamos todos los contenedores, incluyendo aquellos que no están corriendo

``` 
docker ps -a
```

Reiniciamos el contenedor

```
docker restart trusting_beaver

# docker restart <nombre_contenedor>
```

### 14. Eliminamos el contenedor

``` 
docker rm trusting_beaver modest_khayyam lucid_greider

# docker rm <nombre_contenedor_1> <nombre_contenedor_2> <nombre_contenedor_3>
```

### 15. Ejecutamos el contenedor asignandole un nombre y un parametro de autolimpieza

``` 
docker run --rm -d -p 8080:5000 --name python-server python-docker

# docker run --rm -d -p 8080:5000 --name <nombre_a_asignar_contenedor> <nombre_imagen>
```

En este punto podemos detener el contenedor

### 16. Modificamos el archivo `app.py`, con el siguiente contenido:

``` 
import time

import redis
from flask import Flask

app = Flask(__name__)
cache = redis.Redis(host='redis', port=6379)

def get_hit_count():
    retries = 5
    while True:
        try:
            return cache.incr('hits')
        except redis.exceptions.ConnectionError as exc:
            if retries == 0:
                raise exc
            retries -= 1
            time.sleep(0.5)

@app.route('/')
def hello():
    count = get_hit_count()
    return 'Hello World! I have been seen {} times.\n'.format(count)
```

### 16. Agregamos redis al archivo de `requirements.txt`

``` 
flask
redis
```

### 17. Agregamos el archivo `docker-compose.yml

``` 
version: "3.9"
services:
  web:
    build: .
    ports:
      - "8000:5000"
  redis:
    image: "redis:alpine"

```

### 18. Ejecutamos el siguiente comando, el cual creará los servicios necesarios para la aplicacion

``` 
docker-compose up
```

### 19.Probamos la aplicacion en el navegador llamando la siguiente url:

``` 
http://localhost:8000/
```

Si actualizamos la pagina podremos ver que el contador de visitas se incrementa, eso debido a que se esta usando redis para persistir la informacion

### 20. Editamos el archivo compose para asignar una montaje al servicio web 

```
version: "3.9"
services:
  web:
    build: .
    ports:
      - "8000:5000"
    volumes:
      - .:/code
    environment:
      FLASK_ENV: development
  redis:
    image: "redis:alpine"
```

Este nuevo volumen monta el directorio actual en el host a la carpeta /code dentro del contenedor, lo que nos permite modificar el codigo al vuelo, sin necesidad de reconstruir la imagen, volvemos a correr los servicios usando:

```
docker-compose up
```

Notemos que el contador no se ha reiniciado, eso es porque redis se encarga de persistir esa informacion

Podemos modificar el mensaje del archivo app.py para que se distinto:

``` 
return 'Hello from Docker! I have been seen {} times.\n'.format(count)
```

Actualizamos la aplicacion en el navegador y podremos ver que el mensaje ha cambiado sin necesidad de reconstruir el contenedor

### 21. Comandos adicionales de docker compose que son útiles

Correr en modo deatached

``` 
docker-compose up -d
```

Servicios que están corriendo actualmente

```
docker compose ps
```

Diferentes comandos en algun servicio 

``` 
docker compose run
```

En este ejemplo se ejecuta en el servicio web env, que listara las variables de ambiente

```
 docker compose run web env
```

Detener los servicios, por ejemplo, cuando se inicien en modo deatached

```
docker-compose stop
```

Detener completamente los servicios y remover la data en los volumenes:

``` 
docker compose down --volumes
```

### Desafio, recordando la sesion pasada. 

Crea un fork del repositorio que fue proporcionado y en un nuevo branch sube el código de este ejercicio.

# :books: Para aprender mas

* Comandos y tutorial Docker: https://github.com/abduvik/just-enough-series/tree/master/courses/docker+docker-compose
* Docker: https://docs.docker.com/get-started/overview/
* Docker Compose: https://docs.docker.com/compose/

