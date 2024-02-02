package io.github.perceivechuchu.jwt.oauth2.model.plain;

import io.github.perceivechuchu.jwt.oauth2.model.TextRSAPrivateKey;

public class PlainTextRSAPrivateKey implements TextRSAPrivateKey {

    private final String text;

    public PlainTextRSAPrivateKey(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
