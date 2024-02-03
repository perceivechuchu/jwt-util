package io.github.perceivechuchu.jwt.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import io.github.perceivechuchu.jwt.oauth2.exception.JWTSignerException;
import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPrivateKey;
import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPublicKey;
import io.github.perceivechuchu.jwt.oauth2.model.base64.Base64TextRSAPrivateKey;
import io.github.perceivechuchu.jwt.oauth2.model.base64.Base64TextRSAPublicKey;
import io.github.perceivechuchu.jwt.oauth2.model.plain.PlainTextRSAPrivateKey;
import io.github.perceivechuchu.jwt.oauth2.model.plain.PlainTextRSAPublicKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.UUID;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class JWTSignerTests {

    @Test
    void sign_ReturnSignedJWT_WhenAllPlainTextParametersAreValid() throws JWTSignerException {
        String plainPrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqPfgaTEWEP3S9w0t\n" +
                "gsicURfo+nLW09/0KfOPinhYZ4ouzU+3xC4pSlEp8Ut9FgL0AgqNslNaK34Kq+NZ\n" +
                "jO9DAQIDAQABAkAgkuLEHLaqkWhLgNKagSajeobLS3rPT0Agm0f7k55FXVt743hw\n" +
                "Ngkp98bMNrzy9AQ1mJGbQZGrpr4c8ZAx3aRNAiEAoxK/MgGeeLui385KJ7ZOYktj\n" +
                "hLBNAB69fKwTZFsUNh0CIQEJQRpFCcydunv2bENcN/oBTRw39E8GNv2pIcNxZkcb\n" +
                "NQIgbYSzn3Py6AasNj6nEtCfB+i1p3F35TK/87DlPSrmAgkCIQDJLhFoj1gbwRbH\n" +
                "/bDRPrtlRUDDx44wHoEhSDRdy77eiQIgE6z/k6I+ChN1LLttwX0galITxmAYrOBh\n" +
                "BVl433tgTTQ=\n" +
                "-----END PRIVATE KEY-----";

        String plainPublicKey = "-----BEGIN RSA PUBLIC KEY-----\n" +
                "MEgCQQCo9+BpMRYQ/dL3DS2CyJxRF+j6ctbT3/Qp84+KeFhnii7NT7fELilKUSnx\n" +
                "S30WAvQCCo2yU1orfgqr41mM70MBAgMBAAE=\n" +
                "-----END RSA PUBLIC KEY-----";

        TextRSAPrivateKey privateKeyPlainObject = new PlainTextRSAPrivateKey(plainPrivateKey);
        TextRSAPublicKey publicKeyPlainObject = new PlainTextRSAPublicKey(plainPublicKey);

        JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to client ID
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to client ID
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

        assertNotNull(JWTSigner.sign(privateKeyPlainObject, publicKeyPlainObject, jwtBuilder));
    }

    @Test
    void sign_ReturnSignedJWT_WhenAllBase64TextParametersAreValid() throws JWTSignerException {
        String privateKeyBase64 = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUJWQUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQVQ0d2dnRTZBZ0VBQWtFQXFQZmdhVEVXRVAzUzl3MHQKZ3NpY1VSZm8rbkxXMDkvMEtmT1BpbmhZWjRvdXpVKzN4QzRwU2xFcDhVdDlGZ0wwQWdxTnNsTmFLMzRLcStOWgpqTzlEQVFJREFRQUJBa0Fna3VMRUhMYXFrV2hMZ05LYWdTYWplb2JMUzNyUFQwQWdtMGY3azU1RlhWdDc0M2h3Ck5na3A5OGJNTnJ6eTlBUTFtSkdiUVpHcnByNGM4WkF4M2FSTkFpRUFveEsvTWdHZWVMdWkzODVLSjdaT1lrdGoKaExCTkFCNjlmS3dUWkZzVU5oMENJUUVKUVJwRkNjeWR1bnYyYkVOY04vb0JUUnczOUU4R052MnBJY054WmtjYgpOUUlnYllTem4zUHk2QWFzTmo2bkV0Q2ZCK2kxcDNGMzVUSy84N0RsUFNybUFna0NJUURKTGhGb2oxZ2J3UmJICi9iRFJQcnRsUlVERHg0NHdIb0VoU0RSZHk3N2VpUUlnRTZ6L2s2SStDaE4xTEx0dHdYMGdhbElUeG1BWXJPQmgKQlZsNDMzdGdUVFE9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0K";

        String publicKeyBase64 = "LS0tLS1CRUdJTiBSU0EgUFVCTElDIEtFWS0tLS0tCk1FZ0NRUUNvOStCcE1SWVEvZEwzRFMyQ3lKeFJGK2o2Y3RiVDMvUXA4NCtLZUZobmlpN05UN2ZFTGlsS1VTbngKUzMwV0F2UUNDbzJ5VTFvcmZncXI0MW1NNzBNQkFnTUJBQUU9Ci0tLS0tRU5EIFJTQSBQVUJMSUMgS0VZLS0tLS0K";

        TextRSAPrivateKey privateKeyBase64Object = new Base64TextRSAPrivateKey(privateKeyBase64);
        TextRSAPublicKey publicKeyBase64Object = new Base64TextRSAPublicKey(publicKeyBase64);

        JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to client ID
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to client ID
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

        assertNotNull(JWTSigner.sign(privateKeyBase64Object, publicKeyBase64Object, jwtBuilder));
    }

    @Test
    void sign_ThrowJWTSignerException_WhenPrivateKeyIsInvalid() {
        String privateKeyBase64 = "invalid_private_key";

        String publicKeyBase64 = "LS0tLS1CRUdJTiBSU0EgUFVCTElDIEtFWS0tLS0tCk1FZ0NRUUNvOStCcE1SWVEvZEwzRFMyQ3lKeFJGK2o2Y3RiVDMvUXA4NCtLZUZobmlpN05UN2ZFTGlsS1VTbngKUzMwV0F2UUNDbzJ5VTFvcmZncXI0MW1NNzBNQkFnTUJBQUU9Ci0tLS0tRU5EIFJTQSBQVUJMSUMgS0VZLS0tLS0K";

        TextRSAPrivateKey privateKeyBase64Object = new Base64TextRSAPrivateKey(privateKeyBase64);
        TextRSAPublicKey publicKeyBase64Object = new Base64TextRSAPublicKey(publicKeyBase64);

        JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to client ID
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to client ID
                .withAudience("https://your-oauth-url/")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

        JWTSignerException jwtSignerException = assertThrows(JWTSignerException.class, () -> JWTSigner.sign(privateKeyBase64Object, publicKeyBase64Object, jwtBuilder));
        assertNotNull(jwtSignerException);
    }

    @Test
    void sign_ThrowJWTSignerException_WhenPublicKeyIInvalid() {
        String privateKeyBase64 = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUJWQUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQVQ0d2dnRTZBZ0VBQWtFQXFQZmdhVEVXRVAzUzl3MHQKZ3NpY1VSZm8rbkxXMDkvMEtmT1BpbmhZWjRvdXpVKzN4QzRwU2xFcDhVdDlGZ0wwQWdxTnNsTmFLMzRLcStOWgpqTzlEQVFJREFRQUJBa0Fna3VMRUhMYXFrV2hMZ05LYWdTYWplb2JMUzNyUFQwQWdtMGY3azU1RlhWdDc0M2h3Ck5na3A5OGJNTnJ6eTlBUTFtSkdiUVpHcnByNGM4WkF4M2FSTkFpRUFveEsvTWdHZWVMdWkzODVLSjdaT1lrdGoKaExCTkFCNjlmS3dUWkZzVU5oMENJUUVKUVJwRkNjeWR1bnYyYkVOY04vb0JUUnczOUU4R052MnBJY054WmtjYgpOUUlnYllTem4zUHk2QWFzTmo2bkV0Q2ZCK2kxcDNGMzVUSy84N0RsUFNybUFna0NJUURKTGhGb2oxZ2J3UmJICi9iRFJQcnRsUlVERHg0NHdIb0VoU0RSZHk3N2VpUUlnRTZ6L2s2SStDaE4xTEx0dHdYMGdhbElUeG1BWXJPQmgKQlZsNDMzdGdUVFE9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0K";

        String publicKeyBase64 = "invalid_public_key";

        TextRSAPrivateKey privateKeyBase64Object = new Base64TextRSAPrivateKey(privateKeyBase64);
        TextRSAPublicKey publicKeyBase64Object = new Base64TextRSAPublicKey(publicKeyBase64);

        JWTCreator.Builder jwtBuilder = JWT.create()
                .withIssuer("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set issuer to client ID
                .withSubject("f1faabb6-1d3f-4b46-acd7-eb408340d4f0") // Set subject to client ID
                .withAudience("https://your-oauth-token-endpoint")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plusSeconds(300));

        JWTSignerException jwtSignerException = assertThrows(JWTSignerException.class, () -> JWTSigner.sign(privateKeyBase64Object, publicKeyBase64Object, jwtBuilder));
        assertNotNull(jwtSignerException);
    }

}
