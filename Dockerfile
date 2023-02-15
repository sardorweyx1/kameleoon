FROM eclipse-temurin:11-alpine
COPY target/kameleoon-0.0.1-SNAPSHOT.jar kameleoon.jar
ENTRYPOINT ["java","-jar","/kameleoon.jar"]