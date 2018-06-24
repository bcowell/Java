/*
    CIS*4650 A1
    Brayden Cowell
    0844864
    File Name: sgml.flex
    JFlex specification for SGML (Standard Generalized Markup Language)
*/

import java.util.*;

%%

%class Lexer
%type Token
%line
%column

%eofval{
    if (!s.isEmpty()) {
        System.err.print("unmatched tagnames on the stack: [");
        do {
            try {
                String tagname = s.pop();
                System.err.print(" "+tagname);
            } catch (Exception e) {}
        }while (!s.isEmpty());
        System.err.print(" ]");
    }

    return null;
%eofval};

%{
    public class Stack {
        private ArrayList<String> tagStack;
 
        public Stack() {
            tagStack = new ArrayList<String>();
        }

        public boolean push(String x) {
            return tagStack.add(x);
        }
 
        public String pop() throws Exception {
            if (!isEmpty()) {
                String prev;
                prev = tagStack.remove(( tagStack.size()-1 ));
                return (prev);
            } else {
                throw new Exception("Stack empty.");
            }
        }

        public boolean isEmpty() {
                return tagStack.isEmpty();
        }

        public String stackTop() {
            if (!isEmpty()) {
                return (tagStack.get(tagStack.size()-1));
            } else {
                return null;
            }
        }
    }

    // Initialize the global tag stack
    Stack s = new Stack();

    public boolean relevantTag(String tag, String prevTag) {
        String[] relTagList = {"DOC","TEXT","DATE","DOCNO","HEADLINE","LENGTH"};

        // Special case P - is only a relevant tag if it appears within another relevant tag
        if (((tag.equals("P")) && Arrays.asList(relTagList).contains(prevTag)) || (tag.equals("P") && prevTag.equals("P"))) {
            return true;
        } else if (Arrays.asList(relTagList).contains(tag)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchTags(String prev, String curr) {
        if (prev.equals(curr)) {
            return true;
        } else { 
            return false; 
        }
    }

    public void mismatchTags(String text, int line, int column) {
        // Display an error message if the tagnames don't match
        line += 1;
        System.err.println(text+" wrong tag on line "+line+" at col "+column);
    }

    public String cleanOpenTag(String tag) {
        // Strip the <> from tag
        tag = tag.replace("<","");
        tag = tag.replace(">","");

        // Change to upperCase
        tag = tag.toUpperCase();

        // Remove any attributes
        if (tag.contains(" ")) {
            tag = tag.substring(0, tag.indexOf(' '));
        }
        
        return tag;
    }

    public String cleanCloseTag(String tag) {
        // Strip the <> and / from tag
        tag = tag.replace("<","");
        tag = tag.replace(">","");
        tag = tag.replace("/","");

        // Change to upperCase
        tag = tag.toUpperCase();

        return tag;
    }

%}

// REGULAR EXPRESSIONS

opentag = <[^/]([^>]*)>
closedtag = <[/]([^>]*)>

alpha = [A-Za-z]
num = [0-9]

word = ({alpha}|{num}*){alpha}({alpha}|{num})*

float = ("+"|"-")?({num}+"."{num}*)|({num}*"."{num}+|{num}+)
number = {float}

hyphen = ({word}|{num})+"-"({word}|{num})+
apost = {word}"'"{word}

punct = [^ \n\r\tA-Za-z0-9"<""/"">"]
blank = [ |\n|\r|\t|\f]



%state TAG, IRRELEVANTTAG
%%

<YYINITIAL> {
    {opentag} { 
        String tag;
        tag =  cleanOpenTag(yytext());
        s.push(tag);

        Token tok = new Token(Token.OPENTAG, tag, yyline, yycolumn);
        yybegin(TAG); 
        return tok; 
    }
    {blank}                        { /* skip whitespace */ }     
}
<TAG> {
    {opentag} {
        String tag;
        tag =  cleanOpenTag(yytext());

        // Check if the tag is relevant
        String prevTag = s.stackTop();

        if (relevantTag(tag, prevTag)) {
            // push the next tag onto the stack
            s.push(tag);
            Token tok = new Token(Token.OPENTAG, tag, yyline, yycolumn);
            return tok; 
        } else {
            yybegin(IRRELEVANTTAG);
        }
    }
    {closedtag} {
        // pop previous tag and check if it matches current close-tag
        try {
            String prev = s.pop();
            String curr = cleanCloseTag(yytext());

            // Check if the tag is relevant
            if (relevantTag(curr,prev)) {
                if (matchTags(prev, curr)) {
                    Token tok = new Token(Token.CLOSEDTAG, curr, yyline, yycolumn); 
                    return tok;
                } else {
                    mismatchTags(yytext(), yyline, yycolumn);
                }
            }
        } catch (Exception e) {
            // System.out.println("Stack underflow.");
        }
    }
    {number}                         { return new Token(Token.NUM, yytext(), yyline, yycolumn); }
    {word}                           { return new Token(Token.WORD, yytext(), yyline, yycolumn); }
    {hyphen}                         { return new Token(Token.HYPHEN, yytext(), yyline, yycolumn); }
    {apost}                          { return new Token(Token.APOST, yytext(), yyline, yycolumn); }
    {punct}                          { return new Token(Token.PUNCT, yytext(), yyline, yycolumn); }
    {blank}                     { /* skip whitespace */ }     
}
<IRRELEVANTTAG> {
    {closedtag}                      { yybegin(TAG); }
    .                                {  /* skip embeded */ }
}
    {blank}                          { /* skip whitespace */ }     
    .                                {}
