
# MyHomeQuote – In-Memory User Score API

## ✅ Requirements

- JDK 11
- Maven 3.x

---

## ⚙️ Building and Running the Project

### 🔨 Build with Maven

To compile the project using Maven, execute:

```bash
mvn clean install
```

This will create the executable JAR in the `target/` directory.

### ▶️ Run the Application

After a successful build, run the JAR with:

```bash
java -jar target/myhomequote.jar
```
>  Alternatively, use the helper script:
```bash
./run.sh
```

You should see console output similar to:

```
Tomcat started on port(s): 8080 (http) with context path '/result'
Started MyHomeQuoteApplication
```

This means application is up and running.
---

## 📚 API Documentation (Swagger UI)

Once running, explore and test the API using Swagger UI:

🌐 [http://localhost:8080/result/swagger-ui/index.html](http://localhost:8080/result/swagger-ui/index.html)

You’ll find all endpoints, request/response formats, and can interact with the service live.

---

### 🧪 API Testing

A Postman collection is available to test and interact with the API endpoints.

**Collection path:** `provisioning/postman/MyHomeQuote.postman_collection`

To use the collection:

1. Open Postman
2. Click **Import**
3. Select the file from the path above
4. Use the predefined requests (e.g. `PUT /result/info/setinfo`) to test the API