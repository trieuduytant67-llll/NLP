package com.harito.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{HashingTF, IDF, RegexTokenizer, StopWordsRemover, Tokenizer, Normalizer} //[new] (vector normalization)
import org.apache.spark.sql.functions._
import java.io.{File, PrintWriter}
// import com.harito.spark.Utils._

object Lab17_NLPPipeline {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("NLP Pipeline Example")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    println("Spark Session created successfully.")
    println(s"Spark UI available at http://localhost:4040")
    println("Pausing for 10 seconds to allow you to open the Spark UI...")
    Thread.sleep(10000)

    // 1. --- Read Dataset ---

    //[new] (custom document limit)
    val limitDocuments = 1000 // change this variable to process different number of documents

    val readStartTime = System.nanoTime() //[new] (performance measurement: read)
    val dataPath = "src/data/c4-train.00000-of-01024-30K.json"
    val initialDF = spark.read.json(dataPath).limit(limitDocuments) // Limit configurable
    val readDuration = (System.nanoTime() - readStartTime) / 1e9d //[new] (performance measurement: read)
    println(f"--> Reading data took $readDuration%.2f seconds.") //[new] (performance measurement: read)

    println(s"Successfully read ${initialDF.count()} records.")
    initialDF.printSchema()
    println("\nSample of initial DataFrame:")
    initialDF.show(5, truncate = false) // Show full content for better understanding

    // --- Pipeline Stages Definition ---

    // 2. --- Tokenization ---
    val tokenizer = new RegexTokenizer()
      .setInputCol("text")
      .setOutputCol("tokens")
      .setPattern("\\s+|[.,;!?()\"']") // Fix: Use \\s for regex, and \" for double quote

    /*
    // Alternative Tokenizer: A simpler, whitespace-based tokenizer.
    val tokenizer = new Tokenizer().setInputCol("text").setOutputCol("tokens")
    */

    // 3. --- Stop Words Removal ---
    val stopWordsRemover = new StopWordsRemover()
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("filtered_tokens")

    // 4. --- Vectorization (Term Frequency) ---
    val hashingTF = new HashingTF()
      .setInputCol(stopWordsRemover.getOutputCol)
      .setOutputCol("raw_features")
      .setNumFeatures(20000) // Set the size of the feature vector

    // 5. --- Vectorization (Inverse Document Frequency) ---
    val idf = new IDF()
      .setInputCol(hashingTF.getOutputCol)
      .setOutputCol("tfidf_features") //[new] (rename to avoid conflict before normalization)

    //[new] (vector normalization)
    val normalizer = new Normalizer()
      .setInputCol(idf.getOutputCol)
      .setOutputCol("features")
      .setP(2.0) // L2 normalization

    // 6. --- Assemble the Pipeline ---
    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, stopWordsRemover, hashingTF, idf, normalizer)) //[new] (add normalization to pipeline)

    // --- Time the main operations ---

    println("\nFitting the NLP pipeline...") // Fix: Ensure single-line string literal
    val fitStartTime = System.nanoTime()
    val pipelineModel = pipeline.fit(initialDF)
    val fitDuration = (System.nanoTime() - fitStartTime) / 1e9d
    println(f"--> Pipeline fitting took $fitDuration%.2f seconds.")

    println("\nTransforming data with the fitted pipeline...") // Fix: Ensure single-line string literal
    val transformStartTime = System.nanoTime()
    val transformedDF = pipelineModel.transform(initialDF)
    transformedDF.cache() // Cache the result for efficiency
    val transformCount = transformedDF.count() // Force an action to trigger the transformation
    val transformDuration = (System.nanoTime() - transformStartTime) / 1e9d
    println(f"--> Data transformation of $transformCount records took $transformDuration%.2f seconds.")

    // Calculate actual vocabulary size after tokenization and stop word removal
    val vocabStartTime = System.nanoTime() //[new] (performance measurement: vocab size)
    val actualVocabSize = transformedDF
      .select(explode($"filtered_tokens").as("word"))
      .filter(length($"word") > 1) // Filter out single-character tokens
      .distinct()
      .count()
    val vocabDuration = (System.nanoTime() - vocabStartTime) / 1e9d //[new] (performance measurement: vocab size)
    println(f"--> Vocabulary calculation took $vocabDuration%.2f seconds.") //[new]
    println(s"--> Actual vocabulary size after tokenization and stop word removal: $actualVocabSize unique terms.")

    // --- Show and Save Results ---
    println("\nSample of transformed data:") // Fix: Ensure single-line string literal
    transformedDF.select("text", "features").show(5, truncate = 50)

    val n_results = 20
    val results = transformedDF.select("text", "features").take(n_results)

    // 7. --- Write Metrics and Results to Separate Files ---

    // Write metrics to the log folder
    val log_path = "src/log/lab17_metrics.log" // Corrected path
    new File(log_path).getParentFile.mkdirs() // Ensure directory exists
    val logWriter = new PrintWriter(new File(log_path))
    try {
      logWriter.println("--- Performance Metrics ---")
      logWriter.println(f"Data read duration: $readDuration%.2f seconds") //[new]
      logWriter.println(f"Pipeline fitting duration: $fitDuration%.2f seconds")
      logWriter.println(f"Data transformation duration: $transformDuration%.2f seconds")
      logWriter.println(f"Vocabulary calculation duration: $vocabDuration%.2f seconds") //[new]
      logWriter.println(s"Actual vocabulary size (after preprocessing): $actualVocabSize unique terms")
      logWriter.println(s"HashingTF numFeatures set to: 20000")
      if (20000 < actualVocabSize) {
        logWriter.println(s"Note: numFeatures (20000) is smaller than actual vocabulary size ($actualVocabSize). Hash collisions are expected.")
      }
      logWriter.println(s"Metrics file generated at: ${new File(log_path).getAbsolutePath}")
      logWriter.println("\nFor detailed stage-level metrics, view the Spark UI at http://localhost:4040 during execution.")
      println(s"\nSuccessfully wrote metrics to $log_path")
    } finally {
      logWriter.close()
    }

    // Write data results to the results folder
    val result_path = "src/results/lab17_pipeline_output.txt" // Corrected path
    new File(result_path).getParentFile.mkdirs() // Ensure directory exists
    val resultWriter = new PrintWriter(new File(result_path))
    try {
      resultWriter.println(s"--- NLP Pipeline Output (First $n_results results) ---")
      resultWriter.println(s"Output file generated at: ${new File(result_path).getAbsolutePath}\n")
      results.foreach { row =>
        val text = row.getAs[String]("text")
        val features = row.getAs[org.apache.spark.ml.linalg.Vector]("features")
        resultWriter.println("="*80)
        resultWriter.println(s"Original Text: ${text.substring(0, Math.min(text.length, 100))}...")
        resultWriter.println(s"TF-IDF Vector: ${features.toString}")
        resultWriter.println("="*80)
        resultWriter.println()
      }
      println(s"Successfully wrote $n_results results to $result_path")
    } finally {
      resultWriter.close()
    }

        //[new] (find similar documents)
    println("\nFinding similar documents using cosine similarity...")
    import org.apache.spark.ml.linalg.Vector
    val docs = transformedDF.select("text", "features").collect()

    val baseDoc = docs(0) // pick the first document
    val baseVector = baseDoc.getAs[Vector]("features")

    //[new] (fixed cosine similarity without asBreeze)
    def cosineSimilarity(v1: Vector, v2: Vector): Double = {
      val arr1 = v1.toArray
      val arr2 = v2.toArray
      val dot = arr1.zip(arr2).map { case (x, y) => x * y }.sum
      val norm1 = math.sqrt(arr1.map(x => x * x).sum)
      val norm2 = math.sqrt(arr2.map(x => x * x).sum)
      if (norm1 == 0.0 || norm2 == 0.0) 0.0 else dot / (norm1 * norm2)
    }

    val similarities = docs.zipWithIndex.map { case (row, idx) =>
      val vec = row.getAs[Vector]("features")
      val sim = cosineSimilarity(baseVector, vec)
      (idx, row.getAs[String]("text"), sim)
    }

    val topSimilar = similarities.sortBy(-_._3).slice(1, 6) // exclude self, top 5
    println("\nTop 5 similar documents to the first one:")
    topSimilar.foreach { case (idx, text, score) =>
      println(s"[$idx] Similarity: $score%.4f | Text snippet: ${text.substring(0, Math.min(100, text.length))}...")
    }

    //[new] (write top 5 results to file)
    val similar_path = "src/results/top5aliket.txt"
    new File(similar_path).getParentFile.mkdirs() // Ensure directory exists
    val similarWriter = new PrintWriter(new File(similar_path))
    try {
      similarWriter.println(s"--- Top 5 Similar Documents to the First Document ---")
      similarWriter.println(s"Output file generated at: ${new File(similar_path).getAbsolutePath}\n")
      topSimilar.foreach { case (idx, text, score) =>
        similarWriter.println("="*80)
        similarWriter.println(s"Doc Index: $idx")
        similarWriter.println(f"Cosine Similarity: $score%.4f")
        similarWriter.println(s"Text Snippet: ${text.substring(0, Math.min(200, text.length))}...")
        similarWriter.println("="*80)
        similarWriter.println()
      }
      println(s"Successfully wrote Top 5 similar docs to $similar_path")
    } finally {
      similarWriter.close()
    }
  }
}