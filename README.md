
# Arepas RJ

This project is based on the development of a web application for a food business located in the southern area of the Aburrá Valley, Antioquia, Colombia.
 
This, through the "administrator" role, allows you to enter the products to be offered and the cost of delivery. At the same time, it allows you, through the role of "customer", to choose the products available to purchase, calculate shipping and pay online, allowing your order to arrive in perfect condition, according to your instructions to the corresponding place.

Additionally, the application allows you to establish a connection with customer service through your WhatsApp line, which is located on a floating banner in all views. This allows the client to be supported in any PQRS (Requests, Complaints, Claims and Suggestions) that may arise in the process.

## Installation
Installation of the project using Spring Initializr.

We enter the Spring Initializr page and fill in the requested information.
We choose the version of Java that we are going to use, the type of project (be it Maven, Kotlin, etc.)
Then you choose the version of Spring to use.

Once the versions have been chosen, we proceed to provide information such as the name of the project or the package.

Finally, the dependencies that we are going to implement in the project are downloaded.

After downloading the already built project, we enter the IDE of preference, configure the version to be used within the IDE, clean and build the project, and if all the configurations are correct it should work.
## Project structure
The project structure is made up of several folders where, in turn, folders or files are configured that make up
the project. Among the main folders are:

"Project Files" is where the pom.xml is stored, a file that contains the dependencies and versions of the project.

"Test Dependencies and Java Dependencies" this folder stores all the dependencies that the project uses and that Java requires to function.

"Source Packages" is the main folder where we run the project, create the classes, packages, and in general it is where all the logic is stored.

And finally, in the folders that we have the most contact with, there are "dependencies", which is where the front-end views are stored in case we are going to work on them from the same project, and where the properties are, which are the necessary configurations for the project, such as connecting to the database, assigning permissions, and all the main configurations that we need.

Inside the "Source Packages" folder is the main folder of the project, from where all the other packages that contain our application will be derived. Packages need to be derived from the parent package to allow data persistence.
## Use

Each role allows you to create a profile with your registration data through the implementation of a CRUD (Create, Read, Update, Delete).

Once the user has registered they can be "administrator" or "client".

With the "administrator" role it is possible to enter the products to be offered and addresses with all their characteristics (description, price, quantity), in turn, you can update them when necessary due to changes in business rules.

With the role of "customer" it is possible to review the list of available products, register home delivery data, online payment, review of orders placed and the possibility of contacting the customer service area via WhatsApp.
## Project status

The current status of the project is "In development".
## Credits

Miguel Eduardo Escobar
miguelescobarp03@gmail.com

Laura Ximena Limas Sarasty
laura.limas0207@gmail.com

Juan Pablo Restrepo Arnaiz
pabloarnaiz1616@gmail.com

# ESPAÑOL
## Arepas RJ

Este proyecto se basa en la elaboración de un aplicativo web de un emprendimiento de comida ubicado en la zona sur del Valle de Aburrá, Antioquia, Colombia.
 
Este a través del rol de "administrador" permite ingresar los productos a ofertar y coste de domicilio. A su vez, permite mediante el rol de "cliente", elegir los productos disponibles a comprar, cálculo de envío y pago en línea, posibilitando que su pedido llegue en perfectas condiciones, según sus indicaciones al lugar correspondiente. 

Adicionalmente, el aplicativo permite establecer conexión con servicio al cliente a través de su línea de whatsapp, que se ubica en un banner flotante en todas las vistas. Esto permite acompañamiento del cliente ante cualquier PQRS (Peticiones, Quejas, Reclamos y Sugerencias) que pueda surgir en el proceso. 

## Instrucciones de instalación

Instalación del proyecto con mediante Spring Initializr.

Entramos en la página de Spring Initializr y rellenamos la información solicitada. 
Escogemos la versión de Java que vamos a utilizar, el tipo de proyecto (sea Maven, Kotlin, etc)
Después se elige la versión de Spring que se van a usar.

Una vez elegidas las versiones, procedemos a dar la información como el nombre del proyecto, o del paquete.

Por último se descargan las dependencias que vayamos a implementar en el proyecto.

Después de descargar el proyecto ya construido entramos en el IDE de preferencia, se configura la versión que se va a utilizar dentro del IDE, limpiamos y costruimos el proyecto, y si todas las configuraciones están correctas debería funcionar.

## Estructura del proyecto

La estructura del proyecto se compone de varias carpetas donde a su vez están configuradas carpetas o archivos que conforman
el proyecto. Entre las carpetas principales están:

"Project Files" es donde se almacena el pom.xml, archivo que contiene las dependencias y versiones del proyecto.

"Test Dependencies y Java Dependencies" esta carpeta almacena todas las dependencias que utiliza el proyecto y que Java requiere para funcionar.

"Source Packages" es la carpeta principal donde ejecutamos el proyecto, creamos las clases, los paquetes, y en general es donde se almacena toda la lógica.

Y por último en las carpetas que más contacto tenemos está "dependencies" que es donde se almacenan las vistas front-end en caso de que vayamos a trabajarlas desde el mismo proyecto, y donde están las propiedades que son las configuraciones necesarias para el proyecto, como la conexión a la base de datos, asignación de permisos, y todas las configuraciones principales que necesitemos.

Dentro de la carpeta "Source Packages" está la carpeta principal del proyecto, desde donde se van a derivar todos los demás paquetes que contienen nuestra aplicación. Es necesario que los paquetes deriven del paquete principal para permitir la persistencia de los datos.
## Uso


Cada rol permite crear perfil con sus datos de registro por medio de la implementación de un CRUD (Crear, Leer, Actualizar, Borrar). 

Una vez el usuario se ha registrado puede ser "administrador" o "cliente".

Con el rol de "administrador" es posible ingresar los productos a ofertar y domicilios con todas sus caracteristicas (descripción, precio, cantidad), a su vez, puede actualizarlos cuando sea necesario por modificaciones de reglas de negocio. 

Con el rol de "cliente" es posible revisar la lista de productos disponibles, registrar datos de envío de domicilio, pago en línea, revisión de pedidos realizados y posibilidad de contacto con el área de servicio al cliente a través de whatsapp.

## Estado del proyecto

El estado actual del proyecto es "En desarrollo". 
## Créditos

Miguel Eduardo Escobar
miguelescobarp03@gmail.com

Laura Ximena Limas Sarasty
laura.limas0207@gmail.com

Juan Pablo Restrepo Arnaiz
pabloarnaiz1616@gmail.com
