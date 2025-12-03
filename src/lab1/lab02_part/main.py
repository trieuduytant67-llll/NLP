from src.preprocessing.regex_tokenizer import RegexTokenizer
from src.representations.count_vectorizer import CountVectorizer

if __name__ == "__main__":
    tokenizer = RegexTokenizer()
    vectorizer = CountVectorizer(tokenizer)

    corpus = [
        "I love NLP.",
        "I love programming.",
        "NLP is a subfield of AI."
    ]

    matrix = vectorizer.fit_transform(corpus)

    print("\n--- Vocabulary ---")
    print(vectorizer.vocabulary)

    print("\n--- Document-Term Matrix ---")
    for row in matrix:
        print(row)
