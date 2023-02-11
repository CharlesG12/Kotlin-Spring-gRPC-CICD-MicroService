# Overview
A reactive server example written in Kotlin

# Dependencies
- Java 17
- Docker
- Docker Compose

# Technologies
- Kotlin
- Gradle
- Github Action
- Armeria: Reactive Web server framework
- Spring Boot: Dependency Injection
- Reactor: Reactive library for non-blocking asynchronous paradigm
- WebFlux: Reactive based Rest
- R2DBC: Reactive based SQL

# User Manual
Run the server in terminal, then login in swagger UI to test the rest api.

# Setup local Database
```shell
$ make create-postgres
```
localhost:5050
add new server in pgadmin, Host Name: host.docker.internal

# Build
```shell
$ make build
```

# Unit Test
```shell
$ make test
```

## Run the Server
```shell
$ make run-server
```

## Teardown local database
```shell
$ make teardown-postgres
```

## REST Swagger UI 
http://localhost:8080/swagger-ui/index 