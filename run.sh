#!/bin/bash

JAR_FILE=$(ls target/*.jar | head -n 1)

if [ -f "$JAR_FILE" ]; then
  echo "Running $JAR_FILE"
  java -jar "$JAR_FILE"
else
  echo "JAR file not found. Please build the project first."
  exit 1
fi