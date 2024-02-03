package io.github.perceivechuchu.jwt.signing.model.base64;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPublicKey;
import org.apache.commons.codec.binary.Base64;

/**
 * This is a concrete class for base64 RSAPublicKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public class Base64TextRSAPublicKey implements TextRSAPublicKey {

    /**
     * The textual value of the RSA public key
     */
    private final String text;

    public Base64TextRSAPublicKey(String base64Text) {
        this.text = base64Text;
    }

    /**
     * Gets the plain text by converting from the supplied base64 text of RSA public key
     *
     * @return the plain text for RSA public key
     * @since 1.0.0
     */
    @Override
    public String getPlainText() {
        return new String(new Base64().decode(this.text.getBytes()));
    }

}
