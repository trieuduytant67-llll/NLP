1. Implementation:
Setup: Installed libs: gensim, scikit-learn, datasets.
Data: https://huggingface.co/datasets/zeroshot/twitter-financial-news-sentiment (or just take from datasets like i did), split into training (train_dataset) and validation (test_dataset)
Sorten:
	- Load dataset
	- Classify text
	- Put in model
	- Evaluate
2. Code execution: run each block, top to bottom

3. Result
- Precision: correctly predicted instances .
- Recall: proportion of correctly predicted out of all actual instances of that class.
- F1-score: harmonic mean of precision and recall - model's performance for each class.
- Support: number of actual instances for each class in the validation dataset.

- Accuracy: The overall accuracy of the model .
- Macro Avg: This is the average of the precision, recall, and F1-score across all classes, without considering the support for each class.
- Weighted Avg: This is the average of the precision, recall, and F1-score across all classes, weighted by the support for each class - more representative performance metric when classes are imbalanced.

a) Base case
- The model achieves 77% overall accuracy, which is solid for a simple baseline.
- Positive sentiment (class 2) is detected very accurately (F1 = 0.86), likely because it’s the dominant class.
- Negative (class 0) has low recall (0.27), meaning the model misses many negative samples.
- The macro-average F1 = 0.61, lower than accuracy, reflects class imbalance — the model performs well on majority classes but less so on minority ones.

In summary, the baseline Logistic Regression model shows good overall accuracy, but its performance varies across classes, particularly in terms of recall for classes 0 and 1. Class 2, being the majority class, is predicted with high recall and F1-score.

b) Improve case
These are the classification reports for the Multinomial Naive Bayes, Gradient Boosting Classifier, and MLP Classifier models after applying preprocessing and feature selection.

Multinomial Naive Bayes Model:
- Accuracy: 77%
- Similar precision for class 0 and 1 compared to baseline, but significantly lower recall.
- High recall for class 2, similar to the baseline.
- Lower f1-scores for classes 0 and 1 indicate poorer performance on these classes.
=> Overall accuracy is slightly lower than the baseline Logistic Regression model.

Gradient Boosting Classifier Model:
- Accuracy: 75%
- Higher precision for classes 0 and 1 compared to the baseline and Naive Bayes.
- Very low recall for classes 0 and 1.
- High recall for class 2.
- Lower f1-scores for classes 0 and 1 due to low recall.
=> Overall accuracy is the lowest among the evaluated models so far.

MLP Classifier Model:
- Accuracy: 76%
- More balanced precision and recall across all classes compared to the other models.
- Lower precision and recall for class 2 compared to the baseline and Naive Bayes, but still relatively high.
- Higher f1-scores for classes 0 and 1 compared to Naive Bayes and Gradient Boosting, indicating better performance on the minority classes.
=> Overall accuracy is slightly lower than the baseline but higher than Gradient Boosting.


Overall, the baseline Logistic Regression model performed best in terms of overall accuracy and handling the majority class. The MLP classifier showed promise in balancing performance across all classes, particularly improving the f1-scores for the minority classes, but at a slight cost to overall accuracy.

4. Challenge:
	- Diffirate the improve and baseline
	- Wrong naming
	- Foget to wipe old data so you just run wrong result or dataset


5. Resouces:
	- Scikit-learn Documentation: https://scikit-learn.org/stable/
	- Hugging Face Datasets: https://huggingface.co/docs/datasets
	- NLTK Documentation: https://www.nltk.org/
	- Text Classification Best Practices (TF-IDF + Logistic Regression baseline)
	- Alot help from ChatGPT + Gemini