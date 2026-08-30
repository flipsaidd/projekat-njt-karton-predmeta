package com.fgc.kartonpredmeta.parser;

import lombok.Getter;

@Getter
public class Token {

    private final TokenType type;
    private final String value;
    private final int line;

    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

}
