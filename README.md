# bridges

## Observation
- when it's running using docker, the mysql database will just persist the data inside the container, it's possible to use volumes from docker to persist the data on your machine.
- The application runs on a tomcat apache server by default

## Requirements to run the project inside the Docker
- docker [To install docker](https://docs.docker.com/engine/install/ubuntu/)
- docker-compose [To install docker-compose](https://docs.docker.com/compose/install/)

## To run docker container execute the following command:

### `docker-compose up`
- It will be running on [http://localhost:8080](http://localhost:8080)
- The docker-compose runs the database mysql and the java application.

## Requirements to execute the project
- jdk at least version 8
- maven
- mysql database running and set according to the application settings.

## To configured mysql database edit the application.properties file
- Current all the properties listed below has been set to run with docker setting
- spring.datasource.url is the datasource URL **jdbc:mysql://database_host:port/database_name?useSSL=false**
- spring.datasource.username is the database user name 
- spring.datasource.password is the database password 
- Just set all properties listed above according to your database settings
- There is no need to create the database tables, the app creates all automatically.

## To run the project Execute the following commands
1. `mvn install` (it will run the unit tests and create a coverage report)
2. `mvn spring-boot:run`
3. `mvn test` (it runs the unit tests and create a coverage report)
4. It will be running on [http://localhost:8080](http://localhost:8080)

### Coverage report will be in following directory
- target/site/jacoco/index.html

## Endpoints
- GET - localhost:8080/bridges
- POST - localhost:8080/bridges
- content json/application
 - example :
 - {
  "name": "Chicago",
  "height": 25.0,
  "width": 30.0,
  "length": 50.0,
  "latitude": 25,
  "longitude": 25.0
}
