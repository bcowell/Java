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

public class Scanner {

    private Lexer scanner = null;

    public Scanner( Lexer lexer ) {
        scanner = lexer; 
    }

    public Token getNextToken() throws java.io.IOException {
        return scanner.yylex();
    }

    public static void main(String argv[]) {
        try {
            Scanner scanner = new Scanner(new Lexer(new InputStreamReader(System.in)));
            Token tok = null;
            while((tok=scanner.getNextToken()) != null) {
                System.out.println(tok);
            }
        } catch (Exception e) {
            System.out.println("Unexpected exception:");
            e.printStackTrace();
        }
    }
}