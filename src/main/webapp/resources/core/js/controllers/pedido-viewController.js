app.controller('pedidoViewController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	self.pedidos;
	
	self.rmvPedido = function(pedido){
		var a = confirm("Você realmente deseja excluir o pedido: "+ pedido.codPedido + "?");
		if(a){
			$http.post('/estoquemary/pedido/delete/', pedido)
			.success(function(data){
				alert("Pedido deletado com sucesso.");
				location.reload();
			});
		}
	}

}]);