#!/bin/bash

echo "🔨 Building Spring Boot application..."
./mvnw clean package -DskipTests

BUILD_STATUS=$?

if [ $BUILD_STATUS -eq 0 ]; then
  echo "Build successful. JAR file: target/*.jar"
else
  echo "Build failed"
   read -p "Press Enter to exit..."
    exit 1
fi