package com.Parsers.JSONParser;

public enum JSONCharacter {
    JSON_COMMA(","),
    JSON_COLON(":"),
    JSON_BRACKET_LEFT("["),
    JSON_BRACKET_RIGHT("]"),
    JSON_BRACE_LEFT("{"),
    JSON_BRACE_RIGHT("}"),
    JSON_QUOTE("\"");

    private final String field;

    private JSONCharacter(String field) {
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
