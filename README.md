## Spring Boot application

Proyecto Spring Boot con las dependecias / starters:
* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev DevTools

Aplicacion API REST con acceso a base de datos H2 para persistir la informacion.

El acceso se puede realizar desde Postman o Navegador.

## Entidad Laptop

1. LaptopRepository
2. LaptopController

   a. Buscar todas las laptops

## Despliegue de app Spring Boot en Heroku

Crear archivo 'system.properties' en el proyecto con el contenido
java.runtime.version=18

1. Tener cuenta en heroku.com
2. Configuracion Git en ordenador
3. Proyecto subido desde GitHub en Intellij IDEA desde VCS > share project on GitHub.
4. Desde Heroku, crear app y buscar repo.
5. Uso de deploy automatico y ejecucion de deploy.
6. Monitoreo desde Heroku 
7. Verificacion desde PostMan