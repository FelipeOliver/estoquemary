app.controller('vendaController',['$scope', '$http', function($scope, $http){
	
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

	self.rmvProduto = function(produto) {
		console.log(produto.quantidade);
		var i = self.produtosAdicionados.indexOf(produto);
		self.produtosDisponiveis.push(produto);
		if(i != -1){
			self.produtosAdicionados.splice(i,1);
		}
	};
	
	self.addvenda = function(){
		$('form[name="formAddVenda"] input').attr('disabled', true);
		$('form[name="formAddVenda"] button').attr('disabled', true);
	};
	
	self.addList = function(){
		self.venda.produto = self.produtosAdicionados;
		console.log(angular.toJson(self.venda));
		$.get("/estoquemary/venda/addVenda?teste=" + angular.toJson(self.venda))
		.success(function(data){
			alert(data);
		});		
	}

}]);