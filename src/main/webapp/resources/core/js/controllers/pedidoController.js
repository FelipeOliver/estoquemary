app.controller('pedidoController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	self.salvo = false;
	
	self.produtoService = new ProdutoService();
	
	self.produtosDisponiveis = []; //= JSON.parse(JSON.stringify(self.produtos));
	self.disponiveis = [];
	
	self.produtosAdicionados = [];
	
	self.addProduto = function(produto) {
		var i = self.produtosDisponiveis.indexOf(produto);
		self.produtosAdicionados.push(produto);
		if(i != -1){
			self.produtosDisponiveis.splice(i,1);
		}
	};
	 var _init = function(){
		 $scope.$apply(function(){
			 self.produtosAdicionados.forEach(function(e, i){
				var a;
				self.disponiveis.forEach(function(f, i){
					if(e.produto.codProduto == f.codProduto){
						a = i;
					}
				});
				
				if(a != -1){
					self.disponiveis.splice(a,1);
				}
			 });
			 
			 self.disponiveis.forEach(function(e,i){
				var lista = {
						id: null,
						produto : {
							codProduto : e.codProduto,
							descricao : e.descricao,
							pontuacao : e.pontuacao
						},
						pedido : {
							codPedido : null
						},
						valorPago : e.valorPago,
						qntdProdutos : e.qntdProdutos
				};
				self.produtosDisponiveis.push(lista);
			 });
			 
			if(self.pedido != null){
				self.salvo = true;
			}
		});
	 }
	window.onload =_init;
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
		self.produtosAdicionados.forEach(function(e,i){
			e.pedido = self.pedido;
		});
		console.log(self.produtosAdicionados);
		$http.post("/estoquemary/pedidoProdutos/addList", self.produtosAdicionados)
		.success(function(data){
			alert(data);
		});		
	}
}]);