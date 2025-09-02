# Use an OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

RUN apt-get update & apt-get install -y maven

RUN mvn clean package

# Copy the built jar to the container
COPY target/sistema-pagos-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]