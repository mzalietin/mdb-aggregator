## Description

MovieDB - Aggregator

## Build

Prerequisites: Java 17

`gradlew clean build`

## Run

`java -jar application/build/libs/application-0.0.1.jar`

## Debug

`java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar application/build/libs/application-0.0.1.jar`
