1. Quantitative Comparison
==========================

F1-score (Macro) and Test Loss for all four models:

    Pipeline										  F1-score     Test Loss
    TF-IDF + Logistic Regression                      0.822567      0.000000
    Word2Vec (Avg) + Dense                            0.000533      4.128409
    Embedding (Pre-trained) + LSTM                    0.054002      3.392387
    Embedding (Scratch) + LSTM                        0.000533      4.128409

Remarks:
- TF-IDF + Logistic Regression is the only model achieving high F1-score (0.8226) and low loss,
  indicating strong performance on the current dataset.
- Models using embeddings/LSTM show low F1-score and high loss — likely due to:
      • small training dataset
      • suboptimal architecture
      • frozen embeddings or inappropriate embedding size
- Overall: for this dataset, the classical TF-IDF + Logistic Regression model remains the most stable
  and effective.

=====================================================

2. Qualitative Analysis
=======================

Test sentences and predictions:

a) "can you remind me to not call my mom"  
   (true intent: reminder_create)
       - TF-IDF + LR: calendar_set
       - Word2Vec + Dense: alarm_set
       - LSTM Pre-trained: datetime_query
       - LSTM Scratch: alarm_set

   Remarks:
       - Contains negation ("not") and a complex action → all models fail.
       - Pre-trained LSTM is somewhat closer semantically (datetime_query), showing sequence awareness
         but still insufficient training data.

-----------------------------------------------------

b) "is it going to be sunny or rainy tomorrow"  
   (true intent: weather_query)
       - TF-IDF + LR: weather_query
       - Word2Vec + Dense: alarm_set
       - LSTM Pre-trained: qa_currency
       - LSTM Scratch: alarm_set

   Remarks:
       - TF-IDF + LR predicts correctly because of clear keywords ("sunny", "rainy", "tomorrow").
       - LSTM/Word2Vec models fail due to limited data and weak learned semantics.

-----------------------------------------------------

c) "find a flight from new york to london but not through paris"  
   (true intent: flight_search)
       - TF-IDF + LR: general_negate
       - Word2Vec + Dense: alarm_set
       - LSTM Pre-trained: alarm_set
       - LSTM Scratch: alarm_set

   Remarks:
       - Complex structure with negation ("but not through paris").
       - All models fail. LSTM could handle order and negation theoretically but underfits due to
         limited data.

=====================================================

General Remarks
================

TF-IDF + Logistic Regression
    + Simple, fast, strong performance on small datasets
    - Cannot capture word order; weak with negation or complex context

Word2Vec (Avg) + Dense
    + Uses embeddings, captures basic word semantics
    - Ignores sequence order → poor on complex sentences; low F1

LSTM + Pre-trained Embedding
    + Can model sequences, word order, and negation
    - Too little training data → underfitting, low F1

LSTM + Embedding from Scratch
    + Flexible, learns its own embeddings
    - Requires large dataset; easily overfits or underfits

=====================================================

Conclusion
==========

- For small datasets, **TF-IDF + Logistic Regression is the strongest model**.
- LSTM models are theoretically superior for complex sentence structure and sequential patterns,
  but require much more data to outperform classical models.
- Techniques such as **Dropout** and **EarlyStopping** help reduce overfitting for LSTM.

