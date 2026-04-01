# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app

# 1. Copy file JAR từ stage build
COPY --from=build /app/target/*.jar app.jar

# 2. Copy file certificate vào thư mục làm việc (/app)
# Giả sử file này đang nằm ở thư mục gốc của project bạn
COPY global-bundle.pem ./global-bundle.pem

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]