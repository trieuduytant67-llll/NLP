Phần 1: Tensor
	- Tạo Tensor: từ list (torch.tensor) hoặc từ NumPy (torch.from_numpy).
	- Tensor đặc biệt: toàn 1 (ones_like), ngẫu nhiên (rand_like).
	- Thông tin Tensor: shape, dtype, device.
	- Phép toán: +, *, / hỗ trợ broadcast; @ cho nhân ma trận.
	- Indexing & Slicing: giống NumPy (x[0], x[:,1], x[1,1]).
	- Thay đổi hình dạng: view() hoặc reshape().

Phần 2: Autograd (đạo hàm tự động)
	- Cơ chế: Tensor với requires_grad=True sẽ xây dựng computation graph.
	- Tính đạo hàm: z.backward() lưu gradient trong x.grad.
			gọi lại z.backward() mà không retain_graph=True sẽ lỗi.

Phần 3: Xây dựng mô hình với torch.nn
	- nn.Linear: lớp tuyến tính y = xW^T + b.
	- nn.Embedding: ánh xạ chỉ số từ sang vector embedding.
	- nn.Module: cách chuẩn để định nghĩa mô hình; định nghĩa forward() cho luồng dữ liệu.

Tensor + autograd là nền tảng của PyTorch.
Module (nn.Module) + layer (Linear, Embedding) là cách xây dựng mạng nơ-ron.
Có thể kết hợp các lớp để tạo mô hình phức tạp hơn, PyTorch tự động quản lý forward/backward.