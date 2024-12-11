FROM openjdk:17-jdk-alpine
ADD target/*.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
