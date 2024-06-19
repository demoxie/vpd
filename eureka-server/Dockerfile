FROM openjdk:17-jdk-alpine

WORKDIR /app/eureka-server

COPY target/app.jar /app/eureka-server/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/eureka-server/app.jar"]
