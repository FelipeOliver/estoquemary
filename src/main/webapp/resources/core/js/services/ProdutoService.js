function ProdutoService(){
	
	var _findAll = function(){
		return $.get(SERVER_APP + '/produto/findAll');
	}
	
	var _add = function(produto){
		return $.post(SERVER_APP + '/produto/add', produto);
	}
		
	return {
		findAll : _findAll,
		
		add: _add
	}
}