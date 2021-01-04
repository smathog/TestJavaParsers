package com.Parsers.JSONParser;

public class JSONString implements JSONComponent{
    private final String str;

    public JSONString(String str) {
        this.str = str;
    }

    @Override
    public String JSONRepresentation() {
        return str;
    }
}
