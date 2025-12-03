# Test Directory

This directory is reserved for unit tests, integration tests, and test utilities for the NLP Labs project.

## Structure

```
test/
├── unit/                    # Unit tests for individual components
│   ├── test_tokenizers.py
│   ├── test_vectorizers.py
│   └── ...
├── integration/             # Integration tests for complete workflows
│   ├── test_pipeline.py
│   └── ...
├── fixtures/                # Test data and fixtures
│   ├── sample_text.txt
│   ├── sample_data.json
│   └── ...
└── README.md               # This file
```

## Running Tests

### Unit Tests (Python)
```bash
# Run all tests
python -m pytest test/

# Run specific test file
python -m pytest test/unit/test_tokenizers.py

# Run with verbose output
python -m pytest -v test/
```

### Test Requirements
```bash
pip install pytest pytest-cov
```

## Testing Guidelines

### 1. Test Naming Convention
- Test files: `test_*.py`
- Test functions: `test_*`
- Test classes: `Test*`

### 2. Test Structure
```python
import pytest
from src.lab1.src.preprocessing import SimpleTokenizer

class TestSimpleTokenizer:
    def setup_method(self):
        """Setup before each test"""
        self.tokenizer = SimpleTokenizer()
    
    def test_tokenize_basic(self):
        """Test basic tokenization"""
        result = self.tokenizer.tokenize("Hello world")
        assert result == ["hello", "world"]
    
    def test_tokenize_with_punctuation(self):
        """Test tokenization with punctuation"""
        result = self.tokenizer.tokenize("Hello, world!")
        assert len(result) > 0
```

### 3. Coverage
```bash
# Generate coverage report
pytest --cov=src --cov-report=html test/

# View coverage in browser
open htmlcov/index.html
```

## Current Status

- [ ] Unit tests for Lab 1 tokenizers
- [ ] Unit tests for Lab 2 vectorizers
- [ ] Integration tests for Lab 3 embeddings
- [ ] Integration tests for Lab 4 classification
- [ ] Tests for Lab 5 PyTorch models
- [ ] Tests for Lab 6 Transformers

## Best Practices

1. **Isolation**: Each test should be independent
2. **Clarity**: Test names should describe what's being tested
3. **Coverage**: Aim for >80% code coverage
4. **Performance**: Keep tests fast (< 1s per test)
5. **Fixtures**: Use reusable test data

## Contributing

When adding new code:
1. Write tests first (TDD approach)
2. Ensure all tests pass
3. Maintain or improve coverage
4. Document test purpose and edge cases

---

**Note**: Tests help ensure code quality and catch regressions early. They are essential for maintaining project reliability.
