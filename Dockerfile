FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/todo-0.0.1-SNAPSHOT.jar todo.jar
ENTRYPOINT ["java","-jar","todo.jar"]