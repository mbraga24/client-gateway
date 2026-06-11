# Client Gateway

A Spring Boot application that demonstrates user authentication, authorization, and client management using Spring Security, JWT, PostgreSQL, and REST APIs.

## Overview

This project implements a client management API with user authentication, JWT-based security, role-based authorization, email integration, request validation, and database persistence. It serves as a gateway layer for managing clients and secured application resources.

## Tech Stack

* Java 17
* Spring Boot 3.1.2
* Spring Security
* Spring Data JPA
* PostgreSQL
* JWT (JJWT)
* Spring Mail
* Hibernate Validator
* Spring Web
* Spring WebFlux
* Maven
* Lombok
* Log4j2

## Features

* User registration
* User login
* JWT token generation
* JWT token validation
* Role-based authorization
* Client management
* Email notifications
* Request validation
* PostgreSQL persistence
* REST API endpoints
* Secure authentication and authorization

## API Endpoints

| Method | Endpoint         | Description                                  |
| ------ | ---------------- | -------------------------------------------- |
| POST   | `/auth/register` | Registers a new user                         |
| POST   | `/auth/login`    | Authenticates a user and returns a JWT token |
| GET    | `/clients`       | Returns all clients                          |
| GET    | `/clients/{id}`  | Returns a client by ID                       |
| POST   | `/clients`       | Creates a new client                         |
| PUT    | `/clients/{id}`  | Updates an existing client                   |
| DELETE | `/clients/{id}`  | Deletes a client                             |
| POST   | `/email/send`    | Sends an email notification                  |

> Update the endpoints above to match the actual controllers implemented in the project.

## Database Configuration

The application uses PostgreSQL.

Default configuration:

```yml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/clientdb
    username: postgres
    password: password

  jpa:
    hibernate:
      ddl-auto: update
```

## Security

The application uses Spring Security with JWT authentication.

After a successful login, a JWT token is generated and returned to the client. The token must be included in the Authorization header of subsequent requests to access protected resources.

Example:

```http
Authorization: Bearer <jwt-token>
```

## Project Structure

```text
src/main/java/com/pnc
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
├── validation
└── SpringPncApplication.java
```

## Notes

This project was built as a learning project to practice Spring Boot enterprise application development concepts, including authentication, authorization, JWT security, PostgreSQL integration, email services, validation, REST APIs, and layered application architecture.

## Author

Marlon Braga
