from abc import ABC, abstractmethod

# Tokenizer Interface (Lab 1)
class Tokenizer(ABC):
    @abstractmethod
    def tokenize(self, text: str) -> list[str]:
        pass


# Vectorizer Interface (Lab 2)
class Vectorizer(ABC):
    @abstractmethod
    def fit(self, corpus: list[str]):
        # Learn the vocabulary from a list of documents.
        pass

    @abstractmethod
    def transform(self, documents: list[str]) -> list[list[int]]:
        # Transform documents into count vectors.
        pass

    def fit_transform(self, corpus: list[str]) -> list[list[int]]:
        # fit + transform.
        self.fit(corpus)
        return self.transform(corpus)
