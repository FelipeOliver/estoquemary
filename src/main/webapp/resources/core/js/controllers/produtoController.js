app.controller('produtoController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	var produtoService = new ProdutoService();
	
	self.produto;
	
	self.produtos = [];
	
	self.addProduto = function() {
		//$.post("/produto/add", self.produto)
		produtoService.add(self.produto)
		.success(function(data){
			alert(data);
			location.reload();
		});
	};
	
	self.carregaProduto = function(produto){
		if(String(produto.valorVenda).indexOf('.') == -1)
			produto.valorVenda =  String(produto.valorVenda) + ".00";
		self.produto = produto;
	};	
	
	self.deleteProduto = function(produto){
		var c = confirm("Você deseja excluir o produto: " + produto.descricao + " ?");
		if(c){
			console.log(String(produto.codProduto));
			produtoService.remove(String(produto.codProduto))
			.success(function(data){
				alert(data);
				location.reload();
			})
			.error(function(erro){
				if(erro.status == 409){
					alert(erro.responseText);
				}
			});
		}
	};
}]);