Exercise 1:
- The model correctly predicts "capital" — prediction accuracy: 0.9341.
- BERT is suitable for Masked LM because it is encoder-only and bidirectional, so it can see both the left and right context of the masked token.

Exercise 2:
- The results are reasonable.
- GPT is appropriate for text generation because it is decoder-only and unidirectional, trained to predict the next token.

Exercise 3:
- The vector size is (1, 768), which corresponds to the hidden_size of bert-base-uncased.
- Use attention_mask to ignore padding when averaging embeddings; otherwise, the resulting vector will be distorted.