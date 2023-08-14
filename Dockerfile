

#Build stage

FROM gradle:latest AS BUILD
WORKDIR .
COPY . .
RUN gradle jar

# Package stage

FROM amazoncorretto:11-al2023-jdk
COPY . .
ENTRYPOINT ["java", "-jar", "build/libs/Calculator-1.0-SNAPSHOT.jar"]


