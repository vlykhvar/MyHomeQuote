
# MyHomeQuote – In-Memory User Score API

## 🚀 Features

- `PUT /setinfo` – Submit a user's score for a level
- `GET /userinfo/{userId}` – Retrieve top 20 scores by a user across all levels
- `GET /levelinfo/{levelId}` – Retrieve top 20 users for a specific level
- All data is stored in memory (no database)
- Supports concurrent/multithreaded access
- Trim logic keeps only top 20 results per user and per level
- Swagger UI for API documentation

---

## ⚙️ Building and Running the Project

### 🔨 Build with Maven

To compile the project using Maven, execute:

```bash
mvn clean install
```

This will create the executable JAR in the `target/` directory.

> ✅ Alternatively, use the helper script:
```bash
./build.sh
```

### ▶️ Run the Application

After a successful build, run the JAR with:

```bash
java -jar target/myhomequote.jar
```
> ✅ Alternatively, use the helper script:
```bash
./run.sh
```

You should see console output similar to:

```
Tomcat started on port(s): 8080 (http) with context path '/result'
Started MyHomeQuoteApplication
```

This means your application is up and running.
---

## 📚 API Documentation (Swagger UI)

Once running, explore and test the API using Swagger UI:

🌐 [http://localhost:8080/result/swagger-ui/index.html](http://localhost:8080/result/swagger-ui/index.html)

You’ll find all endpoints, request/response formats, and can interact with the service live.

---

## ✅ Requirements

- JDK 11
- Maven 3.x

---