FROM maven:3-openjdk-15
MAINTAINER Guilherme Augusto Moreira Baldissera
VOLUME /tmp
RUN mkdir /bridge
WORKDIR /bridge
COPY . /bridge/
RUN mvn install -DskipTests

ENTRYPOINT ["mvn","spring-boot:run"]

EXPOSE 8080