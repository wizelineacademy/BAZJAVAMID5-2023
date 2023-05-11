# :computer:  Actividades 

## Iniacilaizacion y modificacion del repositorio local.

### Antes de empezar :exclamation::exclamation:
*Nota: Para poder realizar los commits es necesario utlizar los siguientes comandos para asingar un correo electronico y un nombre de usuario a tu cuenta de git* 

``` 
git config --global user.name = <nombre-del-usuario>
```
```
git config --global user.email = <correo-electronico>
```

>:bulb: El correo electronico debe de ser uno que este asociada a una cuenta de GitHub. 

para verificar que se actualizaron correctamente puedes utilizar el siguiente comando.
```
git config --global -l 
```
### 1. Descarger el codigo dentro del repositorio. 

Dirigirse al siguiente repositorio: https://github.com/GeorginaFraire/git-course y descargar el codigo. (⚠️ DESCARGAR NO CLONAR) 

https://github.com/GeorginaFraire/BAZJAVAMID5-2023/assets/97203617/89a858f8-5275-452f-86ff-ccf6e3ad9192

*_Nota: puedes continuar las instrucciones dentro del [README](https://github.com/GeorginaFraire/git-course#readme) del repositorio anterior o continuar dentro de este._*

### 2. Inicializar el repositorio

Descomprime el contenido que acabas de descargar en el paso 1.

Puedes abrir la terminal y redirigirte a la carpeta en donde se descomprimio el contenido.
```
cd <nombre-de-la-carpeta>
```
O puedes abrir la carpeta en VSCode.

Inicializa el repositorio.
```
git init
```

Renombra la rama princial
```
git branch -M main
```

### 3. Primer Commit

Agregamos el contenido al area de preparación (staging area) 
```
git add . 
```

Creamos el primer commit
```
git commit -m "commit inicial"
```

### 4. Visuzalizar differencias con comandos de git

Nota 💡 : Para ver las diferencias entre el ultimo estado conocido del repositorio en comparacion con lo que se esta trabajando actualmente tenemos el comando `git diff`

Agregamos un archivo con nuestro nombre miNombre.md y modificacion alguno de los ay existente y vemos las diferencias
```
git diff
```
Agregamos los cambios al area de preparación y volvemos a ver las diferencias.
```
git add .
```
```
git diff --staged
```
Creamos el siguiente commit. 
```
git commit -m "Se agrego archivo miNombre.md"
```
Modificamos el archivo miNombre.md agregando un pequeña presentacion de nosotros: Ej: _Me llamo Georgina y estoy en al presentación de git_
Creamos el siguiente commit 
```
git commit -am "Presentacion"
```

_Nota 💡 : el parametro `-am` es un forma de agrega y realizar el commit en un solo paso, esto sirve unicamente para modificaciones, no funciona en archivos nuevos_

### 5. Eliminar commit / Deshacer cambios con `git reset`

Dentro del archivo de Heroes vamos a agregar uno nuevo al final del archivo y creamos el commit 
```
git add heroes.md
```
```
git commit -m "Agregar a X heroe"
```

Dentro del archivo de villamos agregamos uno y creamos un commit 
``` 
git commit -am "Se agrego X villano"
```

Ver el historial de commit
``` 
git log
```
Puede agregar el siguiente [alias](https://gist.github.com/Klerith/0acf18bbece7923bcac55edb71b03c2b) y utilizarlo para ver el historial de commits. 
```
git lg
```

Eliminar le ultimo commit en donde agregamos a un villano. 
_Nota: para eso vamos a tener que copiar el id del commit que deseamos eliminar y sustituir `<commit_hash>` del codigo de abajo_
```
git reset --hard <commit-hash>
```

Puedes verificar que el commit seleccionado desarecio del historial de commit al revisarlo con:
```
git log
```
o 
```
git lg 
```

### 6. Creacion de ramas
Crear una rama llamada 'misiones', utilizando alguna de las distintas opciones
```
git branch misiones
```
_Nota 💡 : Si la rama se creo con `git branch` moverse a esa rama utilizando el `git checkout misisones` o `git switch misiones`._

``` 
git checkout -b misiones
```
```
git switch -c misiones
```

Agregar un archivo _misiones.md_ y crear un commit 
```
git add misiones.md 
```
```
git commit -m "Se agrego el archivo misiones.md"
```
Modificar el archivo de misiones.md, Ej: Salvar a ciudad Gotica, Arreglar el batimovil y crear otro commit
```
git commit -am "Modificacion de misiones.md"
```
Ver el historial del commit (`git log` | `git lg`) y comprobar que `main` se quedo atras en el historial por dos commits

### 7. Union de ramas 
_💡 Nota: Para poder unir las ramas tenemos que estar situados en la rama que queremos unir nuestros cambios, en este caso `main`._

Movernos a la rama main 
```
git checkout main
```
Realizamos la union de las ramas (misiones y main) 
```
git merger misiones
```
Eliminamos la rama misiones, ya no nos sera util
```
git branch -d misiones
```

### 8. Almacenamiento temporal del cambios

_Nota 💡: Supongamos que estás trabajando en una nueva función en tu proyecto, pero aún no has completado los cambios y necesitas cambiar de rama para solucionar un problema urgente._

Modificamos el archivo de villanos y guardamos esos cambios de manera temporal
```
git stash 
```
Modificamos el archivo de heores y guardamos esos cambios de manera temporal CON EL MENSAJE "HEROES"
```
git stash save HEROES
```
Modificamos el archivo de misiones y guardamos esos cambios temporalmente CON EL MENSAJE "MISIONES" 
```
git stash save MISIONES
```
Vemos el listado de cambios almacenandos temporalmente
```
git stash list
```
Aplicamos y eliminamos los cambios de villanos ( en dos pasos) 

```
git stash apply <stashId> 
```
``` 
git drop <stashId>
```
Aplicamos y eliminamos los cambios de herores ( en 1 paso).
``` 
git stash pop
```
o
```
git stash pop <stashId>
```
Vemos el listado de cambios almacenandos temporalmente y los eliminamos
```
git stash list 
```
```
git stash clear
```
Volvemos a ver el listado de cambios temporales y podemos comprobar que ya no estan disponibles
```
git stash list
```
Para finalizar agregamos un ultimo commit con los cambios que dejamos despues de sacarlos del stash.

### 9. Actualización de ramas

Creamos una nueva rama "misiones-V2" _SOLO LA CREAMOS 🚨_
```
git branch misiones-V2
```
Modificamos le archivo heroes.md, creamos un nuevo commit.
```
git commit -am "agregamos a x en heores.md"
```
Visualizamos el historial de commits
```
git log
```
o 
```
git lg 
```
Nos movemos a la rama que creamos anteriormente 
```
git checkout misiones-V2
```
o 
```
git switch misiones-V2
```
_Nota 💡: Se puede observar que la rama main tiene un commit extra que no esta dentro de la rama misiones-V2_

_⚠️ Para poder atualizar una rama con el `rebase` tenemos que estar situados en la rama que queremos actualizar_

Actualizamos la rama misiones-V2
```
git rebase main
```
Revisamos el historial de commit y podemos observar que ya estan a la par. 
```
git log
```
o 
```
git lg 
```
_Nota 💡: Si hubieramos tenido cambios (commits) que solo estuvieran en la rama misiones-rebase esos cambios se hubieran ido hasta el final del listado de commit_

Modificamos el archivo de misiones y creamos un commit 
```
git commit -am "Agremos x a misiones.md"
```
Modificamos el archivo de villanos y creamos un commit
```
git commit -am "Agregamos a X en villanos.md
```

Escenario 💡: _por alguna extraña razon necesitamos que los villanos que agregamos (30) esten en la rama principal, pero auno no tenemos que agregar las nuevs misiones, entonces tenemos que mover el (los) commit(s) en donde agregamos a los villamos a la rama principal_

Nos movemos a la rama princial
```
git checkout main
```
Revisamos el historial de commits y copiamos el hash/id del commit en donde agremos a los villamos
```
git log
```
o 
```
git lg 
```
Movemos/Copiamos el commit a al rama principal
```
git cherry-pick <commit_hash>
```
Extra ✚ : Podemos movernos a la rama misiones-V2 y eliminar el ultimo commit (donde agreamos a los villanos (30)) con un `git reset --hard`

### 10. Repositorio remoto

Vamos a crear un [Repositorio en GitHub](https://docs.github.com/es/get-started/quickstart/create-a-repo#create-a-repository)

![image](https://github.com/GeorginaFraire/BAZJAVAMID5-2023/assets/97203617/7e5ad6b0-59c7-4b2b-8f27-bb1d4c238129)

Agregamos un repositorio remoto al repo local en el que hemos estado trabajando 
```
git remote add origin <url>
```
![image](https://github.com/GeorginaFraire/BAZJAVAMID5-2023/assets/97203617/5dc46118-9d7a-4472-9313-15825dd05014)

Verificamos que se agrego de manera correcta
```
git remote -v 
```
Subimos los cambios del repo local al repo remoto
```
git push -u origin main
```
Recargamos la pagina de nuestro repositorio remoto.

# Listo! :tada:


# 📚 Para aprender mas:

https://git-scm.com/
https://learngitbranching.js.org/?locale=es_AR
https://docs.github.com/es/get-started/signing-up-for-github/signing-up-for-a-new-github-account
https://docs.github.com/es/get-started/quickstart/create-a-repo
