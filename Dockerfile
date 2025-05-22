FROM openjdk:17-jdk-slim

# Install curl for health checks & remove unnecessary packages after installation
RUN apt-get update && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY target/*.jar app.jar

EXPOSE 9090

# Use a non-root user (ensure 1000 user exists inside container)
USER 1000

ENTRYPOINT ["java", "-jar", "app.jar"]
