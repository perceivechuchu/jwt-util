package io.github.perceivechuchu.jwt.oauth2;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.perceivechuchu.jwt.oauth2.exception.JWTSignerException;
import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPrivateKey;
import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPublicKey;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.StringReader;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

public class JWTSigner {

    private JWTSigner() {
    }

    public static String sign(TextRSAPrivateKey privateKeyTextObject, TextRSAPublicKey publicKeyTextObject, JWTCreator.Builder jwtBuilder) throws JWTSignerException {
        try {
            RSAPrivateKey rsaPrivateKey = buildRSAPrivateKey(privateKeyTextObject.getText());
            RSAPublicKey rsaPublicKey = buildRSAPublicKey(publicKeyTextObject.getText());
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            // Sign the JWT with the private key
            return jwtBuilder.sign(algorithm);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to sign JWT with private key. Message: " + e.getMessage());
        }
    }

    private static RSAPrivateKey buildRSAPrivateKey(String privateKeyText) throws JWTSignerException {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(privateKeyText));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
            if (Objects.isNull(object)) {
                throw new JWTSignerException("Invalid private key format");
            }
            return (RSAPrivateKey) converter.getPrivateKey(object);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to build RSA Private Key. Message: " + e.getMessage());
        }
    }

    private static RSAPublicKey buildRSAPublicKey(String publicKeyText) throws JWTSignerException {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(publicKeyText));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            SubjectPublicKeyInfo object = (SubjectPublicKeyInfo) pemParser.readObject();
            if (Objects.isNull(object)) {
                throw new JWTSignerException("Invalid public key format");
            }
            return (RSAPublicKey) converter.getPublicKey(object);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to build RSA Public Key. Message: " + e.getMessage());
        }
    }

}
