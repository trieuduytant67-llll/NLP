# Lab 5: Deep Learning with PyTorch, RNNs, and Transformers

## Overview
Lab 5 is divided into multiple sub-modules exploring different neural network architectures:

### 5.1: PyTorch Basics
- **Content**: Tensor operations, autograd (automatic differentiation), nn.Module
- **Notebook**: `../../notebook/lab5_pytorch.ipynb`
- **Report**: `../../report/lab5_pytorch.md`

### 5.2: Intent Detection with RNNs (Text Classification)
- **Content**: Multiple architectures for intent classification
  - TF-IDF + Logistic Regression (baseline)
  - Word2Vec + Dense layers
  - Embedding + LSTM (pre-trained)
  - Embedding + LSTM (from scratch)
- **Dataset**: Intent classification from CLINC dataset
- **Notebook**: `../../notebook/lab5_rnns_text.ipynb`
- **Report**: `../../report/lab5_rnns_text.md`

### 5.3: POS Tagging with RNNs
- **Content**: Part-of-Speech tagging using BiLSTM architecture
- **Dataset**: UD_English-EWT corpus (12,544 train + 2,001 dev sentences)
- **Architecture**: Embedding → RNN → Linear Classification
- **Results**: 79.79% dev accuracy
- **Notebook**: `../../notebook/lab5_rnns_pos.ipynb`
- **Report**: `../../report/lab5_rnns_pos.md`

### 5.4: Main Lab 5 (General Deep Learning)
- **Notebook**: `../../notebook/lab5_main.ipynb`
- **Report**: `../../report/lab5_main.md`

## Key Architectures Covered
1. Simple RNN for sequential tasks
2. LSTM for better long-range dependencies
3. BiLSTM for bidirectional context
4. Feed-forward networks with embeddings

## How to Run
- View notebooks in `notebook/` directory
- Check reports in `report/` directory for detailed analysis and results
