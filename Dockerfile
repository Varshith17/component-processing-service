FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD target/component-processing-0.0.1-SNAPSHOT.jar component-processing.jar
ENTRYPOINT ["java","-jar", "/component-processing.jar" ]