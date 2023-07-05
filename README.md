# HTTP Parser
This is a RESTful API app developed to parse an HTTP response status line and headers.

## Technologies Used
* Java 17
* Junit 5.9.3 and mockito
* Spring Boot 3.1.1
* Maven 3.9.2
* IntelliJ IDE
* Docker
* Postman(Optional)

## Project Architecture
* src/main/java/com/broadcom/parser/httpParser/HTTPParserApplication.java
  - Spring Boot App for bootstrapping and launching the application
* src/main/java/com/broadcom/parser/httpParser/controller/HTTPParserController.java
  - Control layer defining the API
* src/main/java/com/broadcom/parser/httpParser/model/HTTPResponse.java
  - Data model layer for the HTTP Response class
* src/main/java/com/broadcom/parser/httpParser/HTTPParserApplication.java
  - Service layer for processing the logic
* com/broadcom/parser/httpParser/HttpParserApplicationTests.java
  - Unit tests with tests for control and service layers

## Summary of API Specification
### Endpoint: Parse HTTP response
* Path: `/parser/parse`
* Method: `POST`
* Payload: A string representing HTTP response
* Response: JSON containing the HTTPResponse object

Description:
Takes in a raw text string as a payload returns a HTTPResponse JSON object. Please note that the Data does not survive.

Example Response:
```json
{
  "httpVersion":"1.0",
  "statusCode":200,
  "reasonPhrase":"OK",
  "validHeaders":4,
  "invalidHeaders":0
}
```

## Test
1. Clone this repository and run the app.
2. Open a new terminal and navigate into the project directory:
```
cd httpParser
```
3. Run this in the terminal within the 'httpParser' directory:
```
curl -X POST -H "Content-Type: text/plain" -d $'HTTP/1.0 200 OK\r\ncache-control: public\r\ncontent-length: 0\r\ncontent-type: image/svg+xml\r\ndate: Tue, 22 Jun 2021 22:24:42 GMT' http://localhost:8080/parser/parse
```
4. This should return
```json
{
  "httpVersion":"1.0",
  "statusCode":200,
  "reasonPhrase":"OK",
  "validHeaders":4,
  "invalidHeaders":0
}
```

## Run the Unit Test

1. Make sure Maven is installed locally
2. Make sure Docker is installed and running locally
3. Run this in the terminal within the 'Broadcom' directory:
```
docker run -it --rm -v $PWD:$PWD -w $PWD -v /var/run/docker.sock:/var/run/docker.sock maven:3.9.0-eclipse-temurin-17 mvn test
```