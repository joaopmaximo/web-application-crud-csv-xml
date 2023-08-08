FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dskiptests

FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD=PROD_DB_PASSWORD
ENV MYSQL_DATABASE=PROD_DB_NAME
ENV MYSQL_PASSWORD=PROD_DB_PASSWORD
ENV MYSQL_USER=PROD_DB_USERNAME
COPY ./script.sql /docker-entrypoint-initdb.d/

FROM bellsoft/liberica-openjdk-alpine:17
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]