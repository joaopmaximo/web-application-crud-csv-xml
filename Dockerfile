FROM maven:3.9.4-eclipse-temurin-8-alpine AS build
COPY . .
RUN mvn clean package -Dskiptests

FROM bellsoft/liberica-openjdk-alpine:17
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]