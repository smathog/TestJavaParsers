package com.Parsers.JSONParser;

public class JSONInteger extends JSONNumber {
    private final int num;

    public JSONInteger(int num) {
        this.num = num;
    }

    @Override
    public String JSONRepresentation() {
        return Integer.toString(num);
    }
}
