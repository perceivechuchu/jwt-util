package io.github.perceivechuchu.jwt.signing.model.base64;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPrivateKey;
import org.apache.commons.codec.binary.Base64;

/**
 * This is a concrete class for base64 RSAPrivateKey
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
public class Base64TextRSAPrivateKey implements TextRSAPrivateKey {

    /**
     * The textual value of the RSA public key
     */
    private final String text;

    public Base64TextRSAPrivateKey(String base64Text){
        this.text = base64Text;
    }

    /**
     * Gets the plain text by converting from the supplied base64 text of RSA private key
     *
     * @return the plain text for RSA public key
     * @since 1.0.0
     */
    @Override
    public String getPlainText() {
        return new String(new Base64().decode(this.text.getBytes()));
    }

}
