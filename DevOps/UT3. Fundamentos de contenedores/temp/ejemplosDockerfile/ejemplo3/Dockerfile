#Images base con Java
FROM openjdk:27-ea-oraclelinux9

#Directorio de trabajo dentro del contenedor
WORKDIR /app

#Copiamos el archivo java
COPY HolaMundo.java .

#Compilamos la aplicación
RUN javac HolaMundo.java


#Establecemos el comando por defecto para ejecutar en el contenedor
CMD ["java", "HolaMundo"]