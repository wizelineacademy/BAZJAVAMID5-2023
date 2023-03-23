# :computer:  Actividades 

## Inicialización de segumiento a git y git status 

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
### 1. Crea una carpeta vacia. 
*Nota: puedes hacerlo desde tu interfaz grafica o utlizar el siguiente comando en la terminal*
```
mkdir <nombre-de-la-carpeta>
```
### 2. Inicializar el repositorio

Abre tu terminal y dirigete a la carpeta que acaba de crear en el paso 1 e inicializa git en tu carpeta.
```
cd <nombre-de-la-carpeta>
```
```
git init
```
### 3. Crear archivos
Dentro de tu carpeta crea dos nuevos archivos uno llamado 'teammates.md' y otro 'languages.md' 
*Nota: Estos archivos puedes hacerlos desde una interfaz grafica o puedes hacerlo desde tu terminal.*
```
touch teammate.md
```
```
touch languages.md
```
### 4. Agregar teammates.md al 'staging area' y crear tu primer commit.
```
git add teammates.md
```
``` 
git commit -m <mensaje>
```
> :bulb: Recuerda que el mensaje de tu commit debe de ser algo descriptivo. 

 ### 5. Verifica el estatus de tu repositorio.
```
git status
```
Para este punto indicaste que quieres darle seguimiento al archivo de teammates.md entonces en tu consola de debe de aparecer que al archivo de 
languages.md no le estas dando seguimeinto (untrack).

### 6. Modifica ambos archivos y verifica su estatus.

Realiza un cambio en ambos archivos, te suegrimos agregar un par de lineas en cada archivo, guardas cambios y despues de estas acciones repite el 
comando para ver el estaus de tus archivos.

``` 
git status
```
En este momento en tu terminal te debe de aparecer un mensaje indicado que el archivos de teammated.md ha sido modifcado y el archivo de languages.md 
debe de aparecer en la sección de  'Untracked files'.

Algo muy parecido a esto: 
```
Changes not staged for commit: \
  (use "git add <file>..." to update what will be committed) \ 
  (use "git restore <file>..." to discard changes in working directory) \ 
        modified:   teammates.md \
Untracked files: \
  (use "git add <file>..." to include in what will be committed) \
        languages.md \
no changes added to commit (use "git add" and/or "git commit -a")
```
### 7. Agrega los archivos al staging area y realiza un commit 
Puedes usar algun de las siguientes opciones para agregar los archivos. 
#### a) Para agregar uno por uno. 
```
git add languajes.md
```
``` 
git add teammates.md
```
o 
```
git add languajes.md, teammates.md
```
#### b) Para agregar todos juntos 
```
git add . 
```
#### c) para agregar según el tipo de archivo 
```
git add *.md
```
Realiza el commit 
```
git commit -m <mensaje>
```
Ahora vuelve a verificar el estatus de tu repositorio y veas que ya no se muestran cambios para agregar al staging area. 

### 8. Verifica el número de commtis realizacions junto con sus mensajes.
Para este punto deben de aparecerte dos commits con los mensajes que indicaste a cada uno en los pasos anteriores. 
```
git log
```

## Creación y sincronización de archivos locales a repositorio remoto.

*Nota: Para poder realizar el siguiente ejercicio es necesario tener un cuenta en [GitHub](https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home) 
y haber realizado el ejercicio anterior para poder subir los cambios a un repositorio remoto*

### 1. Crea un [nuevo repositorio](https://github.com/new) en tu cuenta de GiHub.

> :rotating_light: el nombre de tu repositorio debe de ser unico dentro de cuenta, debe de incluir una descripción y tiene que ser publico.

### 2. Asigas el repositorio remoto que acabas de crear con el repositorio local que creaste en el primer ejercicio.  
```
git remote add origin <url-del-repositorio-remoto
```
*La url del repositorio remoto debe de lucir similar a :  https://github.com/[nombre_de_usuario_de_Github]/[nombre_del_repositorio].git*

> "rotating_light" Es importante estar dentro del directorio de trabajo en donde inicalizamos con el git init. 

### 3 Verifica el repositorio remoto. 
```
git remote -v 
```

### 4 Renombras el branch.
Anteriormente la convención para nombrar el branch principal era *master* pero ahora se cambio a *main*. 
```
git branch -M main
```
### 5 Sube los cambios locales al repositorio remoto. 
```
git push - u origin main
```
### 6 Verifica que los cambios se subieron correctamente.  
Refresca la pagina de tu repositorio remoto en tu navegador.

### 7 Modifica tu repositorio remoto. 
Crea un nuevo archivo con lossiguientes pasos: 
#### 1 Ve a la opcion 'AddFile > create file'
#### 2 Nombrea al archivo como 'GitHub.md'
#### 3 Llena el formulario del final para realizar un nuevo commit desde otra fuente. 

### 8 Traer cambios del repositorio remoto.
```
git pull
```
### 9 Verifica qlos nuevos cambios. 
Dentro de tu carpeta local debe de aparecerte el archivo GitHub.md 


# :books: Para aprender mas 

* GIT: https://git-scm.com/            
* GITHUB: https://docs.github.com/es  
* CLAVE SSH: https://docs.github.com/es/authentication/connecting-to-github-with-ssh    
* DOBLE AUTENTIFICACIÓN: https://docs.github.com/es/authentication/securing-your-account-with-two-factor-authentication-2fa 
* GIT - ATLASSIAN https://www.atlassian.com/git 
