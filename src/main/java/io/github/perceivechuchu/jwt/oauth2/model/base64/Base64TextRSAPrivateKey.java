package io.github.perceivechuchu.jwt.oauth2.model.base64;

import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPrivateKey;
import org.apache.commons.codec.binary.Base64;

public class Base64TextRSAPrivateKey implements TextRSAPrivateKey {

    private String text;

    public Base64TextRSAPrivateKey(String text){
        this.text = text;
    }

    @Override
    public String getText() {
        return new String(new Base64().decode(this.text.getBytes()));
    }

}
