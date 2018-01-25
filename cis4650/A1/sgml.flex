/*
  CIS*4650 A1
  Brayden Cowell
  0844864

  File Name: sgml.flex
  JFlex specification for SGML (Standard Generalized Markup Language)
*/

%%

%class Lexer
%type Token
%line
%column
    
%{
  // Add basic stack operations to Lexer
  class Stack {
    static final int MAX = 1000;
    int top;
    Token a[] = new Token[MAX];
 
    boolean isEmpty() {
        return (top < 0);
    }
    
    Stack() {
        top = -1;
    }
 
    boolean push(Token x) {
      if (top >= MAX) {
        System.out.println("Stack Overflow");
        return false;
      } else {
        a[++top] = x;
        return true;
      }
    }
 
    Token pop() {
      if (top < 0) {
        System.out.println("Stack Underflow");
        return null;
      } else {
        Token x = a[top--];
        return x;
      }
    }
  }
  Stack s = new Stack();
%}

%eofval{
  return null;
%eofval};

opentag = <[^/]([^>]*)>
closedtag = <[/]([^>]*)>

number = -?([0-9]+|[0-9]*\.[0-9]+([eE][-+]?[0-9]+)?)
chars = a-zA-Z0-9
hyphen = (\?=[a-zA-Z0-9]*-)[a-zA-Z0-9-]+
apost = (\?=[a-zA-Z0-9]*')[a-zA-Z0-9']+
word = (\?![\s,.:>]|\^)\/([0-9]*[A-Za-z]+[0-9]*)+\/(\?=[\s,.:<]|\$)
punct = [\.|\,|\?|\!|\"|\:|\;]+

// lword = [:space:,.:>]
// rword = [:space:,.:<]

/* comments */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )

%state TAG
%%

<YYINITIAL> {
  {opentag} { 
    // push tag on the stack
    Token tok = new Token(Token.OPENTAG, yytext(), yyline, yycolumn);
    s.push(tok);
    yybegin(TAG); 
    return tok; 
  }
  {WhiteSpace}            { /* skip whitespace */ }   
  {Comment}               { /* skip comments */ }
}
<TAG> {
  {opentag} { 
    // push the next tag onto the stack
    Token tok = new Token(Token.OPENTAG, yytext(), yyline, yycolumn);
    s.push(tok);
    return tok; 
  }
  {closedtag} { 
    // pop last tag and check if tags match
    // Have to build a string to match token like "OPEN-DOC"
    String copy = "OPEN-" + yytext();
    copy = copy.replace("<","");
    copy = copy.replace(">","");
    copy = copy.replace("/","");
    copy = copy.toUpperCase();

    if (s.pop().toString().equals(copy)) {
      System.out.println("GOOD");
    } else {
      System.out.println("BAD");
    }
    Token tok = new Token(Token.CLOSEDTAG, yytext(), yyline, yycolumn); 
    yybegin(TAG); 
    return tok;
  }
  {number}             { return new Token(Token.NUM, yytext(), yyline, yycolumn); }
  {word}               { return new Token(Token.WORD, yytext(), yyline, yycolumn); }
  {hyphen}             { return new Token(Token.HYPHEN, yytext(), yyline, yycolumn); }
  {apost}              { return new Token(Token.APOST, yytext(), yyline, yycolumn); }
  {punct}              { return new Token(Token.PUNCT, yytext(), yyline, yycolumn); }
  {WhiteSpace}            { /* skip whitespace */ }   
  {Comment}               { /* skip comments */ }
}
.                         { return new Token(Token.ERROR, yytext(), yyline, yycolumn); }
