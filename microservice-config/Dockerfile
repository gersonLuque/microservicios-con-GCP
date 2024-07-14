# Usa una imagen base de Eclipse Temurin con JDK 17
FROM eclipse-temurin:17-jdk

COPY ./target/microservice-config-0.0.1-SNAPSHOT.jar microservice-config.jar
CMD ["java","-jar","microservice-config.jar"]