FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/*.jar pixelground-back.jar

ENTRYPOINT ["java", "-jar", "pixelground-back.jar"]
