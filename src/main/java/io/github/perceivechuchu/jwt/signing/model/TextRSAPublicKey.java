package io.github.perceivechuchu.jwt.signing.model;

/**
 * This is an interface for text RSAPublicKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public interface TextRSAPublicKey {

    /**
     * Gets the plain text for RSA public key
     *
     * @return the plain text for RSA public key
     * @since 1.0.0
     */
    String getPlainText();

}
