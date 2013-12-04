# Notes about the design and implementation of the parser #

First, a short overview of the input format:

int f(int e) {int t = 0; t = e + 8; return (t*t); }

f:
ldc 0 // reserve stack space for local variable t
iload 12 // load e with offset 12 from MP
ldc 8
iadd
istore 16 // Save to t with offset 4*4 from MP
iload 16 // load t
iload 16 // load t again
imul
ireturn // pop an integer and return that

(First, comments will be removed.) No, comments as special tokens.
"//" until "\n" as token and "/*" until "*/", too. This is important so
that each token can take a line number. This is needed for good error
reporting later.
So, basicaly this consists of a stream of whitespace separated tokens:
"f:" "ldc" "0" "iload" "12" and so on.

As a first step, the stream of tokens is searched for InstructionTokens:

"f:" i/ldc "0" i/iload "12" and so on.

This way, the instruction tokens may be enumerated:

"f:" i/ldc@0 "0" i/load@1 "12" and so on.

As next step, labels will be evaluated. Anything ending in a ":" is
considered as a label. Labels with a following instruction are
jump / function labels, labels with a following literal (unexpanded) are
value labels.

The labels will be stored in 2 mappings (jump/value), mapping the label name
(without ":") to the result (instructionNumber/string).

In a final step, the found instruction tokens consume their needed parameters,
resulting in Instruction Instances. If any unexpanded token is left, an error
is thrown.

    String -> Code[] throws ParseException

String -> Iterate over chars
Use of CharacterIterator worse than using charAt of String class
  source: http://stackoverflow.com/a/360930/1116364
charAt is limited to Strings or more generaly CharSequnces.


CHAR -> [TokenFactory..] -> Token

do
{
nextChar <- getNextChar
} while(factories->partOfToken(nextChar))
token <- factories->token
do
{
nextChar <- getNextChar
} while(token->consume)


Reader -> LineNumberReader -> NoCommentReader
>>> Tokenizer (Word, Number, Label)


CHAR <- input.read()

