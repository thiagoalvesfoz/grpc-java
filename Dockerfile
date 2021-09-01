########### COMPILER APPLICATION ##############
FROM maven:3.6-jdk-11-slim AS build

WORKDIR /workspace

COPY pom.xml /workspace
COPY src /workspace/src

RUN mvn clean package

########### REDUCE IMAGE - 670MB to  159MB ##############
FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.11_9

WORKDIR /app
COPY --from=build /workspace/target/grpc-java-jar-with-dependencies.jar .

ENTRYPOINT [ "java", "-jar", "grpc-java-jar-with-dependencies.jar" ]
CMD ["-help"]
