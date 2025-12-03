# Lab 2: Large-Scale NLP Pipeline with Apache Spark

## Overview
Lab 2 demonstrates building a complete NLP processing pipeline using Apache Spark, designed for processing large-scale text data. The lab implements text preprocessing, vectorization, and TF-IDF weighting on distributed computing framework.

## Architecture

The pipeline follows this data flow:
```
Raw Text Data
    ↓
[Data Loading] → Read JSON documents from Spark DataFrame
    ↓
[Tokenization] → RegexTokenizer for splitting text into tokens
    ↓
[Stop Words Removal] → Filter out common, low-information words
    ↓
[Vectorization] → HashingTF for converting tokens to term frequency vectors
    ↓
[TF-IDF Weighting] → IDF weighting to normalize frequent words
    ↓
[Similarity Search] → Find similar documents using cosine similarity
    ↓
[Results] → Output feature vectors and similarity rankings
```

## Dataset
- **File**: `c4-train.00000-of-01024-30K.json`
- **Source**: Common Crawl C4 Dataset
- **Format**: JSON (one record per line)
- **Content**: Text documents for NLP processing
- **Size**: ~30K examples (subset)

## How to Run

### Prerequisites
```bash
# Install Scala and SBT (Scala Build Tool)
# On Windows with choco:
choco install scala
choco install sbt

# Or download manually from scala-lang.org and scala-sbt.org
```

### Running the Project
```bash
# Navigate to lab2 directory
cd src/lab2

# Build and run with SBT
sbt run

# Or compile and run individually
sbt compile
sbt "run"
```

### Build Configuration
- **Build Tool**: SBT (Scala Build Tool)
- **Configuration**: `build.sbt`
- **Main Class**: `Lab17_NLPPipeline` (in `src/main/scala/`)

## Project Structure

```
lab2/
├── build.sbt                    # SBT build configuration
├── src/
│   ├── main/
│   │   └── scala/
│   │       └── Lab17_NLPPipeline.scala    # Main pipeline code
│   ├── data/
│   │   ├── c4-train.00000-of-01024-30K.json  # Input data
│   │   └── this file have no data...        # Placeholder note
│   ├── log/
│   │   └── lab17_metrics.log   # Performance metrics and logging
│   └── results/
│       ├── lab17_pipeline_output.txt      # Feature vectors output
│       └── top5aliket.txt                 # Top 5 similar documents
├── target/                      # Compiled artifacts (generated)
└── .gitignore
```

## Key Components

### 1. Data Loading
```scala
val df = spark.read.json("src/data/c4-train.00000-of-01024-30K.json")
// Each line is a separate JSON record
```

### 2. Tokenization (RegexTokenizer)
- Splits text into individual tokens (words)
- Uses regex patterns to handle punctuation
- Input: raw text documents
- Output: array of token strings

### 3. Stop Words Removal
- Filters common English words (the, a, is, etc.)
- Reduces feature space and improves signal
- Uses Spark's built-in StopWordsRemover

### 4. HashingTF (Term Frequency)
- Converts token sequences to fixed-size feature vectors
- Uses hashing trick to map terms to indices
- Efficient for high-dimensional sparse data
- Default feature dimension: 262144

### 5. IDF (Inverse Document Frequency)
- Weights terms based on their informativeness
- Common words get lower weights
- Rare words get higher weights
- Formula: IDF = log((N+1)/(df+1)) + 1

### 6. Cosine Similarity Search
- Measures similarity between document vectors
- Finds top-K most similar documents
- Computes: similarity = (A · B) / (||A|| × ||B||)

## Configuration Parameters

### limitDocuments
- Purpose: Control dataset size during testing
- Default: Can be modified in code
- Usage: For quick testing on subset of data

### Feature Vector Dimension
- TF dimension: 262144 (2^18)
- Sufficient for most applications
- Trade-off: Higher = more precision, more memory

### Output Results
- **lab17_pipeline_output.txt**: First 20 documents with vectors
  - Format: [Original Text] | [Feature Vector]
- **top5aliket.txt**: Top 5 most similar documents per query
- **lab17_metrics.log**: Execution metrics and timing

## Results Interpretation

### Output File Format
```
Document 1:
Text: "Sample text content..."
Vectors: [sparse vector representation]

Document 2:
...
```

### Metrics Logged
- Pipeline fitting time
- Transformation time
- Vocabulary size
- Hash collision notes (if applicable)

### Similarity Results
```
Query Document: [Original Text]
Top 5 Similar:
  1. [Document ID] - Similarity Score
  2. [Document ID] - Similarity Score
  ...
```

## Challenges & Solutions

### Challenge 1: File Not Found Error
- **Cause**: Data file path incorrect or file missing
- **Solution**: 
  - Verify file exists in specified path
  - Use absolute paths or relative paths from project root
  - Check path separators for Windows vs Linux

### Challenge 2: Output Directory Issues
- **Cause**: Results directory doesn't exist
- **Solution**:
  - Pre-create output directories
  - Use code to check and create directories if needed
  - Redirect output to existing paths

### Challenge 3: Memory/Performance Issues
- **Cause**: Processing too much data at once
- **Solution**:
  - Use `limitDocuments` parameter for testing
  - Process data in batches
  - Optimize feature vector dimensions

## Key Technologies

| Technology | Purpose | Version |
|------------|---------|---------|
| **Apache Spark** | Distributed computing framework | 3.x+ |
| **Scala** | Language for Spark development | 2.13+ |
| **SBT** | Build tool for Scala projects | 1.x+ |
| **Spark MLlib** | Machine learning library | Integrated with Spark |

## Advanced Features (Optional)

### 1. Feature Normalization
- Apply L2 normalization to vectors for consistency
- Standardizes vector magnitudes

### 2. Document Clustering
- Group similar documents
- Use K-means algorithm
- Analyze document relationships

### 3. Performance Optimization
- Use partitioning for better data distribution
- Cache intermediate results
- Parallelize computations

## Learning Outcomes

After completing this lab, you should understand:
- ✅ Apache Spark architecture and usage
- ✅ Building end-to-end NLP pipelines
- ✅ Handling large-scale text data
- ✅ TF-IDF vectorization for text representation
- ✅ Similarity-based document search
- ✅ Logging and monitoring pipeline execution

## Troubleshooting

### Build Fails
```bash
# Clean and rebuild
sbt clean
sbt compile
sbt run
```

### Java Heap Space Error
```bash
# Increase JVM memory
export _JAVA_OPTIONS=-Xmx4G
sbt run
```

### Scala Not Recognized
```bash
# Add Scala to PATH or use full path
/path/to/scala/bin/scala
```

## Related Labs
- **Lab 1**: Text tokenization foundations
- **Lab 3**: Word embeddings and similarity (advanced)
- **Lab 4**: Machine learning classification

## References

- [Apache Spark Documentation](https://spark.apache.org/docs/latest/)
- [Spark MLlib Guide](https://spark.apache.org/docs/latest/ml-guide.html)
- [Scala Documentation](https://scala-lang.org/documentation/)
- [SBT Documentation](https://www.scala-sbt.org/documentation.html)

## Report
See `../../report/lab2.md` for detailed implementation report and results analysis.

## Notes
- Lab 2 uses Scala, different from Python labs
- Requires JVM installation (Java 8+)
- Spark can run in local mode (single machine) or cluster mode
- This setup uses local mode for development/testing
