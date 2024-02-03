package io.github.perceivechuchu.jwt.signing;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.perceivechuchu.jwt.signing.exception.JWTSignerException;
import io.github.perceivechuchu.jwt.signing.model.TextRSAPrivateKey;
import io.github.perceivechuchu.jwt.signing.model.TextRSAPublicKey;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.StringReader;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

/**
 * This class is a utility for jwt signing
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class JWTSigner {

    private JWTSigner() {
    }

    /**
     * Signs a JWT with Private and Public Keys using RSA256 Algorithm
     *
     * @param jwtBuilder the builder for the JWT that also contains the details of the JWT to be signed
     * @param privateKeyTextObject the RSA private key object that holds private key information in textual format e.g. plain text private key
     * @param publicKeyTextObject the RSA public key object that holds public key information in textual format e.g. plain text public key
     * @return the string value of signed jwt
     * @throws JWTSignerException    if the signing of jwt fails
     * @since 1.0.0
     */
    public static String sign(JWTCreator.Builder jwtBuilder, TextRSAPrivateKey privateKeyTextObject, TextRSAPublicKey publicKeyTextObject) throws JWTSignerException {
        try {
            RSAPrivateKey rsaPrivateKey = convertToRSAPrivateKey(privateKeyTextObject.getPlainText());
            RSAPublicKey rsaPublicKey = convertToRSAPublicKey(publicKeyTextObject.getPlainText());
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            // Sign the JWT with the private key using RSA256 Algorithm
            return jwtBuilder.sign(algorithm);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to sign JWT with private key. Message - " + e.getMessage());
        }
    }

    /**
     * Converts s private key in textual format to RSAPrivateKey object
     *
     * @param privateKeyText the private key in textual format
     * @return the RSA private key object that holds RSA private key information in textual format
     * @throws JWTSignerException    if the conversion of RSA private key fails
     * @since 1.0.0
     */
    private static RSAPrivateKey convertToRSAPrivateKey(String privateKeyText) throws JWTSignerException {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(privateKeyText));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
            if (Objects.isNull(object)) {
                throw new JWTSignerException("Invalid private key format - " + privateKeyText);
            }
            return (RSAPrivateKey) converter.getPrivateKey(object);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to build RSA Private Key. Message - " + e.getMessage());
        }
    }

    /**
     * Converts public key in textual format to RSAPublicKey object
     *
     * @param publicKeyText the public key in textual format
     * @return the RSA public key object that holds RSA public key information in textual format
     * @throws JWTSignerException    if the conversion of RSA public key fails
     * @since 1.0.0
     */
    private static RSAPublicKey convertToRSAPublicKey(String publicKeyText) throws JWTSignerException {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(publicKeyText));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            SubjectPublicKeyInfo object = (SubjectPublicKeyInfo) pemParser.readObject();
            if (Objects.isNull(object)) {
                throw new JWTSignerException("Invalid public key format - " + publicKeyText);
            }
            return (RSAPublicKey) converter.getPublicKey(object);
        } catch (Exception e) {
            throw new JWTSignerException("Failed to build RSA Public Key. Message - " + e.getMessage());
        }
    }

}
