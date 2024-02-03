package io.github.perceivechuchu.jwt.signing.model.base64;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPublicKey;
import org.apache.commons.codec.binary.Base64;

public class Base64TextRSAPublicKey implements TextRSAPublicKey {

    private final String text;

    public Base64TextRSAPublicKey(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return new String(new Base64().decode(this.text.getBytes()));
    }

}
