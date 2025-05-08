FROM gradle:7.6.0-jdk17 AS build

RUN apt-get update && apt-get install -y curl
RUN curl -v https://repo.maven.apache.org/maven2/

WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x gradlew
RUN ./gradlew clean bootJar

FROM eclipse-temurin:17-jre
LABEL authors="VarenX5"
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]