package io.github.perceivechuchu.jwt.signing.model.plain;

import io.github.perceivechuchu.jwt.signing.model.TextRSAPrivateKey;

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
