BÁO CÁO KẾT QUẢ HUẤN LUYỆN MÔ HÌNH POS TAGGING (BiLSTM)
============================================
1) Tổng quát

Bài toán POS Tagging yêu cầu gán nhãn từ loại cho từng token trong câu. 
Trong bài Lab này, mô hình được xây dựng theo kiến trúc:
    - Embedding
    - Simple RNN
    - Linear Classification
Mô hình được huấn luyện trên bộ dữ liệu UD_English-EWT (định dạng CoNLL-U).

============================================
2) Dữ liệu và tiền xử lý

Số lượng câu:
    - Train: 12544 câu
    - Dev:   2001 câu
	
Các bước tiền xử lý:
    - Đọc file .conllu bằng hàm load_conllu()
    - Tạo vocabulary:
        word_to_ix = 19675 từ (bao gồm <UNK>)
        tag_to_ix  = 18 nhãn UPOS
    - Xây dựng POSDataset
    - Dùng pad_sequence để padding câu trong batch

============================================
3) Kiến trúc mô hình

Mô hình SimpleRNNForTokenClassification gồm:
    nn.Embedding    -> chuyển word_id thành vector
    nn.RNN          -> xử lý chuỗi embedding
    nn.Linear       -> dự đoán nhãn POS tại mỗi timestep

Loss function:
    CrossEntropyLoss(ignore_index=PAD_TAG)

Optimizer:
    Adam

============================================
4) Kết quả huấn luyện
Epoch 1/6 — train_loss: 1.6433 | train_acc: 0.6513 | dev_loss: 1.2147 | dev_acc: 0.6279
Epoch 2/6 — train_loss: 0.9851 | train_acc: 0.7318 | dev_loss: 0.9616 | dev_acc: 0.7009
Epoch 3/6 — train_loss: 0.7871 | train_acc: 0.7765 | dev_loss: 0.8441 | dev_acc: 0.7449
Epoch 4/6 — train_loss: 0.6689 | train_acc: 0.8068 | dev_loss: 0.7765 | dev_acc: 0.7684
Epoch 5/6 — train_loss: 0.5836 | train_acc: 0.8298 | dev_loss: 0.7324 | dev_acc: 0.7846
Epoch 6/6 — train_loss: 0.5168 | train_acc: 0.8486 | dev_loss: 0.7073 | dev_acc: 0.7979
Final dev accuracy: 0.7979


============================================
5) Demo dự đoán

Input: I love NLP
Prediction: [('I', 'PRON'), ('love', 'VERB'), ('NLP', 'VERB')]
------------------------------------------------------------
Input: Im about to blow
Prediction: [('Im', 'VERB'), ('about', 'ADP'), ('to', 'ADP'), ('blow', 'VERB')]
------------------------------------------------------------
Input: Im a Lion Piza Chicken
Prediction: [('Im', 'VERB'), ('a', 'DET'), ('Lion', 'NOUN'), ('Piza', 'NOUN'), ('Chicken', 'NOUN')]
------------------------------------------------------------
Input: You diggn in me
Prediction: [('You', 'PRON'), ('diggn', 'VERB'), ('in', 'ADP'), ('me', 'PRON')]
------------------------------------------------------------


============================================
6) Nhận xét

Ưu điểm:
- Mô hình học tốt các nhãn phổ biến như PRON, VERB, DET, NOUN.
- Dev accuracy đạt gần 80%, phù hợp với Simple RNN cơ bản.
- Xử lý từ ngoài từ điển (OOV) khá ổn nhờ token <UNK>.
- Loss và accuracy đều giảm/tăng đều qua từng epoch, không bị overfitting mạnh.
- Mô hình hoạt động tốt với các câu đơn giản, ngắn.

Hạn chế:
- Simple RNN chỉ nhìn ngữ cảnh một chiều, đôi khi dự đoán sai nhãn từ cuối câu.
- Một số từ viết tắt, tên riêng, hoặc từ slang (Im, diggn, NLP) bị dự đoán sai.
- Accuracy thấp hơn so với mô hình LSTM/BiLSTM hoặc Transformer.
- Mô hình chưa dùng embedding pretrained nên khó nhận dạng các từ chuyên ngành hoặc ít gặp.
- Padding và batch size nhỏ có thể ảnh hưởng đến độ chính xác cho câu dài.

Hướng cải thiện:
- Sử dụng LSTM hoặc GRU thay cho RNN để ghi nhớ tốt hơn.
- Tăng kích thước embedding, hidden size, hoặc số epoch để huấn luyện sâu hơn.
- Tiền xử lý text nâng cao: lowercase, normalize, xử lý punctuation, tokenization chuẩn.

============================================
7) Hoàn thiện
    - Đọc và xử lý dữ liệu CoNLL-U
    - Tạo dataset, dataloader, padding
    - Xây dựng mô hình RNN đơn giản
    - Huấn luyện và đánh giá mô hình POS Tagging

Mặc dù còn hạn chế, mô hình Simple RNN đạt hiệu năng ổn định với dev accuracy gần 80% và có thể mở rộng sang các kiến trúc mạnh hơn như BiLSTM, CRF hoặc Transformer trong tương lai.

