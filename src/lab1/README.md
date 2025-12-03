# Lab 1: Text Tokenization and Preprocessing

## Overview
Lab 1 focuses on fundamental text processing techniques including tokenization, vocabulary building, and document-term matrix creation. This lab demonstrates both simple and regex-based tokenization approaches.

## Contents

### Part 1: Basic Tokenization
- **SimpleTokenizer**: Basic whitespace-based tokenization with lowercasing
- **RegexTokenizer**: Advanced regex-based tokenization for handling punctuation and special cases
- **Comparison**: Understanding differences between tokenization strategies

**Main file**: `main.py` (Lab_01)

### Part 2: Document-Term Matrix (Lab_02 Part)
- **Vocabulary Building**: Creating word-to-index mappings
- **Count Vectorization**: Building document-term frequency matrices
- **Application**: Foundation for text classification and NLP tasks

**Main file**: `lab02_part/main.py`

## Dataset
- **Source**: UD English-EWT (Universal Dependencies)
- **File**: `en_ewt-ud-train.txt`
- **Format**: Annotated English sentences with linguistic tags

## How to Run

### Part 1: Tokenization
```bash
python main.py
```

Expected output:
- Tokenization results on test sentences
- Comparison between SimpleTokenizer and RegexTokenizer
- Sample from UD_English-EWT corpus

### Part 2: Document-Term Matrix
```bash
cd lab02_part
python main.py
```

Expected output:
- Vocabulary statistics (number of unique words)
- Document-term matrix representation
- Sample document vectors

## Key Concepts

### Tokenization Challenges
1. **Punctuation Handling**: How to treat punctuation attached to words
2. **Contractions**: Splitting contractions (e.g., "isn't" → "isn" + "'" + "t")
3. **Special Characters**: Handling numbers, symbols, URLs
4. **Language-Specific Issues**: Hyphens, apostrophes, emoji

### Vectorization Approach
- **Count-based**: Simple word frequency
- **Binary**: Word presence/absence
- **TF-IDF** (advanced): Term frequency-inverse document frequency weighting

## Output Examples

### Tokenization
```
Original: "Hello, world! This is a test."
SimpleTokenizer: ['hello', ',', 'world', '!', 'this', 'is', 'a', 'test', '.']
RegexTokenizer: ['hello', ',', 'world', '!', 'this', 'is', 'a', 'test', '.']
```

### Document-Term Matrix
```
Vocabulary: {'.': 0, 'a': 1, 'ai': 2, 'i': 3, 'is': 4, 'love': 5, 'nlp': 6, ...}
Document 1: [1, 0, 0, 1, 0, 1, 1, 0, 0, 0]
Document 2: [1, 0, 0, 1, 0, 1, 0, 0, 1, 0]
```

## Project Structure

```
lab1/
├── main.py                      # Part 1: Tokenization
├── src/
│   ├── core/
│   │   ├── dataset_loaders.py  # Data loading utilities
│   │   └── interfaces.py        # Abstract base classes
│   └── preprocessing/
│       ├── simple_tokenizer.py  # Simple tokenizer implementation
│       └── regex_tokenizer.py   # Regex-based tokenizer
├── lab02_part/                  # Part 2: Vector representation
│   ├── main.py
│   ├── src/
│   │   ├── core/
│   │   ├── preprocessing/
│   │   └── representations/
│   │       └── count_vectorizer.py  # Document-term matrix creation
│   └── en_ewt-ud-train.txt
└── en_ewt-ud-train.txt
```

## Key Files Description

### `simple_tokenizer.py`
- Simple whitespace-based word splitting
- Lowercasing for normalization
- Removes common punctuation

### `regex_tokenizer.py`
- Regular expression patterns for tokenization
- Handles complex punctuation scenarios
- More granular token splitting

### `count_vectorizer.py`
- Converts token sequences to numerical vectors
- Builds vocabulary from documents
- Creates document-term matrices

### `dataset_loaders.py`
- Utility functions for loading data
- Parsing UD format
- Data preprocessing pipeline

## Learning Outcomes

After completing this lab, you should understand:
- ✅ Basic text preprocessing techniques
- ✅ Different tokenization strategies and their trade-offs
- ✅ Creating document representations for machine learning
- ✅ Working with real linguistic datasets (UD)
- ✅ Building vocabulary and handling OOV (out-of-vocabulary) words

## Related Labs
- **Lab 2**: Apply these techniques in a full NLP pipeline with Spark
- **Lab 3-6**: Use tokenization as foundation for embedding and deep learning models

## Notes
- This lab uses relatively small dataset for demonstration
- Tokenization quality significantly impacts downstream tasks
- Different tokenization strategies suit different NLP applications

## Report
See `../../report/lab1.md` for detailed implementation report.
