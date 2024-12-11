FROM openjdk:17-jdk-slim
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
