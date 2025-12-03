from typing import List, Dict
from src.core.interfaces import Vectorizer, Tokenizer

class CountVectorizer(Vectorizer):
    def __init__(self, tokenizer: Tokenizer):
        self.tokenizer = tokenizer
        self.vocabulary: Dict[str, int] = {}

    def fit(self, corpus: List[str]):
        unique_tokens = set()
        for doc in corpus:
            tokens = self.tokenizer.tokenize(doc)
            unique_tokens.update(tokens)

        # Sort to ensure consistent index ordering
        self.vocabulary = {token: idx for idx, token in enumerate(sorted(unique_tokens))}

    def transform(self, documents: List[str]) -> List[List[int]]:
        vectors = []
        for doc in documents:
            vector = [0] * len(self.vocabulary)
            tokens = self.tokenizer.tokenize(doc)
            for token in tokens:
                if token in self.vocabulary:
                    vector[self.vocabulary[token]] += 1
            vectors.append(vector)
        return vectors
