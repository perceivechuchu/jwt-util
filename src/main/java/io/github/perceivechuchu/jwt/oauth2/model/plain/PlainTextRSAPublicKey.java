package io.github.perceivechuchu.jwt.oauth2.model.plain;

import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPublicKey;
import org.apache.commons.codec.binary.Base64;

public class PlainTextRSAPublicKey implements TextRSAPublicKey {

    private String text;

    public PlainTextRSAPublicKey(String text){
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
