FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/SpisokTest-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/application.properties

CMD ["java", "-jar", "app.jar"]
