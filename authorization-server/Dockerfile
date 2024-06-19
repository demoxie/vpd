FROM openjdk:17-jdk-alpine

WORKDIR /app/authorization-server

COPY target/app.jar /app/authorization-server/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/authorization-server/app.jar"]
