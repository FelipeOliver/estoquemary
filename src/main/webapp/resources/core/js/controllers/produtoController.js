app.controller('produtoController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	self.produto;
	
	self.addProduto = function() {
		$.post("produto/add", self.produto)
		.success(function(data){
			alert(data);
		});
		$.get("produto/findAll")
		.success(function(data){
			produtos = data;
		});
	};
}]);