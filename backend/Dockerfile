FROM openjdk:21
LABEL authors="dcsuibian"

COPY target/*.jar /app.jar

EXPOSE 7311
ENTRYPOINT ["java", "-jar","/app.jar","--spring.profiles.active=production"]
