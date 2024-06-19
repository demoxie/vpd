FROM openjdk:17-jdk-alpine

WORKDIR /app/config-server

COPY target/app.jar /app/config-server/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/config-server/app.jar"]
