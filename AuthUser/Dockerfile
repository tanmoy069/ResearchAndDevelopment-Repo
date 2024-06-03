FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/auth-service.jar"]