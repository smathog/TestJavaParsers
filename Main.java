package com.Parsers;

import com.Parsers.JSONParser.JSONLexer;
import com.Parsers.JSONParser.JSONParser;
import com.Parsers.XMLParser.XMLParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter JSON or XML: ");
            String input = scanner.nextLine();
            if (input.startsWith("<"))
                System.out.println(XMLParser.parse(input, 0).getFirst());
            else if (input.startsWith("{"))
                System.out.println(JSONParser.parse(JSONLexer.lex(input), 0).getFirst().JSONRepresentation());
            else if (input.equals("exit"))
                break;
            else
                System.out.println("Error: not XML or JSON");
        }
    }
}
