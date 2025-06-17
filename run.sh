set -e

if ! command -v mvn &> /dev/null; then
  echo "Maven is not installed or not in PATH"
  exit 1
fi

echo "🔧 Building the project with local Maven..."
mvn clean install

echo "🚀 Starting the Spring Boot application..."
mvn spring-boot:run