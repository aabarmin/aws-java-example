# Going AWS Java

This repository presents a tiny application and a collection of scripts which
demonstrate what ways are available to deploy the Java app into AWS. 

There the following projects available:
* `services\discovery-server` - Spring Boot app with Eureka - service-discovery server.
* `challenges-provider` - the main app which goes to other providers and requests the 
  challenges for the given discipline. 
* `providers\provider-base` - Spring Boot starter which kicks-off all the necessary classes to 
  have a plain REST API for challenge providers (read more next about challenges).
* `providers\provider-math` - Spring Boot application which provides challenges in `math` 
  discipline. 
* `providers\provider-history` - Spring Boot application which provides challenges in `history` 
  discipline.

## Provider Spring Boot Starter

Actually, this is a very simple starter which starts a collection of components which read
challenges from the file set in the `challenges.classpath.resource` property in your 
`application.yml`. 

The provider exposes `/provide` endpoint, just send a POST request here to get a few challenges: 

```
POST http://localhost:8082/provide
Content-Type: application/json
Accept: application/json

{
  "discipline": "Math",
  "number": 1
}
```

The response is a collection of challenges in JSON format. 

## Challenges provider

This is the main application which exposes `/provide` endpoint. The request here should be sent 
in the following format: 

```
POST http://localhost:8080/provide
Content-Type: application/json

{
  "request": {
    "math": 1,
    "history": 1
  }
}
```

As you may see, the request contains an object where keys are disciplines, values - number of 
questions to request. When the request is parsed, the main challenges' provider goes to the service
discovery and resolves instances which can provide challenges for the given discipline. As soon as
instances are resolved, the target instance is selected randomly and asked to provide challenges 
for it. When all the services are requested, results are combined and returned. 

## Building and running locally

Execute the following command to build:

```shell
./gradlew clean build
```

You may run apps as Spring Boot apps using:

```shell
java -jar application.jar
```

# Going to AWS

See examples in `aws` folder. 