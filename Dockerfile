FROM openjdk:21
WORKDIR /appContainer
COPY ./target/card-service.jar /appContainer
EXPOSE 8081
CMD ["java", "-jar", "card-service.jar"]