package io.github.perceivechuchu.jwt.signing.model.plain;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPublicKey;

/**
 * This is a concrete class for plain text RSAPublicKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public class PlainTextRSAPublicKey implements TextRSAPublicKey {

    /**
     * The textual value of the RSA public key
     */
    private final String text;

    public PlainTextRSAPublicKey(String plainText){
        this.text = plainText;
    }

    /**
     * Gets the supplied plain text for RSA public key
     *
     * @return the plain text for RSA public key
     * @since 1.0.0
     */
    @Override
    public String getPlainText() {
        return this.text;
    }
}
