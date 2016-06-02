app.controller('clienteController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	var clienteService = new ClienteService();
	
	self.addCliente = function() {
		console.log(self.cliente);
		clienteService.add(self.cliente)
		.success(function(data){
			alert(data);
		});
	};
}]);