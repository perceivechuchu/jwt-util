jwt-util
==============

[![Build Status](https://github.com/perceivechuchu/jwt-util/actions/workflows/maven.yml/badge.svg)](https://github.com/perceivechuchu/jwt-util/actions/workflows/maven.yml)
[![Jacoco Coverage](/.github/badges/jacoco.svg)](/.github/badges/jacoco.svg)

jwt-util is a utility library that provides functionality to deal with JWT such as JWT signing.

Installation
============
The artifact is available on Maven Central and can be added to the project's pom.xml:

```xml
<dependency>
    <groupId>io.github.perceivechuchu</groupId>
    <artifactId>jwt-util</artifactId>
    <version>1.0.0</version>
</dependency>
```

The latest version can be found [here](https://central.sonatype.com/artifact/io.github.perceivechuchu/jwt-util)

Features & Usage
========
This library provides the following features:  

## 1. JWTSigner 
<br />

* **Signing a JWT token with Plain Text Private and Public Keys using RSA256 Algorithm**
```
JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to OAuth Client Id
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to Oauth Client Id
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));
                
String privateKeyPlain = "private key in plain text";

String publicKeyPlain = "public key in plain text";

TextRSAPrivateKey privateKeyPlainObject = new PlainTextRSAPrivateKey(privateKeyPlain);
TextRSAPublicKey publicKeyPlainObject = new PlainTextRSAPublicKey(publicKeyPlain);

String jwt = JWTSigner.sign(jwtBuilder, privateKeyPlainObject, publicKeyPlainObject);

// Output: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFlOWdkazcifQ.ewogImlzc..."
```
<br />

* **Signing a JWT token with Base64 Encoded Private and Public Keys using RSA256 Algorithm**
```
JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to OAuth Client Id
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to Oauth Client Id
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

String privateKeyPlain = "private key in base64 format";

String publicKeyPlain = "public key in base64 format";

TextRSAPrivateKey privateKeyPlainObject = new PlainTextRSAPrivateKey(privateKeyPlain);
TextRSAPublicKey publicKeyPlainObject = new PlainTextRSAPublicKey(publicKeyPlain);

String jwt = JWTSigner.sign(jwtBuilder, privateKeyPlainObject, publicKeyPlainObject);

// Output: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFlOWdkazcifQ.ewogImlzc..."
```

Licence
=======
The repository code is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

Author
======
Perceive Chuchu [perceivechuchu@gmail.com](mailto:perceivechuchu@gmail.com) with :green_heart:

API Reference
========
* [jwt-util Java Docs](https://javadoc.io/doc/io.github.perceivechuchu/jwt-util/latest/index.html)

Feedback
========
Please [leave your feedback](https://github.com/perceivechuchu/jwt-util/issues) if you have noticed any issue or have a feature request.

Contributions and Support
=========================
If you want to create a new feature, though not compulsory, it will be helpful to reach out to me first before proceeding.

This avoids a scenario where one would submit a PR for an issue that someone else is working on already.
