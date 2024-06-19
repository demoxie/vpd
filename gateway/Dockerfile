FROM openjdk:17-jdk-alpine

WORKDIR /app/gateway

COPY target/app.jar /app/gateway/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/gateway/app.jar"]
