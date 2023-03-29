# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container to /app
WORKDIR /

# Copy the packaged jar file into the container at /app
COPY target/xurl-0.0.1-SNAPSHOT.jar ./

# Expose port 8080 to the host machine
EXPOSE 8888

# Set the command to run the jar file when the container starts
CMD ["java", "-jar", "xurl-0.0.1-SNAPSHOT.jar"]
