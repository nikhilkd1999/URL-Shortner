# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine-slim

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the container
COPY . /app/

# Give permission to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw package

# Expose port 8888
EXPOSE 8888

# Run the application
CMD ["java", "-jar", "./target/xurl-0.0.1-SNAPSHOT.jar"]
