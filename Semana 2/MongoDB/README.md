# MongoDB

## Pre-requisitos de la sesión en vivo
- Tener instalado [Docker](https://www.docker.com/)
- En caso de no tener instalado Docker, tener instalado [MongoDB](https://coder-solution-es.com/solution-es-blog/1227314)
- Revisar los conceptos básicos:

## Temario

### SQL vs NoSQL
Referencias:
* [IBM](https://www.ibm.com/cloud/blog/sql-vs-nosql)
* [DataScientest](https://datascientest.com/es/sql-vs-nosql-diferencias-usos-ventajas-y-inconvenientes)
* [MongoDB](https://www.mongodb.com/nosql-explained/nosql-vs-sql)

### Conceptos básicos
* Documentos
   * <https://www.mongodb.com/docs/manual/core/document/>
* Colecciones
   * <https://www.mongodb.com/docs/manual/core/databases-and-collections/>

[Documentos y colecciones en español](http://www.cantabriatic.com/introduccion-a-mongodb)

### Primeros pasos
Referencias
* [Instalación directa en Windows](https://www.adictosaltrabajo.com/2011/01/10/mongodb/#:~:text=Instalando%20MongoDB,pues%20ya%20lo%20hemos%20instalado.)
* [Instalación por plataforma](https://www.mongodb.com/docs/manual/administration/install-community/)
* [Instalación utilizando Docker](https://www.jorgehernandezramirez.com/2017/03/26/primeros-pasos-en-mongodb-instalacion-en-docker-find-y-aggregation/)

Comandos
* Levantar instancia de MongoDB
``` docker run --name mongodb -d mongo ```
* Conectarse a MongoDB
``` docker exec -it mongodb mongosh ```
* Mostrar bases de datos
``` show dbs ```
* Crear / Usar base de datos
``` use wizeline_baz_db ```
* Mostrar base de datos en uso
``` db ```
* Insertar documentos
   * Insertar un documento
``` db.proyecto.insertOne({"nombre":"workshop"}) ```

   * Insertar multiples documentos
``` db.proyectos.insertMany([ { nombre: "Proyecto multiple", responsables: ["CTO", "TL"], tipo: "multiple" }, { nombre: "Proyecto cancelado" }]) ```

* Datos de ejemplo

Sakila es un esquema de ejemplo de MySQL que se publicó hace algunos años.  Se basa en un sistema de alquiler de DVD.
En este repositorio se agregaron los datos publicados en esa base de datos
Para importar estos datos se pueden utilizar los siguientes comandos:
   * ```docker cp sakila/customers.json mongodb:/tmp/customers.json```
   * ```docker cp sakila/films.json mongodb:/tmp/films.json```
   * ```docker cp sakila/stores.json mongodb:/tmp/stores.json```
   * ```docker exec mongodb mongoimport -d sakila -c customers --file /tmp/customers.json```
   * ```docker exec mongodb mongoimport -d sakila -c films --file /tmp/films.json```
   * ```docker exec mongodb mongoimport -d sakila -c stores --file /tmp/stores.json```

### Operaciones CRUD
Referencia: [Operaciones CRUD](https://platzi.com/contributions/operaciones-crud-en-mongodb/)
* Consultar documentos
   * Consultar sin filtros
``` db.proyectos.find() ```

   * Consultar con filtros
``` 
db.proyectos.find({"cantidad": 1, "tipo": "single"})
db.proyectos.find({$or: [{"cantidad": 1}, {"tipo": "multiple"}]})
```

* Actualizar documentos
   * Actualizar un documento
```
db.proyectos.updateOne({ cantidad: 1 }, { $set: { tipo: "prueba" } })
```

   * Actualizar multiples documentos
```
db.proyectos.updateMany( { presupuesto: { $ne: 100 } }, { $set: { presupuesto: 100 } })
```

* Eliminar documentos
   * Eliminar todos los documentos
``` db.proyectos.deleteMany({}) ```

   * Eliminar documentos filtrados
``` db.proyectos.deleteMany({ cantidad: 1 }) ```

### Aggregation Pipeline
Primero creamos una nueva DB por nombre `BAZ-Project`

Crearemos una colección por nombre `employees` y agregaremos los siguientes registros

```js
db.employees.insertMany([
    { 
        _id:1,
        firstName: "John",
        lastName: "King",
        gender:'male',
        email: "john.king@abc.com",
        salary: 5000,
        department: { 
                    "name":"HR" 
                }
    },
    { 
        _id:2,
        firstName: "Sachin",
        lastName: "T",
        gender:'male',
        email: "sachin.t@abc.com",
        salary: 8000,
        department: { 
                    "name":"Finance" 
                }
    },
    { 
        _id:3,
        firstName: "James",
        lastName: "Bond",
        gender:'male',
        email: "jamesb@abc.com",
        salary: 7500,
        department: { 
                    "name":"Marketing" 
                }
    },
    { 
        _id:4,
        firstName: "Rosy",
        lastName: "Brown",
        gender:'female',
        email: "rosyb@abc.com",
        salary: 5000, 
        department: { 
                    "name":"HR" 
                }

    },
    { 
        _id:5,
        firstName: "Kapil",
        lastName: "D",
        gender:'male',
        email: "kapil.d@abc.com",
        salary: 4500,
        department: { 
                    "name":"Finance" 
                }

    },
    { 
        _id:6,
        firstName: "Amitabh",
        lastName: "B",
        gender:'male',
        email: "amitabh.b@abc.com",
        salary: 7000,
        department: { 
                    "name":"Marketing" 
                }
    }
])
```

#### Uso de `$match Stage`
`$match` es usado principalmente para seleccionar solo los documentos que hagan `match` dentro de una colección. Equivalente al metodo `find()`.
```js
db.employees.aggregate([ {$match:{ gender: 'female'}} ])
```

#### Uso de `$group Stage`
`$group` se usa para agrupar los documentos que sean especificados por el `_id` y devuelve un solo documento que contiene valores acumulados para cada grupo distinto.
```js
// El campo $grouputiliza _idpara calcular los valores acumulados de todos los documentos de entrada en su conjunto. La expresión { _id:'$department.name'}crea el grupo diferenciado en el campo $department.name. Como no calculamos ningún valor acumulado, devuelve los distintos valores de $department.name, como se muestra a continuación.
db.employees.aggregate([ 
    { $group:{ _id:'$department.name'} }
])

// Calculemos los valores acumulados para cada grupo. Ej. calcular el número de empleados en cada departamento
db.employees.aggregate([ 
    { $group:{ _id:'$department.name', totalEmployees: { $sum:1 } } 
}])
```
La siguiente pipeline de agregación contiene dos etapas:
```js
db.employees.aggregate([ 
    { $match:{ gender:'male'}}, 
    { $group:{ _id:'$department.name', totalEmployees: { $sum:1 } } 
}])
```
En el ejemplo anterior, la primera etapa selecciona a todos los empleados varones y los pasa como entrada a la segunda etapa $groupcomo entrada. Entonces, la salida calcula la suma de todos los empleados varones.

Calcular la suma de los salarios de todos los empleados varones en el mismo departamento.
```js
db.employees.aggregate([ 
    { $match:{ gender:'male'}}, 
    { $group:{ _id:{ deptName:'$department.name'}, totalSalaries: { $sum:'$salary'} } 
}])
```
En el ejemplo anterior, `{ $match:{ gender:'male'}}` devuelve todos los empleados varones. En la `$group stage`, tenemos una expresión de acumulador `totalSalaries: { $sum:'$salary'}` suma el campo numérico `salary` y lo incluye como `totalSalaries` en la salida de cada grupo.

#### Uso de `$sort Stage`

El `$sort` stage se usa para clasificar los documentos según el campo especificado en orden ascendente o descendente.

```js
db.employees.aggregate([
    { $match:{ gender:'male'}}, 
    { $sort:{ firstName:1}}
])
```
En el ejemplo anterior, el `$match` stage devuelve todos los empleados masculinos y pasa a la siguiente etapa `$sort`. La `{ $sort:{ firstName:1}}` expresión ordena los documentos de entrada por `firstName` campo en orden ascendente. El `1` indica el orden ascendente y `-1` indica el orden descendente.

La siguiente pipeline contiene tres etapas para ordenar los documentos agrupados:
```js
 db.employees.aggregate([
    { $match:{ gender:'male'}}, 
    { $group:{ _id:{ deptName:'$department.name'}, totalEmployees: { $sum:1} } },
    { $sort:{ deptName:1}}
])
```
Por lo tanto, puede usar el aggregation pipeline para obtener los documentos requeridos de la colección.


## Ejercicio de Aggregation Pipeline

Tomando como base el archivo `aggregation_excercise.json` e importandolo y creando la colección de `Movies` dentro de la base de datos, contesta lo siguiente:

```
1. Encuentra la pelicula con el rate mas alto por cada director
2. Encuentra el numero de peliculas dirijidas por cada director
3. Encuentra la pelicula con el rating mas alto, donde Ricky sea el director y Tom Hanks sea uno de los actores.
4. ¿Cuantas peliculas protagonizo Tom Hanks?
5. Muestra la lista de todos los actores que trabajaron con el director Jon What.
```

### Modelos y esquemas
Referencia: [MongoDB Arquitectura y modelo de datos](https://sitiobigdata.com/2017/12/27/mongodb-arquitectura-y-modelo-de-datos/#:~:text=Modelo%20de%20datos%20MongoDB)
### Índices
Referencia: [MongoDB: creación y utilización de índices](https://www.genbeta.com/desarrollo/mongodb-creacion-y-utilizacion-de-indices)
### Hashing
Referencia: [dbHash](https://www.mongodb.com/docs/manual/reference/command/dbHash/)
### Replica Set
Referencia: [Replication](https://www.mongodb.com/docs/manual/replication/)
### Mejores Practicas de Diseño
Referencia: [Diseño](https://www.mongodb.com/developer/products/mongodb/mongodb-schema-design-best-practices/)