package com.Parsers;

import com.Parsers.JSONParser.JSONComponent;
import com.Parsers.JSONParser.JSONLexer;

public class Main {

    public static void main(String[] args) {
        //Sample from blog post
        JSONLexer.lex("{\"foo\": [1, 2, {\"bar\": 2}]}").stream()
                .map(JSONComponent::JSONRepresentation)
                .forEach(System.out::println);
    }
}
