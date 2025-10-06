# ---- STAGE 1: build with Maven ----
FROM maven:3.9.9-eclipse-temurin-24 AS build
WORKDIR /workspace

# Leverage cache for dependencies
COPY pom.xml ./
RUN mvn -q -B -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
RUN mvn -q -B clean package -DskipTests

# ---- STAGE 2: lightweight runtime ----
FROM eclipse-temurin:24-jre
WORKDIR /app

# Copy the fat jar
COPY --from=build /workspace/target/*.jar /app/app.jar

# Point Oracle driver to the mounted wallet path
ENV JAVA_TOOL_OPTIONS="-Doracle.net.tns_admin=/app/wallet"
ENV SERVER_PORT=8080

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
