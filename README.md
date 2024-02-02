date-time-util
==============

[![Build Status](https://github.com/perceivechuchu/jwt-util/actions/workflows/maven.yml/badge.svg)](https://github.com/perceivechuchu/jwt-util/actions/workflows/maven.yml)
[![Jacoco Coverage](/.github/badges/jacoco.svg)](/.github/badges/jacoco.svg)

jwt-util a utility library that provides functionality to deal with jwt tokens; jwt signing etc.

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

* **Signing a JWT token with Plain Text Private and Public Keys (RSA256 Algorithm)**
```
String privateKeyPlain = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqPfgaTEWEP3S9w0t\n" +
                "gsicURfo+nLW09/0KfOPinhYZ4ouzU+3xC4pSlEp8Ut9FgL0AgqNslNaK34Kq+NZ\n" +
                "jO9DAQIDAQABAkAgkuLEHLaqkWhLgNKagSajeobLS3rPT0Agm0f7k55FXVt743hw\n" +
                "Ngkp98bMNrzy9AQ1mJGbQZGrpr4c8ZAx3aRNAiEAoxK/MgGeeLui385KJ7ZOYktj\n" +
                "hLBNAB69fKwTZFsUNh0CIQEJQRpFCcydunv2bENcN/oBTRw39E8GNv2pIcNxZkcb\n" +
                "NQIgbYSzn3Py6AasNj6nEtCfB+i1p3F35TK/87DlPSrmAgkCIQDJLhFoj1gbwRbH\n" +
                "/bDRPrtlRUDDx44wHoEhSDRdy77eiQIgE6z/k6I+ChN1LLttwX0galITxmAYrOBh\n" +
                "BVl433tgTTQ=\n" +
                "-----END PRIVATE KEY-----";

String publicKeyPlain = "-----BEGIN RSA PUBLIC KEY-----\n" +
                "MEgCQQCo9+BpMRYQ/dL3DS2CyJxRF+j6ctbT3/Qp84+KeFhnii7NT7fELilKUSnx\n" +
                "S30WAvQCCo2yU1orfgqr41mM70MBAgMBAAE=\n" +
                "-----END RSA PUBLIC KEY-----";

TextRSAPrivateKey privateKeyPlainObject = new PlainTextRSAPrivateKey(privateKeyPlain);
TextRSAPublicKey publicKeyPlainObject = new PlainTextRSAPublicKey(publicKeyPlain);

JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to OAuth Client Id
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to Oauth Client Id
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

String jwt = JWTSigner.sign(privateKeyPlainObject, publicKeyPlainObject, jwtBuilder);

// Output: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFlOWdkazcifQ.ewogImlzc..."
```
<br />

* **Signing a JWT token with Base64 Encoded Private and Public Keys (RSA256 Algorithm)**
```
String privateKeyPlain = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUJWQUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQVQ0d2dnRTZBZ0VBQWtFQXFQZmdhVEVXRVAzUzl3MHQKZ3NpY1VSZm8rbkxXMDkvMEtmT1BpbmhZWjRvdXpVKzN4QzRwU2xFcDhVdDlGZ0wwQWdxTnNsTmFLMzRLcStOWgpqTzlEQVFJREFRQUJBa0Fna3VMRUhMYXFrV2hMZ05LYWdTYWplb2JMUzNyUFQwQWdtMGY3azU1RlhWdDc0M2h3Ck5na3A5OGJNTnJ6eTlBUTFtSkdiUVpHcnByNGM4WkF4M2FSTkFpRUFveEsvTWdHZWVMdWkzODVLSjdaT1lrdGoKaExCTkFCNjlmS3dUWkZzVU5oMENJUUVKUVJwRkNjeWR1bnYyYkVOY04vb0JUUnczOUU4R052MnBJY054WmtjYgpOUUlnYllTem4zUHk2QWFzTmo2bkV0Q2ZCK2kxcDNGMzVUSy84N0RsUFNybUFna0NJUURKTGhGb2oxZ2J3UmJICi9iRFJQcnRsUlVERHg0NHdIb0VoU0RSZHk3N2VpUUlnRTZ6L2s2SStDaE4xTEx0dHdYMGdhbElUeG1BWXJPQmgKQlZsNDMzdGdUVFE9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0K";

String publicKeyPlain = "LS0tLS1CRUdJTiBSU0EgUFVCTElDIEtFWS0tLS0tCk1FZ0NRUUNvOStCcE1SWVEvZEwzRFMyQ3lKeFJGK2o2Y3RiVDMvUXA4NCtLZUZobmlpN05UN2ZFTGlsS1VTbngKUzMwV0F2UUNDbzJ5VTFvcmZncXI0MW1NNzBNQkFnTUJBQUU9Ci0tLS0tRU5EIFJTQSBQVUJMSUMgS0VZLS0tLS0K";

TextRSAPrivateKey privateKeyPlainObject = new PlainTextRSAPrivateKey(privateKeyPlain);
TextRSAPublicKey publicKeyPlainObject = new PlainTextRSAPublicKey(publicKeyPlain);

JWTCreator.Builder jwtBuilder = JWT.create()
.withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to OAuth Client Id
.withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to Oauth Client Id
.withAudience("https://your-oauth-url/")
.withClaim("jti", UUID.randomUUID().toString())
.withExpiresAt(Instant.now().plusSeconds(300));

String jwt = JWTSigner.sign(privateKeyPlainObject, publicKeyPlainObject, jwtBuilder);

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
