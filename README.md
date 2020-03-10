# ZOLOZ API SDK
## Background
This repository provide a SDK to help the customer to integrate ZOLOZ SaaS API with ease. There're also plenty of examples of both single product integration and more compliated business scenario with multiple products integrated.

## Build
### Prequisites
- JDK 1.8
- Maven >3.2.5

To build the project, simply execute following command from the root directory of the project:
```sh
mvn package
```

## API SDK Usage
Upload _src/sdk/target/zoloz-api-sdk-1.0-SNAPSHOT.jar_ to your central Maven repository, or simply install into your local Maven repository, and then introduce the library into your project by adding following dependency in the POM file of your project:
```xml
<dependency>
    <groupId>com.zoloz.api.sdk</groupId>
    <artifactId>zoloz-api-sdk</artifactId>
    <version>1.0-SNAPSHOT</verison>
</dependency>
```

## Examples Usage
### FaceCompare Example
> TODO

### IdRecognize Example
> TODO

### Web-FaceCapture / Web-IdRecognize Examples
#### Prequisite
- JDK 1.8
#### Steps
##### 1. Launch business server:

    Execute following command (on your local machine typically):
    
```sh
java \
  -Dclient.id=<client_id> \
  -Dmerchant.privkey.path=<merchant_private_key_path> \
  -Dzoloz.pubkey.path=<zoloz_public_key_path> \
  -jar src/examples/clientmode-bizserver/target/zoloz-clientmode-bizserver-1.0-SNAPSHOT.jar
```
or specify public key content directly instead of specify the file path of the public key:

```sh
java \
  -Dclient.id=<client_id> \
  -Dmerchant.privkey.path=<merchant_private_key_path> \
  -Dzoloz.pubkey=<zoloz_public_key_base64_content> \
  -jar src/examples/clientmode-bizserver/target/zoloz-clientmode-bizserver-1.0-SNAPSHOT.jar
```

##### 2. Inspect host information:

Find "Server started on <ip>:<host>" pattern from the output of the business server, for example:

The ip is "192.168.3.8", and the port is 8080.

##### 3. Start example from browser:
Visit _http://\<ip\>:\<port\>/index.html_ from your **mobile browser**, for now only a few browsers are supported:
- for iOS, only Safari is supported;
- for Android, only Chrome is supported.

##### 4. Start individual examples by click the hyperlinks.
