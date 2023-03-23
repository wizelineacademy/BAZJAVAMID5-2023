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