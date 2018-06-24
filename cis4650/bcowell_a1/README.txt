CIS*4650 A1
Brayden Cowell
0844864

File Name: README.txt
---------------------------
I built on the sample Java implementation provided.

Scanner built in java and jFlex for marked documents SGML (Standard Generalized Markup Language).
Filters irrelevant tags I.E., not ["<DOC>","<TEXT>","<DATE>","<DOCNO>","<HEADLINE>","<LENGTH>"] and in some cases "<P>".
Sends all errors to stderr including: mismatched tags along with line number, any tagnames left on the stack at the end of input.
Global stack to determine tags is implemented in sgml.flex along with all my Regular Expressions.

Regular Expressions matching:
Word - String of letters and digits ("John","mp3","123abc")
Number - Integers and real numbers with optional preceeding positive or negative sign.
Apostrophized - Words containing the ' character.
Hyphenated - words containing one or more - character.
Punctuation - Any symbol that does not match the above.

Which are all mutually exclusive!


BUILDING & RUNNING
----------
To build the scanner in the current directory
$ make 

To test a file 
$ java Scanner < sample.txt

To rebuild the scanner, type "make clean" and type "make" again.

Errors are sent to System.err, but will still default to the console!
You can redirect errors with,
$ java Scanner < sample.txt 2> errors.txt


Testing
------------
I have included a few test cases called test1.txt, test2.txt and test3.txt.
Test1 contains unclosed opentags
Test2 contains a mismatched tagname
Test3 contains no errors