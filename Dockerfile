#FROM java:8
FROM maven
MAINTAINER Guilherme Augusto Moreira Baldissera
VOLUME /tmp
RUN mkdir /bridge
WORKDIR /bridge
COPY . /bridge/
RUN mvn install -DskipTests

#COPY target/bridges-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["mvn","spring-boot:run"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080