# Imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado
COPY target/*.jar app.jar

# Copiar la wallet desde la ruta en tu máquina local
COPY Wallet_fs3 /app/wallet/

# Exponer el puerto en el que se ejecutará el backend
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]