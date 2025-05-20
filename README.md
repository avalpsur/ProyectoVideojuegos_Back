# ProyectoVideojuegos_Back
Back-End con Spring del Proyecto Integrado basado en una comunidad de videojuegos

# PRODUCCIÓN 
Para subir el proyecto a producción he creado una instancia EC2 en AWS en la cual he instalado las dependencias necesarias tanto para back (Java y Spring) como para front (Node y Angular) y he dockerizado ambos paquetes para poder lanzarlos en remoto. En cuanto a la base de datos, he creado dos archivos nuevos en el application, el application-dev.propertires, que ejecuta la base de datos en local para el desarrollo y hacer pruebas, y el application-prod.properties, que es la que se utilizará finalmente y trabaja desde AWS. Ambos modos se activan y desactivan desde el application.properties
