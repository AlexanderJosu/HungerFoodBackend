FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/HungerFoodBackend-0.0.1.jar HungerFoodBackend.jar
ENTRYPOINT ["java","-jar","/HungerFoodBackend.jar"]