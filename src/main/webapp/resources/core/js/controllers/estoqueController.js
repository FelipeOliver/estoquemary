app.controller('estoqueController',['$scope', '$http', function($scope, $http){
	
	var self = this;

	self.busca = function(){
		$( ".busca tr" ).prop('hidden', false);
		$( ".busca tr:not(:contains('"+ self.campoBusca.toUpperCase().trim() +"'))" ).prop('hidden', true);
		$( ".busca tr:contains('"+ self.campoBusca.toUpperCase().trim() +"')" ).prop('hidden', false);
	};
	

}]);