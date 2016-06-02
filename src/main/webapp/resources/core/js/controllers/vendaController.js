app.controller('vendaController',['$scope', '$http', function($scope, $http){
	
	var self = this;

	self.salvo = false;
	
	self.produtosDisponiveis = []; //= JSON.parse(JSON.stringify(self.produtos));
	
	self.disponiveis = [];
	
	self.produtosAdicionados = [];
	
	self.clientes = {};
	
	self.venda = {};
	
	self.addProduto = function(produto) {
		var i = self.produtosDisponiveis.indexOf(produto);
		self.produtosAdicionados.push(produto);
		if(i != -1){
			self.produtosDisponiveis.splice(i,1);
		}
	};
	
	 var _init = function(){
		 $scope.$apply(function(){
			 self.produtosAdicionados.forEach(function(e, i){
				var a;
				self.disponiveis.forEach(function(f, i){
					if(e.produto.codProduto == f.codProduto){
						a = i;
					}
				});
				
				if(a != -1){
					self.disponiveis.splice(a,1);
				}
			 });
			 
			 self.disponiveis.forEach(function(e,i){
				var lista = {
						id: null,
						produto : {
							codProduto : e.codProduto,
							descricao : e.descricao,
							pontuacao : e.pontuacao
						},
						venda : {
							idVenda : null
						},
						valorVendido : e.valorPago,
						qntdProdutos : e.qntdProdutos
				};
				self.produtosDisponiveis.push(lista);
			 });
			 
			if(self.venda.idVenda != null){
				self.salvo = true;
			}
		});
	 }
	window.onload =_init;

	self.rmvProduto = function(produto) {
		console.log(produto.quantidade);
		var i = self.produtosAdicionados.indexOf(produto);
		self.produtosDisponiveis.push(produto);
		if(i != -1){
			self.produtosAdicionados.splice(i,1);
		}
	};
	
	self.addVenda = function(){
		console.log(self.venda);
		$http.post("/estoquemary/venda/addVenda", self.venda)
		.success(function(data){
			alert(data);
		});
	};

	self.addList = function(){
		console.log(self.produtosAdicionados);
		self.produtosAdicionados.forEach(function(e,i){
			e.venda = self.venda;
		});
		console.log(self.produtosAdicionados);
		$http.post("/estoquemary/produtosVenda/addList", self.produtosAdicionados)
		.success(function(data){
			alert(data);
		});		
	}
	
	self.pesquisaCliente = function(){
		console.log(self.campoPesquisaCliente);
		$http.get("/estoquemary/cliente/findByName/" + self.campoPesquisaCliente).
		success(function(data){
			self.clientes = data;
		});
	};
	
	self.pegaCliente = function(cliente){
		self.venda.cliente = cliente;
		$('#myModalCliente').modal('hide');
	}
	
}]);