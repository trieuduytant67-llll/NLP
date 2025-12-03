# Lab 6: Transformer Models and BERT/GPT

## Overview
Lab 6 explores transformer-based models, particularly BERT and GPT, for various NLP tasks.

## Key Topics Covered

### 1. Masked Language Modeling (Masked LM)
- **Model**: BERT (Bidirectional Encoder Representations from Transformers)
- **Why BERT**: 
  - Encoder-only architecture
  - Bidirectional attention (sees both left and right context)
  - Perfect for understanding tasks like masked token prediction
- **Example**: Predicting masked tokens with high accuracy (0.9341 probability)

### 2. Text Generation
- **Model**: GPT (Generative Pre-trained Transformer)
- **Why GPT**:
  - Decoder-only architecture
  - Unidirectional attention (left-to-right)
  - Trained to predict the next token
- **Capability**: Generates coherent text sequences

### 3. Sentence Embeddings
- **Approach**: Use BERT's contextual embeddings
- **Technique**: 
  - Extract hidden states from all tokens
  - Use attention mask to avoid padding tokens
  - Average pooling to create sentence-level embeddings
- **Output**: Vector size (1, 768) - hidden_size of bert-base-uncased

## Architecture Differences
| Aspect | BERT | GPT |
|--------|------|-----|
| Type | Encoder | Decoder |
| Direction | Bidirectional | Unidirectional (left-to-right) |
| Primary Use | Understanding | Generation |
| Masking | Masked tokens during training | Causal masking |
| Task Example | Fill-in-the-blank | Next word prediction |

## Files
- Main notebook: `../../notebook/lab6.ipynb`
- Report: `../../report/lab6.md`

## How to Run
See the notebook in `notebook/lab6.ipynb` for interactive execution.

## Key Insights
- BERT is ideal for understanding and representation learning
- GPT excels at generation tasks
- Contextual embeddings capture semantic relationships better than static embeddings
- Attention masks are crucial for proper handling of variable-length sequences
