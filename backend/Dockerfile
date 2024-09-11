# Etapa 1: Build del JAR con Maven
FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copia el resto del código y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Verifica que el archivo JAR se haya creado
RUN ls -l /app/target

# Etapa 2: Ejecución del JAR
FROM openjdk:17-jdk-alpine

COPY target/*.jar crud-app.jar

# Activa el perfil 'default' y ejecuta la aplicación
ENTRYPOINT [ "java", "-jar", "crud-app.jar", "--spring.profiles.active=default" ]
