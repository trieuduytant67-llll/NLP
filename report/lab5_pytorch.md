Part 1: Tensors

	- Creating Tensors: from lists (torch.tensor) or from NumPy arrays (torch.from_numpy).
	- Special Tensors: all-ones (ones_like), random tensors (rand_like).
	- Tensor Information: shape, dtype, device.
	- Operations: +, *, / support broadcasting; @ is used for matrix multiplication.
	- Indexing & Slicing: same as NumPy (x[0], x[:, 1], x[1, 1]).
	- Reshaping: using view() or reshape().

Part 2: Autograd (Automatic Differentiation)

	- Mechanism: Tensors with requires_grad=True will build a computation graph.
	- Computing Gradients: z.backward() stores gradients in x.grad. Calling z.backward() again without retain_graph=True will cause an error.

Part 3: Building Models with torch.nn

	- nn.Linear: linear layer implementing y = xW^T + b.
	- nn.Embedding: maps word indices to embedding vectors.
	- nn.Module: the standard way to define models; implement forward() to define the data flow.

Tensors + autograd form the foundation of PyTorch.
nn.Module + layers (Linear, Embedding) are used to construct neural networks.
By combining layers, we can build more complex models, and PyTorch automatically handles forward and backward propagation.