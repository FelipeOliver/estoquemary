app.controller('clienteController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	self.cliente;
	
	self.addCliente = function() {
		console.log(self.cliente);
		$.post("/estoquemary/cliente/add", self.cliente)
		.success(function(data){
			alert(data);
		});
	};
}]);