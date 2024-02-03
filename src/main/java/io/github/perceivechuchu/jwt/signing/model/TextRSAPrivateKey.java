package io.github.perceivechuchu.jwt.signing.model;

/**
 * This is an interface for text RSAPrivateKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public interface TextRSAPrivateKey {

    /**
     * Gets the plain text for RSA private key
     *
     * @return the plain text for RSA private key
     * @since 1.0.0
     */
    String getPlainText();

}
