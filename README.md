# 🚀 DAWe Desplegament d'aplicacions web (Docker pas a pas)
## 🌟 Introducció a Docker
https://dev.to/pwd9000/introduction-to-github-codespaces-building-your-first-dev-container-69l

### 🔹 Instal·lar Docker a Ubuntu
Veure instruccions a:  https://docs.docker.com/desktop/setup/install/linux/

O fer:

```
snap refresh
snap install docker
```

La màquina virtual requereix virtualització de hardware VT-x/AMD-V

### 🔹 Executar el primer docker
`docker run -d --name apache-container -p 8181:80 httpd:latest`

### 🔹 Docker hub
https://hub.docker.com/
https://hub.docker.com/_/httpd
https://hub.docker.com/_/wordpress

### 🔹 Executar un altre docker
`docker run -d --name apache-container -p 8181:80 httpd:latest`
`docker run -d --name some-wordpress -p 8081:80 wordpress`

Per què falla?

## 🌟 Comandes docker

### 🔹 Gestió de contenidors
|Comanda de Docker                                             |Descripció                                                                  |
|--------------------------------------------------------------|----------------------------------------------------------------------------|
|docker run -d -p <host_port>:<cont_port> --name <nom> <imatge>|Crea i inicia un nou contenidor en segon pla (-d) amb mapatge de ports (-p).|
|docker ps -a                                                  |Llista tots els contenidors (en execució i aturats).                        |
|docker stop <contenidor>                                      |Atura un contenidor en execució.                                            |
|docker exec -it <contenidor> bash                             |Executa una shell interactiva dins d'un contenidor.                         |
|docker rm <contenidor>                                        |Elimina un contenidor aturat.                                               |

El port assignat a un contenidor queda fixat.  Per canviar el por, s0ha d'eliminar el continidor i tornar a crear un de nou amb un nou bindig dels ports

### 🔹 Gestió d'imatges
|Comanda de Docker                                             |Descripció                                                                  |
|--------------------------------------------------------------|----------------------------------------------------------------------------|
|docker pull <imatge>                                          |Descarrega una imatge d'un registre (Docker Hub per defecte).               |
|docker images                                                 |Llista les imatges locals.                                                  |
|docker build -t <nom> .                                       |Construeix una imatge a partir d'un Dockerfile.                             |
|docker rmi <imatge>                                           |Elimina una imatge local.                                                   |


### 🔹 Gestió de xarxes
|Comanda de Docker                                             |Descripció                                                                  |
|--------------------------------------------------------------|----------------------------------------------------------------------------|
|docker network create --driver bridge <nom_xarxa>             |Crea una nova xarxa bridge definida per l'usuari.                           |
|docker network ls                                             |Llista totes les xarxes.                                                    |
|docker run --network <nom_xarxa> <imatge>                     |Crea un contenidor connectant-lo a la xarxa especificada.                   |
|docker network connect <xarxa> <contenidor>                   |Connecta un contenidor existent a una xarxa.                                |
|docker network disconnect <xarxa> <contenidor>                |Desconnecta un contenidor d'una xarxa.                                      |
|docker network rm <xarxa>                                     |Elimina una xarxa.                                                          |

### 🔹 Gestió de volums
|Comanda de Docker                                             |Descripció                                                                  |
|--------------------------------------------------------------|----------------------------------------------------------------------------|
|docker volume create <nom_volum>                              |Crea un nou volum anomenat.                                                 |
|docker volume ls                                              |Llista tots els volums locals.                                              |
|docker run -v <nom_volum>:<ruta_cont> <imatge>                |Crea un contenidor muntant el volum especificat a una ruta del contenidor.  |
|docker volume rm <volum>                                      |Elimina un volum.                                                           |
|docker volume prune                                           |Elimina tots els volums no utilitzats (no associats a cap contenidor).      |

## 🌟 Docker file

### 🔹 Executar el primer **Dockerfile**

### 🔹 Configurar el docker file (escriure això al fitxer Docker file)

```
# Utilitza l'imatge oficial de nginx (servidor web) en la seva versió Alpine (més lleugera)
FROM nginx:alpine
# Crea un arxiu index.html amb el contingut "Hola Mon"
RUN echo "<h1>Hola Mon</h1>" > /usr/share/nginx/html/index.html
# Inicia el servidor nginx
CMD ["nginx", "-g", "daemon off;"]
```

### 🔹 Crear l'imatge amb la seva etiqueta (Executar això a la línia de comandes)

```
# Crea l'imatge i l'etiqueta (atenció al punt, que indica on es troba el Dockerfile)
docker build -t helloworld:latest

# Instancia un contenidor a partir de la etiqueta y li pose el nom helloWorld-container
docker run -d --name helloworld-container -p 8383:80 helloworld:latest
```

### 🔹 Fer algunes proves:
1. Intentar aixecar altra vegada un contenidor amb les mateixes característiques
   Es pot fer, però amb altre nom, i a un altre port
2. Si volem que el mateix contenidor funcioni al port 8383?
   comandes: docker ps -a, docker kill, docker start docker container prune

### 🔹 Fer algunes modificacions al docker file
Imaginar que per qualsevol moiut, necessitem que el servidor web serveixi per un port diferent al 80
[...] //Tema del SED 

## 🌟 Reorganitzar el repositori
   - Arribat a aqquest punt, podem reorganizar el repositorio per a separar el que acabem de fer, del que vindrà
   - Crear una carpeta helloworld, y moure-ho tot allí
   - Matar tots els dockers, col·locar-se a la carpeta helloworld, y comprovar que encara podem crear l'imatge i desplegar el Docker
   - comandes: docker images, docker container, docker container ls, o list, con --all

## 🌟 Configurar un contenidor persistent
Els canvis que es fan al contenidor, només es mantenen dins del mateix contenidor, i es mantene després de fer un kill, o un stop, i en reiniciar el contenidor amb la comanda start, però és freque[...]

### 🔹 Diferència entre volumes y bind mount
Els volums es gestionen amb Docker.
Los bind mount són muntatges de directoris mé semblants als que fa el sistema operatiu

### 🔹 Opció 1: enllazar un volum "al vuelo"
   - provar de llençar un contenidor sense volumen, crear un arxiu a la carpeta /usr/share/nginx/html del contenidor,i comprovar que funciona accedint a aquest arxiu mitjançabnt el servidor web. Mat[...]
   - Fer el mateix amb un contenidor amb un colum muntat (veure més avall)
   - Comandes: docker volume ls, docker volume inspect [nombre o id del volumen], docker exec -it [nombre/id] sh (o verlo en el plugin de docker)
   - Executar amb volum: docker run -d --name helloworld-container -p 8383:80 -v hwc:/usr/share/nginx/html helloworld:latest
### 🔹 Opció 2: montar un directori "al vuelo"

## 🌟 **Docker compose**
Com es pot veure, l'instalació de contenidors fent servir només el prompt de la consola, comença a complicar segons les comandes que fem servir, i a mida que volem afegir més característiques a l[...]

### 🔹 Posar ho tot junt a un sol docker compose
Ara  muntarem un contenidor amb les mateixes característiques que el contenidor amb el que hem estat provatn, però ho farem configurant un arxiu docker-compose.ymlmontaremos un contenedor con las mi[...]

### 🔹 Afegir un segon container al mateix arxiu doker compose
Configuar un servidor amb servidor d'aplicacions, i servidor de base de dades

### 🔹 Extra: Mirar el cplugin de Visual studio code per a treballar amb contenidors docker
