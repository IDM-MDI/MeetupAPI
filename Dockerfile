FROM maven:3.8.3-openjdk-17 as build
COPY . /home/maven/src
WORKDIR /home/maven/src
RUN ["mvn", "install", "-Dmaven.test.skip=true"]

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /home/maven/src/web/target/web-1.0.jar web-1.0.jar

ENTRYPOINT ["java", "-jar","web-1.0.jar"]