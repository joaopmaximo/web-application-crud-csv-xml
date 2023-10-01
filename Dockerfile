# Etapa 1: Construir a aplicação usando Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação usando a imagem do JRE
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/web-application-crud-csv-xml.jar web-application-crud-csv-xml.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "web-application-crud-csv-xml.jar"]
