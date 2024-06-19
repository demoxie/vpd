FROM openjdk:17-jdk-alpine

WORKDIR /app/account-service

COPY target/app.jar /app/account-service/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/account-service/app.jar"]
