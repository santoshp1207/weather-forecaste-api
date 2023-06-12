FROM openjdk:8-jdk-alpine
MAINTAINER Santosh Pahariya
COPY target/weather-forecast-api-0.0.1-SNAPSHOT.jar weather-forecast-api.jar
ENTRYPOINT ["java","-jar","/weather-forecast-api.jar"]