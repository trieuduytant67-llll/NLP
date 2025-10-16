1. Các Bước Thực hiện Chính
	- Sử dụng Gensim: Tải mô hình GloVe tiền huấn luyện, lấy vector từ, tính độ tương đồng, và tìm từ tương tự. Xây dựng hàm nhúng câu bằng trung bình vector từ.
	- Sử dụng Spark: Cài đặt PySpark, đọc và tiền xử lý dữ liệu lớn (tập con 100 dòng), huấn luyện mô hình Word2Vec bằng Spark MLlib, và kiểm tra mô hình.
	- Trực quan hóa: Chọn các từ, lấy vector từ GloVe, giảm chiều bằng PCA và t-SNE, và vẽ biểu đồ scatter plot để xem mối quan hệ giữa các từ.
2. Phân tích Kết quả
	- Model Gensim Tiền Huấn luyện:
		+ Thể hiện khả năng nắm bắt tốt mối quan hệ ngữ nghĩa: các cặp từ liên quan (king-queen, dog-cat) có độ tương đồng cao.
		+ Tìm được các từ tương tự hợp lý (king -> prince, queen, v.v.).
	- Nhúng Câu (Gensim): Hàm nhúng câu bằng trung bình vector từ cho kết quả độ tương đồng cao giữa các câu có cùng ngữ nghĩa (sentence1-sentence2) và độ tương đồng thấp với câu không liên quan (sentence1-sentence3).
	- Model Spark Tự Huấn luyện (100 dòng):
		+ Minh họa quy trình huấn luyện với Spark MLlib.
		+ Tuy nhiên, do chỉ sử dụng 100 dòng dữ liệu, từ vựng rất hạn chế và chất lượng embedding thấp, không cho kết quả tìm từ tương tự ý nghĩa.
	- Trực quan hóa (PCA vs t-SNE):
		+ PCA cho thấy một số xu hướng nhóm cơ bản nhưng không rõ ràng.
		+ t-SNE hiệu quả hơn trong việc tạo ra các cụm từ rõ rệt dựa trên mối quan hệ ngữ nghĩa (ví dụ: cụm hoàng tộc, động vật, phương tiện, trái cây đều tách biệt). Điều này chứng tỏ embedding nắm bắt được cấu trúc ngữ nghĩa.
3. Khó khăn và Giải pháp
	- Xử lý dữ liệu lớn với Spark: Việc huấn luyện trên tập dữ liệu lớn thực tế tốn nhiều tài nguyên.
		Giải pháp: Chỉ sử dụng một tập con nhỏ (100 dòng) để minh họa quy trình Spark trong môi trường Colab.
	- Từ vựng hạn chế của model Spark tập nhỏ: Dẫn đến kết quả kiểm tra không ý nghĩa.
		Giải pháp: Hiểu rằng đây chỉ là minh họa; cần huấn luyện trên dữ liệu đủ lớn cho kết quả thực tế.
4. Hướng dẫn Chạy lại
	Chạy lần lượt từng ô mã từ trên xuống dưới trong notebook.

5. Trích dẫn
	Sử dụng các thư viện Gensim, Spark MLlib, Plotly; các khái niệm về GloVe, Word2Vec, PCA, t-SNE; và các tập dữ liệu C4, en_ewt-ud.