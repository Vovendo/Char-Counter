# Spring Boot "Char Counter" Example Project

This is a sample Java / Maven / Spring Boot (version 3.1.3) application that can be used to count characters in a string.
validation service.

## How to Run

This application is packaged as a war which has Tomcat 10.1 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

1. Make sure you are using JDK 20 and Maven 3.x
2. You can build the project and run the tests by running ```mvn clean package```
3. Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/text-validator-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
## About the Service
### Endpoints

At the moment, the service has only one endpoint:

#### **POST** http://localhost:8080/char-counter/api/calculateCharacters

##### Request Body

```json
{
  "text": “Put the text you want to check here (the service will return the number of all characters that are in the string).”
}
```
### Example Requests

#### Correct Text Example

##### **POST** http://localhost:8080/char-counter/api/calculateCharacters

###### Request Body

```json
{
  "text": "aaaaabcccc"
}
```

###### Response Body

```json
{
  "countOfCharacters": {
        "a": 5,
        "c": 4,
        "b": 1
    }
}
```
