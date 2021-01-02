package com.Parsers.JSONParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JSONLexer {
    private static HashSet<Character> JSONWhitespace;
    private static HashSet<Character> JSONSyntax;
    private static HashSet<Character> JSONNumberCharacters;

    static {
        JSONWhitespace = Stream.of(' ', '\t', '\b', '\n', '\r')
                .collect(Collectors.toCollection(HashSet::new));
        JSONSyntax = Arrays.stream(JSONCharacter.values())
                .map(C -> C.getField().charAt(0))
                .collect(Collectors.toCollection(HashSet::new));
        JSONNumberCharacters = IntStream.range(0, 10)
                .mapToObj(i -> Character.valueOf((char) ('0' + i)))
                .collect(Collectors.toCollection(HashSet::new));

    }

    public static Optional<List<String>> lexString(String input) {
        StringBuilder sb = new StringBuilder();
        if (!JSONCharacter.JSON_QUOTE.getField().equals(input.substring(0, 1)))
            return Optional.empty();
        input = input.substring(1);
        input.chars()
                .takeWhile(c -> !JSONCharacter.JSON_QUOTE.getField().equals(Character.toString(c)))
                .forEachOrdered(sb::append);
        if (sb.length() == input.length())
            throw new IllegalArgumentException("Failed to reach terminal \" at end of input String");
        else
            return Optional.of(List.of(sb.toString(), input.substring(sb.length() + 1)));
    }

    public static Optional<List<String>> lexNumber(String input) {
        StringBuilder sb = new StringBuilder();
        input.chars()
    }

    public static Optional<List<String>> lexBoolean(String input) {
        return Optional.empty();
    }

    public static Optional<String> lexNull(String input) {
        return Optional.empty();
    }

    public static List<String> lex(String input) {
        List<String> tokens = new ArrayList<>();
        while (!input.isEmpty()) {
            var jsonString = lexString(input);
            if (jsonString.isPresent()) {
                tokens.add(jsonString.get().get(0));
                input = jsonString.get().get(1);
                continue;
            }
            var jsonNumber = lexNumber(input);
            if (jsonNumber.isPresent()) {
                tokens.add(jsonNumber.get().get(0));
                input = jsonNumber.get().get(1);
                continue;
            }
            var jsonBoolean = lexBoolean(input);
            if (jsonBoolean.isPresent()) {
                tokens.add(jsonBoolean.get().get(0));
                input = jsonBoolean.get().get(1);
                continue;
            }
            var jsonNull = lexNull(input);
            if (jsonNull.isPresent()) {
                tokens.add(null);
                input = jsonBoolean.get();
                continue;
            }
            char leadChar = input.charAt(0);
            if (JSONWhitespace.contains(leadChar))
                continue;
            else if (JSONSyntax.contains(leadChar)) {
                tokens.add(Character.toString(leadChar));
            } else {
                throw new IllegalArgumentException("Unexpected character: " + leadChar);
            }
        }
        return tokens;
    }
}
