# About project:

General information
User service for application of preparing to job interviews

Tech stack

Java 21
Gradle
Spring Boot
Liquibase
PostgreSQL
Kafka

# First download

1. Download from the GitHub https://github.com/ByteB00k/ByteBook-user-service
2. Rebuild gradle dependencies
3. Add to configuration of IntellijIdea VM Options: -Dspring.profiles.active=dev;
4. Install docker (desktop version for Win or Mac)
5. Launch the local file compose for upping the db or by idea or by the terminal `docker-compose -f docker-compose.yml up`

# Testing of endpoints:

# Local launching:

## Type 1, backend service and environment in docker

1. Build docker image from the root: `docker build -t build/libs/ByteBook-user-service-0.0.1-SNAPSHOT.jar`
2. Run in the root `docker-compose up`
4. Go to Swagger - `http://localhost:8080/swagger-ui/index.html`

## Type 2, backend launch locally, but environment in the docker

1. Run in the root `docker-compose -f docker-compose.yml up`
2. Set VM Options: -Dspring.profiles.active=dev
3. Launch backend service
5. Go to Swagger - `http://localhost:8080/swagger-ui/index.html`

# Checking locally all test in the module ByteBook-user-service
1. ...

