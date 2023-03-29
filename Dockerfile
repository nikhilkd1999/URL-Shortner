FROM maven:3.8.4-jdk-11 AS build
WORKDIR /
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /
COPY --from=build /target/xurl-0.0.1-SNAPSHOT.jar .
EXPOSE 8888
CMD ["java", "-jar", "xurl-0.0.1-SNAPSHOT.jar"]
