FROM quay.io/quarkus/ubi-quarkus-native-image:23.1-jdk-17 AS build
WORKDIR /app
COPY --chown=quarkus:quarkus . /app
RUN ./mvnw package -Pnative

FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY --from=build /app/target/*-runner /work/application
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]