app.controller('pedidoViewController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	self.produtoService = new ProdutoService();
	
	self.produtos = []; //= JSON.parse(JSON.stringify(self.produtos));
	
	self.pedido;
	
	self.liberaLinha = function(pedido){
		console.log(pedido);
	}
	
	
	self.addProduto = function(produto) {
		var i = self.produtosDisponiveis.indexOf(produto);
		self.produtosAdicionados.push(produto);
		if(i != -1){
			self.produtosDisponiveis.splice(i,1);
		}
	};

	self.rmvProduto = function(produto) {
		console.log(produto.quantidade);
		var i = self.produtosAdicionados.indexOf(produto);
		self.produtosDisponiveis.push(produto);
		if(i != -1){
			self.produtosAdicionados.splice(i,1);
		}
	};
	
	self.addPedido = function(){
		$http.post("/estoquemary/pedido/addPedido", self.pedido)
		.success(function(data){
			self.salvo = true;
			alert("Pedido Salvo");
		});
	};
	
	self.addList = function(){
		console.log(self.produtosAdicionados);
		var ll = [];
		self.produtosAdicionados.foreach(function(i,e){
			var lista;
			lista.produto.codProduto = e.codProduto;
			lista.pedido.codPedido = self.pedido.codPedido;
			lista.valorPago = e.valorPago;
			lista.qntdProdutos = e.qntdProdutos;
			ll.push(lista);
		});
		$http.post("/estoquemary/pedidoProdutos/addList", ll)
		.success(function(data){
			alert(data);
		});		
	}

}]);