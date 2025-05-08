#FROM gradle:7.6.0-jdk17 AS build
#WORKDIR /home/gradle/project
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x gradlew
#RUN ./gradlew clean bootJar

FROM eclipse-temurin:17-jre
LABEL authors="VarenX5"
WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar
COPY build/libs/testProject-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]