app.controller('vendaViewController',['$scope', '$http', function($scope, $http){
	
	var self = this;
	
	self.venda;
	
	self.rmvVenda = function(venda){
		var a = confirm("Você realmente deseja excluir a venda: "+ venda.idVenda + "?");
		if(a){
			$http.post('/estoquemary/venda/delete/', venda)
			.success(function(data){
				alert("Venda deletada com sucesso.");
				location.reload();
			});
		}
	}

}]);