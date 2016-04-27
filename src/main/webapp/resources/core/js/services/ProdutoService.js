function ProdutoService(){
	
	var _findAll = function(nome){
		return $.get(SERVER_APP + '/produto/findAll');
	}
		
	return {
		findAll : _findAll
	}
}