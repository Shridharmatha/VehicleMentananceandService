# Stage 1: Build the JAR file
FROM maven:3.8.6-openjdk-11-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR file
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/your-app-name.jar your-app-name.jar
ENTRYPOINT ["java", "-jar", "/VehicleMentananceAndService.jar"]
