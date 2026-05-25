# Fase 1: Compilazione con Maven e JDK 25
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /code

# Copia il pom.xml per fare il caching delle dipendenze
COPY pom.xml /code/
RUN mvn dependency:go-offline

# Copia il codice sorgente e compila il Fast-JAR di Quarkus
COPY ./src /code/src
RUN mvn package -DskipTests

# Fase 2: Esecuzione con JRE 25 (leggera e sicura)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /deployments

# Copia gli artefatti generati da Quarkus
COPY --from=build /code/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /code/target/quarkus-app/*.jar /deployments/
COPY --from=build /code/target/quarkus-app/app/ /deployments/app/
COPY --from=build /code/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080

# Ottimizzazioni per Java 25 su piani Free (es. Render 512MB RAM)
# Usiamo ZGC (Z Garbage Collector) che è fantastico in Java 25 per gestire la memoria in modo efficiente
ENV JAVA_TOOL_OPTIONS="-XX:+UseZGC -XX:MaxRAMPercentage=70.0 -Dquarkus.http.host=0.0.0.0"

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]