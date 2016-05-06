function ClienteService(){
	
	var _add = function(cliente){
		return $.get(SERVER_APP + '/cliente/add', cliente);
	}
	
	var _findByName = function(nome){
		return $.get(SERVER_APP + '/cliente/findByName/'+ nome);
	}
		
	return {

		add: _add,
		
		findByName: _findByName
		
	}
}