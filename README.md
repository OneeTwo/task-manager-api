# Task Manager API

REST API for task management built with Spring Boot.

## Features

- User registration and login
- JWT authentication
- Role-based authorization
- Task CRUD operations
- Validation and exception handling
- Swagger/OpenAPI documentation
- PostgreSQL database
- Docker support

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Maven
- Docker
- Swagger/OpenAPI

---

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register user |
| POST | `/auth/login` | Login user |

### Tasks

| Method | Endpoint | Description |
|---|---|---|
| POST | `/tasks` | Create task |
| GET | `/tasks` | Get user tasks |
| PUT | `/tasks/{id}` | Update task |
| DELETE | `/tasks/{id}` | Delete task |
| GET | `/tasks/all` | Get all tasks (ADMIN only) |

---

## Swagger

Swagger UI:

```text
http://localhost:8081/swagger-ui/index.html
