
MODEL REPORT — DEPENDENCY PARSING (spaCy)
============================================

1) Overview
-----------
This short lab demonstrates dependency parsing with spaCy (`en_core_web_md`) and simple syntactic extraction utilities.
Goals:
  - Inspect token dependencies and heads.
  - Visualize dependency trees with `displacy`.
  - Extract simple (subject, verb, object) triplets, noun modifier groups, and paths to ROOT.

============================================

2) Environment & files
-----------------------
Dependencies:
  - Python 3.8+
  - spaCy and the English model

Install:
```powershell
pip install -U spacy
python -m spacy download en_core_web_md
```

Key files:
  - `notebook/lab6_dependency_parsing.ipynb` — interactive demos and helper code
  - `report/lab6_dependency_parsing.md` — this report

============================================

3) Data & inputs
-----------------
- Short, well-formed English sentences used for demonstrations (examples embedded in the notebook).
- Optionally use UD English-EWT (CoNLL-U) for evaluation and larger-scale testing.

============================================

4) Methods / Implementations
-----------------------------
- Basic parsing: call `nlp(text)` and inspect `token.text`, `token.dep_`, `token.head`, `token.children`.
- Visualization: `spacy.displacy.serve(doc, style='dep')` to view dependency trees in a browser.
- Utilities implemented in notebook:
  - `find_main_verb(doc)` — returns token with `dep_ == 'ROOT'`.
  - `extract_noun_chunks(doc)` — collect left modifiers (`det`, `amod`, `compound`) and join with noun head.
  - `get_path_to_root(token)` — follow `.head` to the ROOT and return token sequence.
  - Simple S-V-O extractor: for each verb token, look for children with `nsubj` and `dobj` relations.

============================================

5) Examples / Demo outputs
--------------------------
Examples in the notebook produce outputs similar to:

- Token list with dependencies (token, dep, head):
  - `The det cat nsubj chased` ...

- Found triplet: (cat, chased, mouse)

- Noun modifiers for `cat`: ['big', 'fluffy', 'white']

These outputs help validate extraction heuristics interactively.

============================================

6) Results & observations
-------------------------
- Heuristics work well on short, active-voice sentences.
- Common failure cases:
  - Passive constructions (subject and object swap roles).
  - Coordinated noun phrases and nested clauses.
  - Punctuation and tokenization artifacts affecting dependency attachments.

Practical note: use `DependencyMatcher` for more robust pattern-based extraction when needed.

============================================

7) Remarks & next steps
-----------------------
- Improve triplet extraction to handle passive voice and auxiliaries.
- Add evaluation on a small UD dev set and report precision/recall.
- Consider exporting `displacy` renders to static HTML for the report.

