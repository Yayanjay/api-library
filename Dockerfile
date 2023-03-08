FROM openjdk:11-jre-slim-buster
WORKDIR /app
EXPOSE 3200
CMD ["pwd"]
COPY /target/api-library-0.0.1-SNAPSHOT.jar /app/api-library-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-library-0.0.1-SNAPSHOT.jar"]