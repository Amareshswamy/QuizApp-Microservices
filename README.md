# QuizApp-Microservices

## About - Microservices Quiz Application
This microservices-based application is designed to handle quiz management and question operations. It is built on microservices architecture, with each service handling a specific domain, such as quizzes and questions. The services are connected via an API Gateway and utilize Spring Cloud technologies for seamless communication and service discovery.

## Features
- **Quiz Service**: Manages the creation, retrieval, and submission of quizzes.
- **Question Service**: Handles CRUD operations for quiz questions.
- **API Gateway**: Routes external client requests to the appropriate microservices.
- **Service Discovery**: Uses a service registry to dynamically discover services.
- **Feign Client**: Simplifies communication between services.
- **Spring Cloud**: Ensures resilience, configuration, and monitoring across services.

## Microservices

### Quiz Service
- Responsible for managing quizzes.
- **Endpoints**:
  - `POST /quiz/create`: Create a new quiz.
  - `GET /quiz/{id}`: Retrieve quiz details by ID.
  - `POST /quiz/submit/{id}`: Submit a quiz and calculate the score.

### Question Service
- Responsible for managing questions.
- **Endpoints**:
  - `POST /question/add`: Add a new question.
  - `PATCH /question/update/{id}`: Update an existing question.
  - `DELETE /question/delete/{id}`: Delete a question by ID.
  - `GET /question/all`: Retrieve all questions.
  - `GET /question/category/{category}`: Retrieve questions filtered by category.

## Tech Stack
- **Java 21**
- **Spring Boot**
- **Spring Cloud** (Service Registry, Feign Client)
- **API Gateway**
- **PostgreSQL**
- **Postman**

## Architecture
- **API Gateway**: Acts as a single entry point for all external requests and routes them to the appropriate service.
- **Service Registry(Eureka)**: Each service registers itself with the registry, allowing dynamic discovery of available services.
- **Feign Client**: Used for inter-service communication, enabling one service to call another easily.
- **Spring Cloud**: Provides various tools for building and managing the microservices infrastructure.

## Endpoint Access
All service endpoints are accessed through the API Gateway. Example:
- `GET http://{localhost port}/question-service/question/all`
- `POST http://{localhost port}/quiz-service/quiz/create`
