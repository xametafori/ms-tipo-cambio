FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD ./target/tipodecambio-0.0.1-SNAPSHOT.jar tipodecambio.jar
ENTRYPOINT ["java","-jar","/tipodecambio.jar"]