package io.github.perceivechuchu.jwt.signing.model.plain;

import io.github.perceivechuchu.jwt.signing.exception.JWTSignerException;
import io.github.perceivechuchu.jwt.signing.model.TextRSAPrivateKey;

/**
 * This is a concrete class for plain text RSAPrivateKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public class PlainTextRSAPrivateKey implements TextRSAPrivateKey {

    /**
     * The textual value of the RSA public key
     */
    private final String text;

    public PlainTextRSAPrivateKey(String plainText) {
        this.text = plainText;
    }

    /**
     * Gets the supplied plain text for RSA private key
     *
     * @return the plain text for RSA private key
     * @since 1.0.0
     */
    @Override
    public String getPlainText() {
        return this.text;
    }
}
