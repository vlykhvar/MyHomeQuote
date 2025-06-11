#!/bin/bash

JAR_NAME="myhomequote.jar"

if [ -f "target/$JAR_NAME" ]; then
  echo "🚀 Starting application: $JAR_NAME"
  java -jar "target/$JAR_NAME"
else
  echo "❌ JAR file not found: target/$JAR_NAME"
  echo "💡 Try building the project first: ./build.sh"
   read -p "Press Enter to exit..."
    exit 1
fi