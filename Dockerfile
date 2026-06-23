FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests


FROM eclipse-temurin:17-jre
WORKDIR /app

RUN useradd -m springuser
COPY --from=build /workspace/target/*.jar ./app.jar
RUN chown -R springuser:springuser /app
USER springuser

EXPOSE 8085

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/app/app.jar"]