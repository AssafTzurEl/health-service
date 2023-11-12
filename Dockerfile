FROM docker.io/maven:3.8.3-eclipse-temurin-17 as build
WORKDIR /health-service
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM docker.io/eclipse-temurin:17-jre-alpine
WORKDIR /health-service
COPY --from=build /health-service/target/*.jar health-service.jar
ENTRYPOINT ["java","-jar","/health-service/health-service.jar"]