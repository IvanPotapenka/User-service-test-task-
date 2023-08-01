FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD /target/controller-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java","-jar","controller-0.0.1-SNAPSHOT.jar"]