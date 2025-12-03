MODEL TRAINING REPORT — POS TAGGING (BiLSTM)
============================================

1) Overview
-----------
The POS Tagging task assigns a part-of-speech label to each token in a sentence.
In this lab, the model architecture includes:
    - Embedding layer
    - Simple RNN
    - Linear classification layer

The model is trained on the UD_English-EWT dataset (CoNLL-U format).

============================================

2) Data & Preprocessing
-----------------------
Sentence counts:
    - Train: 12544 sentences
    - Dev:    2001 sentences

Preprocessing steps:
    - Read .conllu file using load_conllu()
    - Build vocabulary:
        word_to_ix = 19675 words (including <UNK>)
        tag_to_ix  = 18 UPOS tags
    - Construct POSDataset
    - Use pad_sequence for batch padding

============================================

3) Model Architecture
---------------------
SimpleRNNForTokenClassification:
    nn.Embedding    -> maps word_id to embedding vector
    nn.RNN          -> processes the sequence of embeddings
    nn.Linear       -> predicts POS tag at each timestep

Loss function:
    CrossEntropyLoss(ignore_index=PAD_TAG)

Optimizer:
    Adam

============================================

4) Training Results
-------------------
Epoch 1/6 — train_loss: 1.6433 | train_acc: 0.6513 | dev_loss: 1.2147 | dev_acc: 0.6279
Epoch 2/6 — train_loss: 0.9851 | train_acc: 0.7318 | dev_loss: 0.9616 | dev_acc: 0.7009
Epoch 3/6 — train_loss: 0.7871 | train_acc: 0.7765 | dev_loss: 0.8441 | dev_acc: 0.7449
Epoch 4/6 — train_loss: 0.6689 | train_acc: 0.8068 | dev_loss: 0.7765 | dev_acc: 0.7684
Epoch 5/6 — train_loss: 0.5836 | train_acc: 0.8298 | dev_loss: 0.7324 | dev_acc: 0.7846
Epoch 6/6 — train_loss: 0.5168 | train_acc: 0.8486 | dev_loss: 0.7073 | dev_acc: 0.7979

Final dev accuracy: 0.7979

============================================

5) Prediction Demo
-------------------
Input: I love NLP  
Output: [('I', 'PRON'), ('love', 'VERB'), ('NLP', 'VERB')]

------------------------------------------------------------
Input: Im about to blow  
Output: [('Im', 'VERB'), ('about', 'ADP'), ('to', 'ADP'), ('blow', 'VERB')]

------------------------------------------------------------
Input: Im a Lion Piza Chicken  
Output: [('Im', 'VERB'), ('a', 'DET'), ('Lion', 'NOUN'), ('Piza', 'NOUN'), ('Chicken', 'NOUN')]

------------------------------------------------------------
Input: You diggn in me  
Output: [('You', 'PRON'), ('diggn', 'VERB'), ('in', 'ADP'), ('me', 'PRON')]

------------------------------------------------------------

============================================

6) Remarks
----------
Strengths:
    - Learns common POS tags well (PRON, VERB, DET, NOUN).
    - Dev accuracy reaches ~80%, reasonable for a simple RNN.
    - Handles OOV words thanks to <UNK>.
    - Loss decreases and accuracy increases consistently — no major overfitting.
    - Performs well on short and simple sentences.

Limitations:
    - Simple RNN only captures one-directional context → errors at sentence ends.
    - Struggles with abbreviations, proper names, slang (Im, diggn, NLP).
    - Lower accuracy than LSTM/BiLSTM or Transformer models.
    - No pretrained embeddings → weaker performance on rare / domain-specific words.
    - Padding + small batch size may reduce accuracy for long sentences.

Improvements:
    - Replace RNN with LSTM or GRU for better memory.
    - Increase embedding size, hidden size, or number of epochs.
    - Better preprocessing: lowercase, normalization, punctuation handling, proper tokenization.

============================================

7) Completion Summary
---------------------
    - Loaded and processed CoNLL-U dataset
    - Built dataset, dataloader, padding
    - Implemented simple RNN model
    - Trained and evaluated POS Tagging model

Although limited, the Simple RNN achieves stable performance with ~80% dev accuracy.
The pipeline can be extended to stronger architectures such as BiLSTM, CRF, or Transformers.

============================================
