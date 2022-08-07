FROM openjdk:16.0.1
WORKDIR /src/main/
COPY . /src/main
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /src/main/question-server.jar
EXPOSE 9595
ENTRYPOINT ["java","-jar","question-server.jar"]