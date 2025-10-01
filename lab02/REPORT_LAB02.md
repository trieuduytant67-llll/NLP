Implementation
-------------------
1. Read the Data:
   - Load data: c4-train.00000-of-01024-30K.json into a Spark DataFrame.
   - Each line in the file is a separate JSON record.
2. Text Preprocessing:
   - Used RegexTokenizer to split the text into tokens (words).
   - Applied StopWordsRemover to filter out common, low-information words.
3. Vectorization:
   - Used HashingTF to convert tokens into term frequency vectors.
   - Applied IDF to weigh the vectors, reducing the importance of frequent words.
4. Save Results:
   - Persisted the final feature vectors to lab17_pipeline_output.txt in the same directory as the code.
5. Logging:
   - Recorded job start/end time, performance metrics, and any errors to lab17_metrics.log.

How to Run the Code and Log Results
-----------------------------------
- Place the data file in the specified path (src/data/c4-train.00000-of-01024-30K.json).
- Run the project using 'sbt run'
- After execution, check lab17_pipeline_output.txt for results and lab17_metrics.log for performance and error logs.

Explanation of Results
----------------------
- The output file contains the original text and its corresponding TF-IDF feature vector for the first 20 records.
- The log file reports pipeline fitting time, transformation time, vocabulary size, and notes about hash collisions if the vocabulary exceeds the set feature vector size.

Difficulties Encountered and Solutions
--------------------------------------
- File Not Found Error:
  The code failed if the data file was not in the expected location.
  > Ensured the file path was correct and updated the code to match the actual location.
- Output Directory Issues:
  The results file was not created if the output directory did not exist.
  > Used code to create the directory if missing/changed the output path to the code directory.


Pre-trained Models
------------------
- None
