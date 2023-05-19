
# Deploying full app in K8s

Vamos a hacer un despligue en kubernetes de una aplicacion tipica en nuestro cluster de kubernetes. Esta aplicación esta construida sobre una app front end y otra app back end.
El front podra acceder al back end y viceversa.




## Archivos


Dentro de esta carpeta encontraremos 4 archivos que nos serviran para crear nuestro ambiente.

- mongo-configmap.yml
- mongo-deployment.yml
- mongo-express.yml
- mongo-secret.yml






## Deployment

Primero tenemos que crear nuestro config map.

```bash
  kubectl create -f mongo-configmap.yml
```


Creamos despues el secret que contendra nuestras credenciales a base de datos.

```bash
  kubectl create -f mongo-secret.yml
```

Podemos levantar nuestro back end.

```bash
  kubectl create -f mongo-deployment.yml
```

Por ultimo levantamos el front end.

```bash
  kubectl create -f mongo-express.yml
```

Con esto nosotros hemos levantado un cluster con dos apps que se comunican entre si y para poder validar podemos accerder a nuestro localhost.

```bash
  localhost:8081
```


