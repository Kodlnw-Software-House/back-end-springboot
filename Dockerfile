FROM maven:3.8.1-jdk-11-slim AS builder
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app
COPY src /usr/src/app/src
RUN mvn -f pom.xml clean package


FROM openjdk:11.0.11-9-jdk
COPY --from=builder /usr/src/app/target/*.jar backend-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","backend-service.jar"]