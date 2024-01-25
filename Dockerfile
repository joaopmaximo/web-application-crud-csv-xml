FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app/
RUN mvn clean package

FROM eclipse-temurin:17-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/web-application.jar app.jar
CMD [ "java", "-jar", "app.jar" ]
