app.controller('clienteController', ['$scope', '$http', function ($scope, $http) {
	
	var self = this;
	
	var clienteService = new ClienteService();
	
	self.cliente;
	
	self.clientes = {};
	
	self.addCliente = function() {
		console.log(self.cliente);
		clienteService.add(self.cliente)
		.success(function(data){
			alert(data);
		});
	}
	
	self.pesquisaCliente = function(){
		console.log(self.campoPesquisaCliente);
		clienteService.findByName(self.campoPesquisaCliente)
		.always(function(data){
			self.clientes = data;
			console.log(data);
		});
		console.log(self.clientes);
    }
    
}]);