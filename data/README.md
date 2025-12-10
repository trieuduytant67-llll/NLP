# Data Directory

This directory contains datasets used across the NLP labs.

## Note on Data Organization

**For GitHub/Version Control**: Only metadata, schema descriptions, and sample data descriptions are stored here.
**Raw dataset files** are NOT committed to avoid:
- Excessive repository size
- Violating data licensing
- Making cloning slow
- Consuming too many tokens in AI analysis

## Datasets by Lab

### Lab 1-2: UD English-EWT Corpus
- **Source**: Universal Dependencies English Web Treebank
- **Format**: CoNLL-U (`.conllu`)
- **Content**: Annotated English sentences with tokenization, POS tags, dependency parsing
- **Typical Location**: `en_ewt-ud-train.txt`, `en_ewt-ud-dev.txt`
- **Size**: ~12,544 training sentences

### Lab 3: C4 Dataset (subset)
- **Source**: Common Crawl (Hugging Face)
- **Format**: JSON (`.json`)
- **File**: `c4-train.00000-of-01024-30K.json`
- **Content**: Text documents for training embeddings
- **Size**: ~30K examples (subset)

### Lab 4: Twitter Financial News Sentiment
- **Source**: Hugging Face Datasets (zeroshot/twitter-financial-news-sentiment)
- **Format**: Dataset automatically loaded via `datasets` library
- **Classes**: Negative (0), Neutral (1), Positive (2)
- **Size**: Varies by split (train/validation/test)

### Lab 5: CLINC Intent Detection
- **Source**: Intent recognition dataset
- **Format**: Dataset automatically loaded via `datasets` library
- **Content**: Intent labels for dialog system queries
- **Size**: Multiple intents and examples

### Lab 5: UD English-EWT (POS Tagging)
- Same as Lab 1-2
- **Format**: `.conllu` with CoNLL-U annotations

### Lab 6: General Text Data and Dependency Parsing
- **Source**: Hugging Face Datasets and Transformers library; UD English Web Treebank for dependency parsing
- **Format**: Automatically handled by library loaders or CoNLL-U for UD data
- **Content**: Used for BERT/GPT demonstrations and dependency parsing examples (UD English-EWT)

**Obtaining UD English-EWT (example)**
```powershell
# download a CoNLL-U file from the Universal Dependencies repo
curl -L -o en_ewt-ud-train.conllu https://raw.githubusercontent.com/UniversalDependencies/UD_English-EWT/master/en_ewt-ud-train.conllu
```

## How to Obtain Datasets

1. **UD English-EWT**: 
   ```
   Download from: https://universaldependencies.org/
   Or use: wget https://raw.githubusercontent.com/UniversalDependencies/UD_English-EWT/master/en_ewt-ud-train.conllu
   ```

2. **Hugging Face Datasets**:
   ```python
   from datasets import load_dataset
   # The code will automatically download when first used
   ```

3. **Spark/PySpark**:
   - Data files should be placed in appropriate paths as specified in respective lab notebooks

## Data File Structure Example

### CoNLL-U Format (Lab 1-5)
```
# sent_id = weblog-blogspot.com_firemist_20051226115726_UTC__0.txt_18
# text = They buy.
1	They	they	PRON	PRP	Case=Nom|Number=Plur	2	nsubj	_	_
2	buy	buy	VERB	VBP	Number=Plur|Person=3|Tense=Pres	0	root	_	SpaceAfter=No
3	.	.	PUNCT	.	_	2	punct	_	_

```

### JSON Format (Lab 2-3)
```json
{
  "text": "Sample text content for NLP processing"
}
```

## Important Notes

- All `.gitignore` rules exclude actual data files
- When pushing to GitHub, only commit:
  - This README.md
  - Data schema documentation
  - Sample/toy datasets for demonstration (if under 25MB)
- Actual large datasets should be downloaded separately by users

---

**Data Organization Last Updated**: December 2025
