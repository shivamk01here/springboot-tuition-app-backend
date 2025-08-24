
# 🎓 Tuition Management API (Spring Boot)

A **production-grade Tuition Management REST API** built using **Spring Boot**.  
This project is designed to showcase **best practices** such as:

- Layered architecture (Controller → Service → Repository)  
- DTO pattern for clean request/response handling  
- Swagger/OpenAPI documentation for API exploration  
- Global exception handling  
- Validation (JSR-380 / Hibernate Validator)  
- Logging & standardized API responses  
- Unit and integration tests  

---

## Features
- Manage **Students, Teachers, Courses, and Enrollments**  
- Secure REST endpoints with proper request validation  
- Clean separation of concerns using DTOs  
- Easy-to-use Swagger UI for testing APIs  

---

## Tech Stack
- **Java 17**  
- **Spring Boot 3.x**  
- **Spring Data JPA (Hibernate)**  
- **MySQL / PostgreSQL**  
- **Swagger (Springdoc OpenAPI 3)**  
- **Lombok**  
- **JUnit + Mockito**  

---

## Project Structure
```

src/main/java/com/example/tuitionapp
│── controller   # REST Controllers (expose APIs)
│── dto          # Data Transfer Objects
│── entity       # JPA Entities (database models)
│── repository   # Data access layer
│── service      # Business logic
│── exception    # Global error handling
│── config       # Swagger, security, other configs

````

---

## API Documentation
Swagger UI is available once the application is running:  

 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

This provides interactive documentation and allows you to test endpoints directly.

---

## Getting Started

### Clone the repository
```bash
git clone https://github.com/shivamk01here/springboot-tuition-app-backend.git
cd springboot-tuition-app-backend
````

### Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tuitiondb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### Run the application

```bash
./mvnw spring-boot:run
```

## 🧪 Testing

Run unit & integration tests:

```bash
./mvnw test
```

---

## 📌 Highlights for Recruiters

* Follows **Clean Architecture** principles
* DTOs for request/response separation
* Swagger docs for self-explanatory APIs
* Proper error handling & response standardization
* Includes testing (unit + integration)

---

## 📜 License

MIT License © 2025 \shivamkumar


