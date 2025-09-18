# NLP - Projects
========================================================================================
18/9/2025
- i follow the step  that was asked in topics
- most of the error/ time fixing error was form syntax error and wrong directory
Lab_01

result:

--- Test on sentences ---

Original: Hello, world! This is a test.
SimpleTokenizer: ['hello', ',', 'world', '!', 'this', 'is', 'a', 'test', '.']
RegexTokenizer:  ['hello', ',', 'world', '!', 'this', 'is', 'a', 'test', '.']

Original: NLP is fascinating... isn't it?
SimpleTokenizer: ['nlp', 'is', 'fascinating', '.', '.', '.', "isn't", 'it', '?']
RegexTokenizer:  ['nlp', 'is', 'fascinating', '.', '.', '.', 'isn', "'", 't', 'it', '?']

Original: Let's see how it handles 123 numbers and punctuation!
SimpleTokenizer: ["let's", 'see', 'how', 'it', 'handles', '123', 'numbers', 'and', 'punctuation', '!']
RegexTokenizer:  ['let', "'", 's', 'see', 'how', 'it', 'handles', '123', 'numbers', 'and', 'punctuation', '!']

--- Tokenizing Sample Text from UD_English-EWT ---
Original Sample: Al-Zaman : American forces killed Shaikh Abdullah al-Ani, the preacher at the
mosque in the town of ...
SimpleTokenizer Output (first 20 tokens): ['al-zaman', ':', 'american', 'forces', 'killed', 'shaikh', 'abdullah', 'al-ani', ',', 'the', 'preacher', 'at', 'the', 'mosque', 'in', 'the', 'town', 'of', 'qaim', ',']
RegexTokenizer Output (first 20 tokens): ['al', '-', 'zaman', ':', 'american', 'forces', 'killed', 'shaikh', 'abdullah', 'al', '-', 'ani', ',', 'the', 'preacher', 'at', 'the', 'mosque', 'in', 'the']


Lab_02

result:


--- Vocabulary ---
{'.': 0, 'a': 1, 'ai': 2, 'i': 3, 'is': 4, 'love': 5, 'nlp': 6, 'of': 7, 'programming': 8, 'subfield': 9}

--- Document-Term Matrix ---
[1, 0, 0, 1, 0, 1, 1, 0, 0, 0]
[1, 0, 0, 1, 0, 1, 0, 0, 1, 0]
[1, 1, 1, 0, 1, 0, 1, 1, 0, 1]


========================================================================================
