Bài 1:
- Mô hình đoán đúng "capital" - tỉ lệ dự đoán 0.9341
- BERT phù hợp cho Masked LM vì BERT là encoder-only, bidirectional - nhìn cả trái và phải của từ bị mask


Bài 2:
- Kết quả có hợp lý 
- GPT phù hợp cho text-generation vì GPT là decoder-only, unidirectional, được huấn luyện để dự đoán từ tiếp theo.


Bài 3:
- Kích thước vector là bao (1, 768), đây là hidden_size của bert-base-uncased.
- Dùng attention_mask để bỏ qua padding khi tính trung bình, else vector sẽ bị nhiễu.