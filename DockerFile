FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dskiptests

FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=banco_de_usuarios
ENV MYSQL_PASSWORD=password
ENV MYSQL_USER=myuser
COPY ./script.sql /docker-entrypoint-initdb.d/

FROM bellsoft/liberica-openjdk-alpine:17
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]