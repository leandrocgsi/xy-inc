# XY-Inc

[![Build Status](https://travis-ci.org/leandrocgsi/xy-inc.svg?branch=master)](https://travis-ci.org/leandrocgsi/xy-inc)
[![Build Status](https://circleci.com/gh/leandrocgsi/xy-inc.svg?&style=shield)](https://circleci.com/gh/leandrocgsi/xy-inc/)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?maxAge=2592000)](https://github.com/leandrocgsi/xy-inc/blob/master/LICENSE.txt)

# Quickstart

1. Install MongoDB (http://www.mongodb.org/downloads, unzip, run `mkdir data`, run `bin/mongod --dbpath=data`)
2. Build and run the app `mvn spring-boot:run`
3. Access the root resource `curl http://localhost:8080/api` and traverse hyperlinks.
4. Or access the location search directly `http://localhost:8080/api/model`.
5. Additionally Swagger gives you possibility you test your endpoints in your interface, that can be accessed by `http://localhost:8080/sdoc.jsp`.

# Technologies used

- Spring Boot
- MongoDB
- Swagger
- Circle CI
- Travis CI
- Embeded Tomcat
- JUnit

# Using services

* Creating a new model

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_01.png?raw=true)

* Show all models

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_02.png?raw=true)

* Deleting a model

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_03.png?raw=true)

* Inserting an item in a new model

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_04.png?raw=true)

* Updating an item from a model

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_05.png?raw=true)

* Show all items from a model

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_06.png?raw=true)

* Show an item from a model by ID

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/img_07.png?raw=true)

# Swagger API documentation 

Swagger is a simple but powerful representation of your RESTful API. With the largest ecosystem of API tooling on the planet, thousands of developers are supporting Swagger in almost every modern programming language and deployment environment. In January 1st 2016 the [Swagger Specification](http://swagger.io/) has been donated to the [Open API Initiative (OAI)](https://openapis.org/) and has been renamed to the [OpenAPI Specification](https://openapis.org/). With a Swagger-enabled API, you get interactive documentation, client SDK generation and discoverability. In this example you can see the documentation API in localhost adress:

```sh
http://localhost:8080/sdoc.jsp
```

As you can see Swagger provide a simple way to document US API's.

![Example Page](https://github.com/leandrocgsi/xy-inc/blob/master/img/swagger_documentation.png?raw=true)
