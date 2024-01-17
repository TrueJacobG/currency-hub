FROM openjdk:17
FROM maven:3.9.6 AS builder
WORKDIR /app
COPY ./pom.xml /app/pom.xml
COPY src ./src
RUN mvn clean install
COPY ./target/currencyhub-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]