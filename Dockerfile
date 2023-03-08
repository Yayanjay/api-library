FROM openjdk:11-jre-slim-buster

EXPOSE 3200

ADD target/api-library-0.0.1-SNAPSHOT.jar /api-library-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/api-library-0.0.1-SNAPSHOT.jar"]