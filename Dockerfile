
# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY target/Weather-forecast-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]