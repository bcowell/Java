JAVAC=javac
JFLEX=jflex
#JFLEX=/home/bcowell/jflex/bin/jflex
#JFLEX=/usr/local/bin/jflex

all: Scanner.class

Scanner.class: Scanner.java Lexer.java Token.java

%.class: %.java 
	$(JAVAC) $^

Lexer.java: sgml.flex 
	$(JFLEX) sgml.flex

clean: 
	rm -f Lexer.java *.class *~