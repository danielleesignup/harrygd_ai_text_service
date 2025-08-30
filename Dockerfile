# ---------------------------
# STEP 1: Build the application
# ---------------------------
FROM maven:3.9.5-eclipse-temurin-17 as builder

# Set working directory inside container
WORKDIR /app

# Copy only the pom and src (for rebuild caching)
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# ---------------------------
# STEP 2: Run the application
# ---------------------------
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in runtime image
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the service port
EXPOSE 8080

# Run the application with OpenAI key as env var
ENTRYPOINT ["java", "-jar", "app.jar"]
