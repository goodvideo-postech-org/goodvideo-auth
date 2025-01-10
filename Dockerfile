# Stage 1: Build the application
FROM maven:3.6.3-openjdk-17 AS build

# Copy the source code and the pom.xml file
COPY src /app/src
COPY pom.xml /app

# Set the working directory
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests=true

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar goodvideo.jar

# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "/goodvideo.jar"]