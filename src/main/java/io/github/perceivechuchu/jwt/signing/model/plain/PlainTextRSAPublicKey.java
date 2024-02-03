package io.github.perceivechuchu.jwt.signing.model.plain;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPublicKey;

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
