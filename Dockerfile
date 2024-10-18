# Use Maven image with OpenJDK 17 for building the application
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Build the project using Maven
RUN mvn clean install

# Use a lightweight JDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/lab2-1.0-SNAPSHOT.jar /app/lab2-1.0-SNAPSHOT.jar

# Run the JAR file
CMD ["java", "-jar", "/app/lab2-1.0-SNAPSHOT.jar"]
