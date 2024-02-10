# Usamos una imagen base de OpenJDK 17
FROM openjdk:17-alpine

# Establecemos el directorio de trabajo en /app
WORKDIR /app

# Copiamos el archivo JAR construido en el contenedor
COPY target/PruebaMicroServicio1-0.0.1.jar /app/app.jar

# Exponemos el puerto en el que se ejecuta la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
