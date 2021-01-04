package com.Parsers.JSONParser;

public class JSONBoolean implements JSONComponent {
    private final boolean bool;

    public JSONBoolean(boolean bool) {
        this.bool = bool;
    }

    @Override
    public String JSONRepresentation() {
        return Boolean.toString(bool);
    }
}
