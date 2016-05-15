app.controller('clienteController',['$scope', '$http', function($scope, $http){
	
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
	};
	
	self.pesquisaCliente = function(){
		console.log(self.campoPesquisaCliente);
		$http.get("/estoquemary/cliente/findByName/" + self.campoPesquisaCliente).
		then(function(response){
			self.teste(response.data);
		});
//		clienteService.findByName(self.campoPesquisaCliente)
//		.then(function(data){
//			self.teste(data.data);
//		});
	};
	
	self.teste = function(data){
		self.clientes = data;
		console.log(self.clientes);
	}
}]);