# Build Stage
FROM maven:3.8.3-openjdk-17 as Build
COPY . .
RUN mvn clean package

# Package Stage

FROM openjdk:17-jdk-slim

COPY --from=Build /target/doaapiproject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]
