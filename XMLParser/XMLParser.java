package com.Parsers.XMLParser;

import com.Parsers.Pair;
import com.Parsers.XMLComponents.XMLContent;
import com.Parsers.XMLComponents.XMLContentString;
import com.Parsers.XMLComponents.XMLSyntax;
import com.Parsers.XMLComponents.XMLTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XMLParser {
    private static HashSet<Character> whiteSpace = Stream.of(' ', '\t', '\b', '\n', '\r')
            .collect(Collectors.toCollection(HashSet::new));

    public static Pair<? extends XMLContent, Integer> parse(String XML, int currentPos) {
        //Handle possible invalid input
        if (XML.isEmpty() || XML.isBlank())
            throw new IllegalArgumentException("Invalid XML passed: " + XML);
        else if (currentPos >= XML.length())
            throw new IllegalArgumentException("Invalid currentPos: " + currentPos + " but XML length is: " + XML.length());

        if (XMLSyntax.getByField(XML.charAt(currentPos)).isPresent()
                && XMLSyntax.getByField(XML.charAt(currentPos)).get() == XMLSyntax.XML_LEFT_ANGLE_BRACKET)
            return parseTag(XML, currentPos);
        else {
            StringBuilder content = new StringBuilder();
            var current = XMLSyntax.getByField(XML.charAt(currentPos));
            var next = XMLSyntax.getByField(XML.charAt(currentPos + 1));
            //append until encountering start closing tag
            while (!(current.isPresent() && current.get() == XMLSyntax.XML_LEFT_ANGLE_BRACKET
                && next.isPresent() && next.get() == XMLSyntax.XML_FORWARD_SLASH)) {
                content.append(XML.charAt(currentPos));
                ++currentPos;
                current = XMLSyntax.getByField(XML.charAt(currentPos));
                next = XMLSyntax.getByField(XML.charAt(currentPos + 1));
            }
            //get to end of closing tag
            while (!(current.isPresent() && current.get() == XMLSyntax.XML_RIGHT_ANGLE_BRACKET)) {
                ++currentPos;
                current = XMLSyntax.getByField(XML.charAt(currentPos));
            }
            return new Pair<>(new XMLContentString(content.toString()), ++currentPos);
        }
    }

    //Invoke upon encountering '<'
    public static Pair<XMLTag, Integer> parseTag(String XML, int currentPos) {
        //Handle possible invalid input
        if (XML.isEmpty() || XML.isBlank())
            throw new IllegalArgumentException("Invalid XML passed: " + XML);
        else if (currentPos >= XML.length())
            throw new IllegalArgumentException("Invalid currentPos: " + currentPos + " but XML length is: " + XML.length());
        else if (XMLSyntax.getByField(XML.charAt(currentPos)).get() != XMLSyntax.XML_LEFT_ANGLE_BRACKET)
            throw new IllegalArgumentException("charAt currentPos of XML is: " + XML.charAt(currentPos) + " but parsed tag must" +
                    " begin with '<'!");

        XMLTag tag = new XMLTag();
        //Discard any whitespace up to first string, which should be element
        while (whiteSpace.contains(XML.charAt(currentPos)))
            ++currentPos;
        StringBuilder element = new StringBuilder();
        while (!whiteSpace.contains(XML.charAt(currentPos)) && XMLSyntax.getByField(XML.charAt(currentPos)).isEmpty()) {
            element.append(XML.charAt(currentPos));
            ++currentPos;
        }
        tag.setElement(element.toString());
        while (whiteSpace.contains(XML.charAt(currentPos)))
            ++currentPos;

        //need to parse attributes
        if (XMLSyntax.getByField(XML.charAt(currentPos)).isEmpty()) {
            TODO implement this
                    //parse attributes
        } else if (XMLSyntax.getByField(XML.charAt(currentPos)).get() == XMLSyntax.XML_FORWARD_SLASH
                && XMLSyntax.getByField(XML.charAt(currentPos + 1)).isPresent()
                && XMLSyntax.getByField(XML.charAt(currentPos + 1)).get() == XMLSyntax.XML_RIGHT_ANGLE_BRACKET) //no content
            return new Pair<>(tag, currentPos + 2);

        if (XMLSyntax.getByField(XML.charAt(currentPos)).get() == XMLSyntax.XML_RIGHT_ANGLE_BRACKET) { //content to parse
            var content = parse(XML, ++currentPos);
            tag.setContent(content.getFirst());
            return new Pair<>(tag, content.getSecond());
        } else
            throw new IllegalArgumentException("Expected tag to close!");
    }
}
