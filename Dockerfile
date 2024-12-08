# Option 1: Multi-Stage Build
# This stage builds the JAR file as part of the Docker image creation process.
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Option 2: Pre-Built JAR
# Uncomment this block if you already have a pre-built JAR file in the target directory.
# FROM openjdk:17.0.1-jdk-slim
# WORKDIR /usr/app
# COPY target/app.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]

# Default: Use the built JAR from Multi-Stage Build
FROM openjdk:17.0.1-jdk-slim
WORKDIR /usr/app
COPY --from=build /app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]