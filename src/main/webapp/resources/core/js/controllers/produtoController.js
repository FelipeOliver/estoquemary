app.controller('produtoController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	var produtoService = new ProdutoService();
	
	self.produto;
	
	self.addProduto = function() {
		//$.post("/produto/add", self.produto)
		produtoService.add(self.produto)
		.success(function(data){
			alert(data);
		});
		//$.get("/produto/findAll")
		produtoService.findAll()
		.success(function(data){
			self.produtos = data;
		});
	};
}]);