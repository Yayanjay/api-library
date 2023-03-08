FROM openjdk:11-jre-slim-buster
EXPOSE 3200
COPY /target/api-librarent.jar api-librarent.jar
ENTRYPOINT ["java", "-jar", "api-librarent"]