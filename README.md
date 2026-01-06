# Knowledge Entry REST API

A small Spring Boot REST API for storing and retrieving **knowledge entries** (Articles and Videos).  
This project demonstrates polymorphic JPA modeling, REST endpoints, validation, error handling, and unit tests.

---

## 📌 Technology Stack

- Java 17
- Spring Boot 4.0.1
- Spring Data JPA
- H2 Embedded Database
- Spring Web / REST
- Spring Validation
- Maven
- JUnit 6 + Mockito for testing

---

## 🏗️ Domain Model

`KnowledgeEntry` (base class, polymorphic)  
- Fields: `id`, `title` (common)  

Subtypes:

1. **ArticleEntry**
   - Fields: `content`, `author`  
2. **VideoEntry**
   - Fields: `url`, `durationInMinutes`  

JPA inheritance: `@Inheritance(strategy = InheritanceType.JOINED)`

---

## 🚀 Running the Application


1. Clone the repository:

```bash
git clone https://github.com/n-dharurkar/KnowledgeEntry.git
cd KnowledgeEntry

2. Build & Run:

mvn clean install
mvn spring-boot:run

3. Access H2 Database:
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: 1234

4. Access the REST End points:

Method	Endpoint	Description	Auth Required
POST	/api/knowledge	Create a knowledge entry	
GET	/api/knowledge/{id}	Retrieve an entry by ID	
GET	/api/knowledge?keyword=...	Search/list entries by keyword	

5. Curl for POST

curl --location 'http://localhost:8080/api/knowledge' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E08DBC31990456A5F82E6B2CF328A3F2' \
--data '{
  "type": "ARTICLE",
  "title": "Spring Boot",
  "content": "Intro to pring Boot",
  "author": "Niraj"
}'


6. Project Structure

src/main/java/com/example/demo/
├── Controller/
│   └── KnowledgeEntryController.java
├── Service/
│   └── KnowledgeEntryService.java
├── Model/
│   ├── KnowledgeEntry.java
│   ├── ArticleEntry.java
│   └── VideoEntry.java
├── Repository/
│   └── KnowledgeEntryRepository.java
├── Dto/
│   └── KnowledgeEntryRequest.java
└── Exceptions/
    └── ResourceNotFoundException.java


