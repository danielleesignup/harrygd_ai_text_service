#  AI Text Service

A lightweight Spring Boot microservice that generates structured **Korean meeting notes or dialogue** from user-provided keywords using OpenAI's GPT API.

Built as part of a real-world internship project, this service is Dockerized, tested, and ready for integration into a full Java + Vue.js stack.

---

##  Features

-  Generate formal notes or realistic dialogues from keywords (in Korean)
-  Exposed via a simple REST API (`POST /v1/generate`)
-  Validates input using JSR-380 annotations
-  Unit + integration tested with JUnit 5 and MockMvc
-  Fully containerized with Docker for production deployment

---

##  Sample Request / Response

###  POST `/v1/generate`

**Request:**
```json
{
  "keywords": ["효율성", "케어"],
  "format": "NOTES"
}

```
**Response:**
```json
{
  "generatedText": "1. 요양보호사는 효율성에 대해 논의했습니다..."
}

```
## Running the Service
### Prerequisites

-Docker Desktop installed and running
-Docker Compose v2+
-An OpenAI API key (sk-...) in .env file

### Running Locally 
#### Build and Start:
```
docker compose up --build
```
#### Restart only the app (keep DB running):
```
docker compose restart app
```
#### Stop containers but keep DB data:
```
docker compose down
```
#### Stop and remove everything (⚠️ wipes DB volume):
```
docker compose down -v
```

### Logs

#### Follow app logs:
```
docker compose logs -f app
```

#### Follow database logs:
```
docker compose logs -f db
```

### Database

#### Open a Postgres shell:
```
docker exec -it ai-postgres psql -U ai_user -d ai_text_db
```

#### List tables:
```
\dt
```

#### Query recent generations:
```
SELECT id, keywords, LEFT(generated_text, 40), created_at
FROM generations
ORDER BY id DESC
LIMIT 5;
```

#### Exit:
```
\q
```

#### Run a one-off query:
```
docker exec -it ai-postgres psql -U ai_user -d ai_text_db ^
  -c "SELECT id, keywords, LEFT(generated_text,40), created_at FROM generations ORDER BY id DESC LIMIT 5;"
```


## API Usage
### Health Check
```
curl.exe http://localhost:8080/actuator/health
```

### Endpoint: Generate Text
```
**POST** `/v1/generate`
```
#### Request Body
```json
{
  "keywords": ["budget", "timeline"],
  "format": "NOTES"
}

```
-format can be NOTES or DIALOGUE

## Testing
### Run all tests locally
```
mvn clean test
```
-Controller unit tests using @WebMvcTest
-End-to-end integration test with real OpenAI calls

## Project Structure
```
src/
├── main/java/com/harrygd/aitextservice/
│   ├── controller/GenerateController.java
│   ├── service/OpenAiTextGenerationService.java
│   ├── model/GenerateRequest.java, GenerateResponse.java, GenerateFormat.java
│   └── AiTextServiceApplication.java
└── test/java/com/harrygd/aitextservice/controller/
    ├── GenerateControllerTest.java
    └── GenerateControllerIntegrationTest.java
```

## License
This repo is public for portfolio purposes only and contains no proprietary code or data.
Built independently during an internship. It does not expose any internal company logic.
