# MovieDB backend

Application that stores info about movies, users and their reviews.

# Component

## MovieDB - Aggregator Service

# Task

Application that stores info about movies, users and their reviews.

<details>
  <summary>Click to expand</summary>

Movie entity:
* Name (unique), issueDate, rating (double).

User entity:
* FirstName, lastName, age, username (unique).

MovieReview entity:
* User, movie, rating (integer from 1 to 10), comment.

You have 2 main services: we can call them gateway-service and aggregator-service.
Gateway-service is the main entrance of your application. Gateway-service does not have access to your main storage (where all data is stored), however it can call some caching storage for some GET operations. It can call aggregator-service to retrieve data, it can call any service to put data. It supports the following operations:
* GET – top 10 movies with the highest rating
* GET – user info by username
* GET – top 10 favorite user movies by username
* GET – movie rating by movie name
* POST – create new movie
* POST – create new user
* POST – add new movie review
* PUT – update movie review
* DELETE – delete movie review
* DELETE – delete user (all user reviews should be also deleted in 10 minutes)

Customer requirements:
- Each new/updated/deleted movie review should affect movie rating in 5 minutes.
- You can receive up 200 movie reviews per second.
- Load balancing should be supported.
- All movie rating calculation logic should be placed inside aggregator-service.

</details>

# Design

This application is a modular monolith that implements **Saga pattern with Event-Sourcing**.

The design addresses following concerns:
+ **Command-Query Responsibility Segregation (CQRS)** - aggregates which handle commands (`movie`, `movie-review`, `user`) are separated from read projection (`query-service`)
+ **Replay capability** - projection (`query-service`) can be rebuilt from event store (Kafka)
+ **Idempotent consumers** - consumers (`movie`, `movie-review`, `query-service`) are configured to handle duplicate messages in a fail-safe manner, but aim to leverage Kafka's Exactly-Once Semantics

Acknowledged but unaddressed concerns:
+ **Transactional Outbox Pattern** - would avoid dual write problem (synchronous DB + Kafka write) with potential lost events
+ **Snapshots** - avoid replaying millions of events
+ **Security**
+ **Observability**
+ **CI/CD**
+ **Unit/Integration test coverage**
+ etc. due to timebox constraints

## Tech stack

+ Java 17
+ Spring Boot Framework
  + Spring Web / WebFlux
  + Spring Data JPA
  + Spring Data R2DBC
  + Spring Kafka
  + Spring Validation
+ Gradle
+ PostgreSQL
+ Apache Kafka (Broker & Stream Processing)
+ JUnit, ArchUnit
+ Grafana K6 for quick load testing

## Architectural diagram

TODO

## REST API

TODO

# Build

## Default build & test

Prerequisites: Java 17

`./gradlew clean build`

## Build Docker image

Prerequisites: Docker environment

`./gradlew clean build jibDockerBuild`

## Run

`java -jar build/libs/mdb-aggregator-0.0.1.jar`

## Debug

`java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar build/libs/mdb-aggregator-0.0.1.jar`
