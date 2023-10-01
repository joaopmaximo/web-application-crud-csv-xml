# Etapa 1: Construir a aplicação usando Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação usando a imagem do JRE
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=build /app/target/projeto-0.0.1-SNAPSHOT projeto-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "projeto-0.0.1-SNAPSHOT.jar"]
