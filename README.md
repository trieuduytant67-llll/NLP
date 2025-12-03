# NLP Labs - Natural Language Processing Study Projects

## Overview

This repository contains a comprehensive series of Natural Language Processing (NLP) laboratories covering fundamental concepts to advanced deep learning techniques. Each lab is structured with clear documentation, source code, and detailed reports.

## Repository Structure

```
./
├── src/                          # Source code for all labs
│   ├── lab1/                    # Tokenization & preprocessing
│   ├── lab2/                    # NLP Pipeline (Spark)
│   ├── lab3/                    # Word embeddings & similarity
│   ├── lab4/                    # Text classification
│   ├── lab5/                    # Deep learning with PyTorch & RNNs
│   └── lab6/                    # Transformers (BERT/GPT)
├── report/                       # Detailed lab reports (markdown format)
│   ├── lab1.md
│   ├── lab2.md
│   ├── lab3.md
│   ├── lab4.md
│   ├── lab5_*.md                # Multiple lab5 sub-reports
│   └── lab6.md
├── notebook/                     # Jupyter notebooks for interactive experimentation
│   ├── lab3.ipynb
│   ├── lab4.ipynb
│   ├── lab5_*.ipynb             # Multiple lab5 notebooks
│   └── lab6.ipynb
├── data/                         # Datasets (structure only, actual data excluded from git)
│   └── README.md                # Data schema and description
├── test/                         # Unit tests (if applicable)
├── .gitignore                    # Git ignore rules
└── README.md                     # This file
```

## Lab Descriptions

### **Lab 1: Tokenization & Preprocessing**
- **Topics**: Text preprocessing, tokenization (simple & regex-based)
- **Output**: Document-term matrices, vocabulary creation
- **Files**: `src/lab1/`, `report/lab1.md`

### **Lab 2: NLP Pipeline (Apache Spark)**
- **Topics**: Large-scale text processing with Spark
- **Techniques**: RegexTokenizer, StopWordsRemover, HashingTF, TF-IDF
- **Output**: Feature vectors, pipeline metrics and logs
- **Files**: `src/lab2/`, `report/lab2.md`

### **Lab 3: Word Embeddings & Similarity**
- **Topics**: Pre-trained embeddings (GloVe), Word2Vec with Spark
- **Techniques**: Cosine similarity, dimensionality reduction (PCA, t-SNE)
- **Visualization**: Word relationship clustering
- **Files**: `notebook/lab3.ipynb`, `report/lab3.md`

### **Lab 4: Text Classification**
- **Topics**: Multiple classification algorithms
- **Models**: 
  - Logistic Regression (baseline, TF-IDF)
  - Multinomial Naive Bayes
  - Gradient Boosting Classifier
  - MLP Classifier
- **Dataset**: Twitter Financial News Sentiment
- **Files**: `notebook/lab4.ipynb`, `report/lab4.md`

### **Lab 5: Deep Learning with PyTorch & RNNs**
Sub-divided into multiple components:

#### 5.1 PyTorch Basics
- Tensors, autograd, nn.Module fundamentals
- Files: `notebook/lab5_pytorch.ipynb`, `report/lab5_pytorch.md`

#### 5.2 Intent Detection (RNNs for Text)
- Intent classification with multiple architectures
- Models: TF-IDF, Word2Vec, LSTM (pre-trained & from scratch)
- Files: `notebook/lab5_rnns_text.ipynb`, `report/lab5_rnns_text.md`

#### 5.3 POS Tagging (BiLSTM)
- Part-of-Speech tagging on UD English corpus
- Architecture: Embedding → RNN → Linear
- Achieved: 79.79% dev accuracy
- Files: `notebook/lab5_rnns_pos.ipynb`, `report/lab5_rnns_pos.md`

#### 5.4 General Deep Learning
- Core PyTorch and neural network concepts
- Files: `notebook/lab5_main.ipynb`, `report/lab5_main.md`

### **Lab 6: Transformer Models (BERT & GPT)**
- **Topics**: Understanding transformer architecture, BERT vs GPT
- **Models**:
  - **BERT**: Masked language modeling, bidirectional encoding
  - **GPT**: Text generation, left-to-right decoding
- **Applications**: 
  - Masked token prediction (BERT)
  - Text generation (GPT)
  - Sentence embeddings extraction
- **Key Insight**: Understanding when to use encoder vs decoder-only models
- **Files**: `notebook/lab6.ipynb`, `report/lab6.md`

## How to Use This Repository

### Prerequisites
```bash
pip install torch torchvision torchaudio
pip install transformers datasets
pip install gensim scikit-learn nltk
pip install pandas numpy matplotlib seaborn plotly
pip install pyspark
pip install jupyter notebook
```

### Running the Labs

**Option 1: Interactive Notebooks (Recommended)**
```bash
jupyter notebook
# Navigate to notebook/ folder and open desired .ipynb files
```

**Option 2: Running Source Code**
- Each lab's source code is in `src/lab{n}/`
- For Scala projects (Lab 2): Use `sbt run`
- For Python projects: Use `python main.py` or follow instructions in source files

### Viewing Reports
- Each lab has a detailed report in `report/lab{n}.md`
- Reports include:
  - Implementation steps
  - Results and analysis
  - Challenges faced and solutions
  - Key insights and conclusions

## Key Concepts Covered

| Topic                 | Lab |
|-------                |-----|
| Tokenization          | 1, 2 |
| Text Preprocessing    | 1, 2 |
| Word Embeddings       | 3, 5 |
| Text Classification   | 4, 5 |
| Neural Networks       | 5, 6 |
| RNNs/LSTMs            | 5 |
| Transformers          | 6 |
| BERT/GPT              | 6 |

## Notes on Data

- The `data/` directory contains only **structure and schema files**, not actual datasets
- Large data files are ignored by `.gitignore` to keep repository size manageable
- Dataset sources:
  - Lab 1-2: UD English EWT corpus
  - Lab 4: Twitter Financial News Sentiment (from Hugging Face)
  - Lab 5: CLINC Intent Detection, UD English EWT
  - Lab 6: General datasets from Hugging Face/Transformers library

## Resources & References

- [PyTorch Documentation](https://pytorch.org/docs/)
- [Transformers Library (Hugging Face)](https://huggingface.co/docs/transformers/)
- [Gensim Documentation](https://radimrehurek.com/gensim/)
- [Scikit-learn Documentation](https://scikit-learn.org/)
- [Spark MLlib Documentation](https://spark.apache.org/docs/latest/ml-guide.html)
- [NLTK Documentation](https://www.nltk.org/)



By completing these labs, I  understand:
- Fundamental NLP preprocessing and tokenization techniques
- Traditional NLP approaches (TF-IDF, feature engineering)
- Word representation and embedding methods
- Deep learning architectures for NLP (RNNs, LSTMs)
- Modern transformer models (BERT, GPT) and their applications
- Practical implementation using PyTorch and modern NLP libraries

## License


This repository is for educational purposes.

