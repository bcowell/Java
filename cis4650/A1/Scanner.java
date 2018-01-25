/*
  CIS*4650 A1
  Brayden Cowell
  0844864
  
  File Name: Scanner.java
*/

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.lang.String;

// TODO Add global stack of OPEN and CLOSED TAGS

public class Scanner {

    private Lexer scanner = null;
    private static final String[] relevantTags = {"DOC", "TEXT", "DATE", "DOCNO", "HEADLINE", "LENGTH"};

    public Scanner( Lexer lexer ) {
        scanner = lexer; 
    }

    public Token getNextToken() throws java.io.IOException {
        return scanner.yylex();
    }

    // Check if the token is part of the relevant tags 
    // case-insensitive lookup
    // https://stackoverflow.com/a/15269846
    public static boolean containsCaseInsensitive(String s, List<String> l) {
        for (String string : l) {
            if (s.toUpperCase().contains(string)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String argv[]) {
        try {
            Scanner scanner = new Scanner(new Lexer(new InputStreamReader(System.in)));
            Token tok = null;
            while((tok=scanner.getNextToken()) != null) {
                // Either OPEN-TAG, CLOSE-TAG, WORD, NUMBER, APOSTROPHIZED, HYPHENATED, PUNCTUATION
                
                // OPEN-TAG or CLOSE-TAG
                if (containsCaseInsensitive(tok.toString(), Arrays.asList(relevantTags))) {
                    System.out.println(tok);
                } else {
                    System.out.println(tok);
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected exception:");
            e.printStackTrace();
        }
    }
}