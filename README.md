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

-Docker installed and running
-An OpenAI API key (sk-...)

### Run with Docker
```
docker build -t harrygd/ai-text-service .

docker run -e OPENAI_API_KEY=sk-your-key-here \
  -p 8080:8080 harrygd/ai-text-service
```


## API Usage

## API Usage

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
*format can be NOTES or DIALOGUE
```
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
