app.controller('pedidoController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	self.salvo = false;
	
	self.produtoService = new ProdutoService();
	
	self.produtosDisponiveis = []; //= JSON.parse(JSON.stringify(self.produtos));
	
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
				self.produtosDisponiveis.forEach(function(f, i){
					if(e.produto.codProduto == f.codProduto){
						a = i;
					}
				});
				
				if(a != -1){
					self.produtosDisponiveis.splice(a,1);
				}
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
		var ll = [];
		self.produtosAdicionados.forEach(function(e,i){
			var lista;
			lista = {
					produto : {
						codProduto : e.codProduto
					},
					pedido : {
						codPedido : self.pedido.codPedido
					},
					valorPago : e.valorPago,
					qntdProdutos : e.qntdProdutos
			};
			console.log(ll);
			ll.push(lista);
		});
		$http.post("/estoquemary/pedidoProdutos/addList", ll)
		.success(function(data){
			alert(data);
		});		
	}
}]);