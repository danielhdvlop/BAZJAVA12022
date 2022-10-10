FROM openjdk:11
MAINTAINER daniel.vargaslo@elektra.com.mx
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]