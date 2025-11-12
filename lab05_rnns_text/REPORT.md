1. So sánh định lượng

Bảng kết quả F1-score (Macro) và Test Loss của 4 mô hình:

                        Pipeline  F1-score (Macro)  Test Loss
    TF-IDF + Logistic Regression          0.822567   0.000000
          Word2Vec (Avg) + Dense          0.000533   4.128409
  Embedding (Pre-trained) + LSTM          0.054002   3.392387
      Embedding (Scratch) + LSTM          0.000533   4.128409


Nhận xét:
- TF-IDF + Logistic Regression là mô hình duy nhất có F1-score cao (0.8226) và loss thấp, cho thấy hoạt động hiệu quả trên tập dữ liệu hiện tại.
- Các mô hình sử dụng embedding/LSTM có F1-score thấp, loss cao, nguyên nhân có thể do dữ liệu huấn luyện ít, cấu trúc mô hình chưa tối ưu hoặc embedding đóng băng/kích thước chưa phù hợp.
- Nhìn chung, với tập dữ liệu này, mô hình cổ điển TF-IDF + Logistic Regression vẫn ổn định và hiệu quả.

=====================================================
2. Phân tích định tính

Các câu thử nghiệm:

a. "can you remind me to not call my mom" (intent thật: reminder_create)
- TF-IDF + LR: calendar_set
- Word2Vec (Avg) + Dense: alarm_set
- LSTM Pre-trained: datetime_query
- LSTM Scratch: alarm_set

Nhận xét: 
- Câu có phủ định "not" và hành động phức tạp. Không mô hình nào dự đoán đúng.
- LSTM Pre-trained gần đúng hơn (datetime_query), thể hiện khả năng xử lý chuỗi nhưng chưa đủ dữ liệu để dự đoán chính xác.

b. "is it going to be sunny or rainy tomorrow" (intent thật: weather_query)
- TF-IDF + LR: weather_query 
- Word2Vec (Avg) + Dense: alarm_set
- LSTM Pre-trained: qa_currency
- LSTM Scratch: alarm_set

Nhận xét:
- TF-IDF + LR dự đoán chính xác nhờ từ khóa "sunny", "rainy", "tomorrow".
- LSTM và Word2Vec chưa học đủ quan hệ từ khóa nên dự đoán sai.

c. "find a flight from new york to london but not through paris" (intent thật: flight_search)
- TF-IDF + LR: general_negate
- Word2Vec (Avg) + Dense: alarm_set
- LSTM Pre-trained: alarm_set
- LSTM Scratch: alarm_set

Nhận xét:
- Câu phức tạp, có phủ định "but not through paris".
- Tất cả mô hình dự đoán sai. LSTM có thể nắm thứ tự từ và phủ định, nhưng dữ liệu ít nên underfit.

=====================================================
Nhận xét chung:
(+)Ưu điểm, (-)Nhược điểm
TF-IDF + Logistic Regression         
	+ Đơn giản, nhanh, hiệu quả trên dữ liệu ít.
	- Không nắm thứ tự từ, khó xử lý phủ định hoặc ngữ cảnh phức tạp.
	
Word2Vec (Avg) + Dense
	+ Sử dụng embedding, học ngữ nghĩa từ từ.
	- Không nắm thứ tự từ -> kém hiệu quả với câu phức tạp. F1-score thấp.
	
LSTM + Embedding Pre-trained
	+ Có thể xử lý chuỗi, thứ tự từ và phủ định.
	- Dữ liệu ít -> kém hiệu quả, dễ underfit, F1-score thấp.
	
LSTM + Embedding học từ đầu
	+ Linh hoạt, tự học embedding từ dữ liệu.
	- Cần nhiều dữ liệu. Dễ overfit hoặc underfit nếu dữ liệu ít.


Kết luận:
- Với tập dữ liệu nhỏ, TF-IDF + Logistic Regression là mô hình mạnh nhất.
- LSTM lý thuyết tốt hơn khi dữ liệu lớn và câu có cấu trúc phức tạp, nhưng hiện tại chưa tối ưu.
- Dropout và EarlyStopping giúp LSTM giảm overfitting.