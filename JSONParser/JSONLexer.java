package com.Parsers.JSONParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONLexer {
    private static HashSet<String> JSONWhitespace;
    private static HashSet<String> JSONSyntax;

    static {
        JSONWhitespace = Stream.of(" ", "\t", "\b", "\n", "\r")
                .collect(Collectors.toCollection(HashSet::new));
        JSONSyntax = Arrays.stream(JSONCharacter.values())
                .map(JSONCharacter::getField)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static Optional<String> lexString(String input) {
        return Optional.empty();
    }

    public static Optional<String> lexNumber(String input) {
        return Optional.empty();
    }

    public static Optional<String> lexBoolean(String input) {
        return Optional.empty();
    }

    public static Optional<String> lexNull(String input) {
        return Optional.empty();
    }

    public static ArrayList<String> lex(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            var jsonString = lexString(input);
            if (jsonString.isPresent()) {
                tokens.add(jsonString.get());
                i += jsonString.get().length() - 1;
                continue;
            }
            var jsonNumber = lexNumber(input);
            if (jsonNumber.isPresent()) {
                tokens.add(jsonNumber.get());
                i += jsonNumber.get().length() - 1;
                continue;
            }
            var jsonBoolean = lexBoolean(input);
            if (jsonBoolean.isPresent()) {
                tokens.add(jsonBoolean.get());
                i += jsonBoolean.get().length() - 1;
                continue;
            }
            var jsonNull = lexNull(input);
            if (jsonNull.isPresent()) {
                tokens.add(null);
                i += jsonNull.get().length() - 1;
                continue;
            }
            String leadChar = input.substring(i, i + 1);
            if (JSONWhitespace.contains(leadChar))
                continue;
            else if (JSONSyntax.contains(leadChar)) {
                tokens.add(leadChar);
            } else {
                throw new IllegalArgumentException("Unexpected character: " + leadChar);
            }
        }
        return tokens;
    }
}
