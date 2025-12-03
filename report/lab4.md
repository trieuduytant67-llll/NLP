1. Main Steps
	- Using Gensim: Load a pre-trained GloVe model, obtain word vectors, compute similarity, and find similar words. Implement a sentence embedding function by averaging word vectors.
	- Using Spark: Install PySpark, read and preprocess large data (use a 100-line subset), train a Word2Vec model with Spark MLlib, and evaluate the model.
	- Visualization: Select words, extract GloVe vectors, reduce dimensionality with PCA and t-SNE, and draw scatter plots to inspect word relationships.
2. Analysis of Results
	- Pre-trained Gensim model:
		+ Demonstrates a good ability to capture semantic relationships: related pairs (e.g., king-queen, dog-cat) show high similarity scores.
		+ Returns reasonable similar words (e.g., "king" -> "prince", "queen", etc.).
	- Sentence Embeddings (Gensim): Averaging word vectors to form sentence embeddings produces high similarity for semantically similar sentences (sentence1 vs sentence2) and low similarity with unrelated sentences (sentence1 vs sentence3).
	- Self-trained Spark model (100 lines):
		+ Illustrates the training workflow with Spark MLlib.
		+ However, because only 100 lines of data were used, the vocabulary is very limited and the embedding quality is poor; the model does not produce meaningful nearest-neighbor results.
	- Visualization (PCA vs t-SNE):
		+ PCA reveals some basic grouping trends but they are not very clear.
		+ t-SNE is more effective at producing distinct clusters by semantic relationship (for example: a "royalty" cluster, animals, vehicles, fruits each separate). This indicates that the embeddings capture semantic structure.
3. Challenges and Solutions
	- Processing large data with Spark: Training on a large dataset requires significant resources.
		Solution: Use a small subset (100 lines) to demonstrate the Spark workflow in a Colab environment.
	- Limited vocabulary in the small Spark-trained model: This leads to meaningless evaluation results.
		Solution: Note that this is only an illustration; a real deployment requires training on a sufficiently large dataset.
4. How to Re-run
	Run the notebook cells sequentially from top to bottom.

5. References
	Uses the libraries Gensim, Spark MLlib, and Plotly; concepts include GloVe, Word2Vec, PCA, and t-SNE; datasets referenced: C4, en_ewt-ud.
