FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY sample.db sample.db
ENTRYPOINT ["java","-jar","/app.jar"]