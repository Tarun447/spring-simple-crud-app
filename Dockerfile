FROM openjdk:17-jdk-slim
MAINTAINER "javacodewiz.com"
COPY  target/spring-database-service-0.0.1-SNAPSHOT.jar spring-database-service.jar
EXPOSE 9001
ENTRYPOINT [ "java","-jar","spring-database-service.jar" ]
