# spring-boot-camel

This application is developed on Spring Boot Framework. It receives address parameter in HTTP GET request via a REST interface, forwards it to an integration layer written on Apache Camel. 
The integration layer then sends this request to Google Geocoding API as an HTTP request, receives the response in XML format, maps the response to a Pojo object, and sends it back to the REST layer.
The REST layer converts this object into JSON and sends the response back to the client.

XML -> Pojo transformation is done by using JAXB library, and Pojo -> JSON transformation is done by Jsonpath

# How to call the service

Open a browser and send a GET request to the following URL

http://localhost:8080/location?address=Jacob Bontiusplaats 91018 LL, Amsterdam The Netherlands

The response will be

```
{"status":"OK","lat":"52.3714238","lng":"4.9269278","address":"Jacob Bontiusplaats, 1018 Amsterdam, Netherlands"}
```

# Building and running the service

Execute the command below:

```
mvn spring-boot:run
```

# Testing the service

There are 2 test cases, one is for the Camel component, the other is for the REST Component. Both tests can be run by typing:

```
mvn test
```
