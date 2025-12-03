import re
from typing import List
from src.core.interfaces import Tokenizer

class SimpleTokenizer(Tokenizer):
    def tokenize(self, text: str) -> List[str]:
        # Convert to lowercase
        text = text.lower()
        
        # Add spaces around punctuation .,?! so they become separate tokens
        text = re.sub(r'([.,?!])', r' \1 ', text)
        
        # Split by whitespace
        tokens = text.split()
        
        return tokens
