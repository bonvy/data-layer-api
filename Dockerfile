# Fase 1: Compilazione Nativa con GraalVM e Maven
FROM ghcr.io/graalvm/native-image-community:25 AS build
WORKDIR /code

# Installa Maven (GraalVM community non lo ha di serie)
RUN microdnf install -y maven

# Copia i file del progetto
COPY pom.xml /code/
RUN mvn dependency:go-offline

COPY . /code/

# Compilazione nativa (Crea un file eseguibile dentro target/)
RUN mvn package -Pnative -DskipTests \
    -Dquarkus.native.container-build=false \
    -Dquarkus.native.native-image-xmx=4g \
    -Dquarkus.native.monitoring=none \
    -Dquarkus.native.additional-build-args=-J-Xmx4g,--no-fallback

# Fase 2: Immagine finale microscopica (senza Java!)
FROM alpine:3.19
WORKDIR /work/

# Copia il binario nativo generato (si chiama di solito *-runner)
COPY --from=build /code/target/*-runner /work/application

# Permessi di esecuzione e configurazione porta
RUN chmod 775 /work/application
EXPOSE 8080

# Avvia direttamente il binario nativo
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]