FROM openjdk:21
WORKDIR /appContainer
COPY ./target/cardapp.jar /appContainer
EXPOSE 8081
CMD ["java", "-jar", "cardapp.jar"]