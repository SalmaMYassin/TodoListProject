FROM openjdk:8-jdk-alpine
COPY target/todo-0.0.1-SNAPSHOT.jar todo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/todo-0.0.1-SNAPSHOT.jar"]