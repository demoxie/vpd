FROM openjdk:17-jdk-alpine

WORKDIR /app/transaction-service

COPY target/app.jar /app/transaction-service/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/transaction-service/app.jar"]
