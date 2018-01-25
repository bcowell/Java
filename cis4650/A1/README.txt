CIS*4650 A1
Brayden Cowell
0844864

File Name: README.txt
---------------------------

I built on the sample Java implementation provided.

RUNNING
----------
To build the scanner, type "make" in the current directory, which will 
generate a bytecode file called Scanner.class.

To test source code like "gcd.tiny", type "java Scanner < gcd.tiny" 
and the token sequence will be displayed on the screen.

To rebuild the scanner, type "make clean" and type "make" again.



Relevant tags:
<Doc>, <Text>, <Date>, <DocNo>, <Headline>, and <Length>

The <Doc> and </Doc> tagsindicatethe start and the end of a document and should always be sent to the output.  In addition,<P> and </P> tags indicate a paragraph and canappear within both relevant and irrelevant tags.  As a result, they should be 
kept for output if they appear within other relevant tags, but filtered out if they appear withinirrelevant tags

1. Write general expressions for OPEN-TAG and CLOSE-TAG
2. Write regular expressions for TOKENS
3. Create global stack to check matched tags are properly nested
4. Filter everything but relevant tokens

REGEXP
OPEN-TAG: <[^/]([^>]*)>
CLOSE-TAG: <[/]([^>]*)>


WORD: strings of letters and digits separated by spaces, tabs, newlines, and most of the punctuation marks.
For example, “John”, “compiler”, “mp3”, “123abc” are all treated as WORD tokens.

NUMBER: integers and real numbers, with possible positive and negative signs.

APOSTROPHIZED: words such as “John’s”, “O’Reily”,  “O’Reily’s”, and “You’re” should be treated as single tokens.  
However, “world’cup”and “this’is’just’a’test”arelikely to be typosand perhaps should be split.  
For the time being, however, you can just write a general pattern for apostrophized words that contain multiple parts separated by apostrophes.  
As a result, strings like “world’cup” and “this’is’just’a’test” will all be treated as singleapostrophized tokensfor now.

HYPHENATED: words such as “data-base” and “father-in-law” should be treated as single tokens.  
However, “---“ should be treated as a sequence of punctuation marks.  
Note that when a hyphenated token is ended withan apostrophized suffix, it will be classified as an apostrophized token such as “father-in-law’s”.  
Again, for the time being, you can write a general pattern for hyphenated words that contain multiple parts separated by hyphens as singlehyphenated tokens.  
For example, “this-is-just-a-test” can be treated as a hyphenated tokenfor now.

PUNCTUATION: Any symbol that does not contribute to the tokens above should be treated as a punctuation mark.