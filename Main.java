package com.Parsers;

import com.Parsers.JSONComponents.JSONComponent;
import com.Parsers.JSONParser.JSONLexer;
import com.Parsers.JSONParser.JSONParser;

public class Main {

    public static void main(String[] args) {
        String s1 = "{\"foo\": [1.0, 2, {\"bar\": [2, 3, 4, \"pie\"]}]}";
        String s2 = "{\"cake\" : 2.0, \"pie\": 3.0}";
        String s3 = "{\"foo\": [1.0, 2, {\"bar\": [2, 3, 4, \"pie\"]}], \"next\": \"cake\"}";
        //Sample from blog post
        var tokens = JSONLexer.lex(s1);
        System.out.println("===LEXER OUTPUT===");
        tokens.stream()
                .map(JSONComponent::JSONRepresentation)
                .forEach(System.out::println);
        System.out.println("===LEXER OUTPUT===");
        System.out.println("===PARSER OUTPUT===");
        var output = JSONParser.parse(tokens, 0);
        System.out.println(output.getFirst().JSONRepresentation());
        System.out.println("===PARSER OUTPUT===");
    }
}
