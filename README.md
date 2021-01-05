# ZOLOZ API SDK IN JAVA
## Background
This repository provide a SDK to help the customer to integrate ZOLOZ SaaS API with ease.

## Build
### Prequisites
- JDK 1.8
- Maven >3.2.5

To build the project, simply execute following command from the root directory of the project:
```sh
mvn package
```

## Usage
get the sdk dependency version in pom.xml file.
Upload _target/zoloz-api-sdk-0.1.0.jar_ to your central Maven repository, or simply install into your local Maven repository, and then introduce the library into your project by adding following dependency in the POM file of your project:
```xml
<dependency>
    <groupId>com.zoloz.api.sdk</groupId>
    <artifactId>zoloz-api-sdk</artifactId>
    <version>0.1.0</verison>
</dependency>
```

