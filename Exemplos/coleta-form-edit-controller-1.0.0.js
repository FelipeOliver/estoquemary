/**
 * Controller da coleta-form-edit.jsp
 */

var common = new Common();
var coletaService = new ColetaService();
var coletaItemService = new ColetaItemService();
var clienteService = new ClienteService();
var cliendService = new CliendService();
var tipoCargaService = new TipoCargaService();
var parceiroService = new ParceiroService();
var configApp = new ConfigApp();
var mask = new Mask();
var destTpEnd;
var remetTpEnd;
var parceiros;
var sequencia = 0;

//Felipe B. - #537 - SolutionColeta
/*------------------------------------------------------------------------------------*/
var nvl = function(variavel, nulo){
	if(variavel == null || variavel == undefined || variavel == '' || variavel == NaN)
		return nulo;
	else
		return variavel;
};

var habilitaEdicao = function(){
	var _habilitaEdicao = (nvl($.trim($('#ocorrenciaCodigo').val()),'65') == '65') ? 'S' : 'N';
	return _habilitaEdicao;
}

//Função utilizada para retirar a formatação da mascara
var limpaCnpj = function(cnpj){
	cnpj = cnpj.replace('.','');
	cnpj = cnpj.replace('.','');
	cnpj = cnpj.replace('/','');
	cnpj = cnpj.replace('-','');
	return cnpj
};

//Habilita a linha de uma ColetaItem para edição
var habilitaLinha = function(seq) {
	
	if('N' === habilitaEdicao()){
		   return;
	}
	// Coloco os botões em modo de edição
	$('#confirm' + seq).css('pointer-events', 'auto');
	$('#confirm' + seq).css('color', '#2A68A6');

	$('#delete' + seq).css('pointer-events', 'none');
	$('#delete' + seq).css('color', 'black');	
	
	 if( $('#item' + seq + 'sequencia').text() == 0){
		   $('#delete' + seq).css('pointer-events', 'auto');
	       $('#delete' + seq).css('color', 'red');	   
	   }
	
	$('#edit' + seq).css('pointer-events', 'none');
	$('#edit' + seq).css('color', 'black');		
	
	id = '.' + seq;
	//Transformo as colunas em Inputs
	$(id).each(
			function() {
				var conteudoOriginal = $(this).text();
				$(this).html(
						"<input class='" + seq
								+ "1 form-control' type='text' value='"
								+ conteudoOriginal + "' />");
			});
	$('#item'+seq+'peso input').attr('type', 'number');
	$('#item'+seq+'largura input').attr('type', 'number');
	$('#item'+seq+'altura input').attr('type', 'number');
	$('#item'+seq+'comprimento input').attr('type', 'number');
	$('#item'+seq+'cubagem input').attr('type', 'number');
	$('#item'+seq+'volume input').attr('type', 'number');
};
var adicionaLinha = function(){
	
	if('N' === habilitaEdicao()){
		   return;
	}
	
	sequencia = sequencia + 1;
	$('#tbItensColeta').append('<tr id="linhaItem'+sequencia+'">'
							   +'<td id="item'+sequencia+'coletaNCompra" hidden>'+ $('#tfNumColeta').val() +'</td>'
							   +'<td id="item'+sequencia+'coletaCiclo" hidden>'+ $('#tfCiclo').val() +'</td>'
							   +'<td id="item'+sequencia+'sequencia" hidden>0</td>'
							   +'<td id="item'+sequencia+'mercadoriaDescricao" class="'+sequencia+'"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'peso"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'largura"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'altura"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'comprimento"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'cubagem"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'volume"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'quantidadeEmp"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'mercadoriaCodigo"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'embalagemCodigo"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'remont"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'onuCodigo"></td>'
							   +'<td class="'+sequencia+'" id="item'+sequencia+'onuGrpEmb"></td>'
							   +'<td><a id="edit'+sequencia+'" href="javascript:habilitaLinha('+sequencia+');" style="color: green;"><i class="glyphicon glyphicon-pencil"></i></a> '
							   +'<a id="confirm'+sequencia+'" href="javascript:saveItem('+sequencia+');" style="color: black;pointer-events: none;"><i class="glyphicon glyphicon-ok"></i></a> '
							   +'<a id="delete'+sequencia+'" href="javascript:deletaLinha('+sequencia+');" style="color: red;pointer-events: auto;"><i class="glyphicon glyphicon-minus"></i></a>'
							   +'</td>'
							   +'</tr>');
	habilitaLinha(sequencia);
	$('#delete'+sequencia).css('color', 'red');
	$('#delete'+sequencia).css('pointer-events', 'auto');
};

var deletaLinha = function(seq){
	
	if('N' === habilitaEdicao()){
		   return;
	   }
	var coleta  = $('#item'+seq+'coletaNCompra').text();
	var ciclo   = $('#item'+seq+'coletaCiclo').text();
	var seqItem = $('#item'+seq+'sequencia').text();
	
	if( $.trim(seqItem) == 0){
		$('#linhaItem'+seq).remove();
		return;
	}
	coletaItemService.getList(coleta, ciclo).success(function(data){
		if(data.length > 1){
			coletaItemService.deleteItem(coleta, ciclo, seqItem).success(function(data){
				$('#linhaItem'+seq).remove();
				alertify.log('Item deletado com sucesso!');
			})
			.error(function(data){
				alertify.log('Erro ao deletar o Item!');
			});
		}else{
			alertify.log('Deve conter ao Menos um Item na coleta');	
		}
	})
	.fail(function(data){
		if(configApp.isSessionInvalid(data.responseText) == true) 
			window.location.reload();
		if(data.status == 404) 
			alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
		else 	
			alertify.log('Erro ao validar Coleta!');
	});
};

//Desabilita a linha de uma ColetaItem para edição
var desabilitaLinha = function(seq){
	   if('N' === habilitaEdicao()){
		   return;
	   }
	
	   //Volto os botões ao estado normal
	   $('#confirm'+seq).css('pointer-events','none');
	   $('#confirm'+seq).css('color','black');

 	   $('#delete' + seq).css('pointer-events', 'auto');
       $('#delete' + seq).css('color', 'red');	   
	   $('#edit'+seq).css('pointer-events','auto');
	   $('#edit'+seq).css('color','#2A68A6');
	   
	   id = '.' + seq  + '1';
	   //Volto as colunas para text
	   $(id).each(function(){
		   var novoConteudo = $(this).val(); 
		   $(this).parent().text(novoConteudo); 
	   });
};

//Executa o Post do ColetaItem que estava em modo de edição
var saveItem = function(seq){
	
	   if('N' === habilitaEdicao()){
		   return;
	   }
	   
	   //habilito o botão de confirma e desabilito o botão de editar
	   desabilitaLinha(seq);
	   
	   if($('#item' + seq + 'mercadoriaDescricao').text() == '' || $('#item' + seq + 'mercadoriaDescricao').text() == undefined || $('#item' + seq + 'mercadoriaDescricao').text() == null){
		   alertify.error("Favor Preencher a descrição da Mercadoria do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if($('#item' + seq + 'remont').text() == '' || $('#item' + seq + 'remont').text() == undefined ||$('#item' + seq + 'remont').text() == null){
		   alertify.error("Favor Preencher o campo Remont. do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if($('#item' + seq + 'mercadoriaCodigo').text() == '' || $('#item' + seq + 'mercadoriaCodigo').text() == undefined || $('#item' + seq + 'mercadoriaCodigo').text() == null){
		   alertify.error("Favor Preencher o código da Mercadoria do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if($('#item' + seq + 'volume').text() == '' || $('#item' + seq + 'volume').text() == undefined || $('#item' + seq + 'volume').text() == null){
		   alertify.error("Favor Preencher o Volume do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if(Number($('#item' + seq + 'volume').text()) <= 0){
		   alertify.error("O Volume tem que ser maior que 0");
		   habilitaLinha(seq);
		   return; 
	   }
	   if($('#item' + seq + 'embalagemCodigo').text() == '' || $('#item' + seq + 'embalagemCodigo').text() == undefined || $('#item' + seq + 'embalagemCodigo').text() == null){
		   alertify.error("Favor Preencher o Codigo da Embalagem do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if($('#item' + seq + 'peso').text() == '' || $('#item' + seq + 'peso').text() == undefined || $('#item' + seq + 'peso').text() == null){
		   alertify.error("Favor Preencher o peso do Item");
		   habilitaLinha(seq);
		   return; 
	   }
	   if(Number($('#item' + seq + 'peso').text()) <= 0){
		   alertify.error("O Peso tem que ser mair que 0");
		   habilitaLinha(seq);
		   return; 
	   }
	   //Monto o JSon do Item
	   var item = {'mercadoria.descricao':  $('#item' + seq + 'mercadoriaDescricao').text(),
			   	    volume: 				$('#item' + seq + 'volume').text() != '' ? $('#item' + seq + 'volume').text() : 0,
			   		peso:					$('#item' + seq + 'peso').text() != '' ? $('#item' + seq + 'peso').text().replace(',','.') : 0,  
			   		'embalagem.codigo':	    $('#item' + seq + 'embalagemCodigo').text(),
			   		remont:				    $('#item' + seq + 'remont').text(),
			   		// Coleta 
			   		coleta:			        $('#item' + seq + 'coletaNCompra').text(),
			   		ciclo:				    $('#item' + seq + 'coletaCiclo').text(),
			   		'mercadoria.codigo':	$('#item' + seq + 'mercadoriaCodigo').text(),
			   		largura:				$('#item' + seq + 'largura').text() != '' ?$('#item' + seq + 'largura').text().replace(',','.') : 0,
			   		altura:				    $('#item' + seq + 'altura').text() != '' ? $('#item' + seq + 'altura').text().replace(',','.') : 0,
			   		comprimento:			$('#item' + seq + 'comprimento').text() != '' ?$('#item' + seq + 'comprimento').text().replace(',','.') : 0,
			   		cubagem:				$('#item' + seq + 'cubagem').text() != '' ? $('#item' + seq + 'cubagem').text().replace(',','.') : 0,
			   		onuCodigo:              $('#item' + seq + 'onuCodigo').text(),
			   		onuGrpEmb:			    $('#item' + seq + 'onuGrpEmb').text(),
			   		sequencia:			    $('#item' + seq + 'sequencia').text()};
	   
	   //Realizo o Update do Item
	   coletaItemService.save(item).success(function(data, statusText, response) {
		   var chave = '#item'+seq+'sequencia'; 
		   $(chave).text(data.coletaNumero);
		   alertify.log("Item gravado com sucesso");
		  
	   })
	   .fail(function(data){
		   if(configApp.isSessionInvalid(data.responseText) == true || data.status == 405) 
				window.location.reload();
			if(data.status == 404) 
				alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
			else{
				   alertify.error("Erro ao gravar o item");
				   habilitaLinha(seq);				
			} 	
	   });
};

//Popula o ComboBox do Armazém de acordo com o Tp de Endereço do Remetente da coleta
var populaCboxArmazem = function(){
	var option = '';//'<option ></option>';
	var uf = $('#tfUfRem').val();
	armazemService.getListaPorUF(uf	).success(function(data) { 
		populaComboboxFilial(data);
		$.each(data, function(i, e) {
			if(e.codigo != $('#cbArmazem').val())
			option += '<option value="'+ common.replace(e.codigo, '-','') + '"> ' 
									   + common.replace(e.codigo, '-','') + ' - ' 
									   + common.replace(e.descricao, '-','') + ' </option>';
		});
		$('#cbArmazem').append(option);

	}).fail(function(data) {
		if(configApp.isSessionInvalid(data.responseText) == true) 
			window.location.reload();
		if(data.status == 404) 
			alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
		else 	
			alertify.alert(data.responseText);
	});
};

//Popula o ComboBox do tipo de carga de acordo com o CNPJ do pagador
var populaCboxTpCarga = function(){
	var _tpColeta = $.trim($('#tpColeta').val());
	var grupoEconomico = ($('#pagadorFrete').val() == 'R') ? $.trim($('#grupoEconomicoRemetente').val()) : $.trim($('#grupoEconomicoDestinatario').val());
	if(grupoEconomico == '0535' && _tpColeta == 'E') {
		$('#cbTpCarga').prop('readonly', true);
		$('#cbTpCarga').val('01 ');
		//$("#cbTpCarga").prop("selectedIndex", 1);
		//$('#cbTpCarga').trigger('change');
	}else{
		var cnpjPagador = $('#cnpjPagadorFrete').val();
		if(cnpjPagador != '' && cnpjPagador != undefined && cnpjPagador != null){
			cnpjPagador = $.trim(cnpjPagador);
			tipoCargaService.getListByCnpj(cnpjPagador).success(function(data) {
				$.each(data, function(i, c) {
					if(c.codigo != $('#cbTpCarga').val())
						$('#cbTpCarga').append('<option value="'+c.codigo+'">'+c.descricao+'</option>'); 
				});	
			}).fail(function(error) {
				if(error.status == 404) alertify.alert('404 - Rota não encontrada, ao busca tipos de carga. ');
				else alertify.alert("ERRO\n\n"+JSON.stringify(error));
			});
			
		}else{
			alertify.alert("O Campo CNPJ do pagador está nulo");
		}
	}
};

//Popula o ComboBox do tipo de veiculo de acordo com o Tipo de Carga escolhido
var populaCboxTpVeiculo = function(){
	var cnpjRemet = nvl($.trim($('#remetCnpj').val()),0);
	var cnpjDest  = nvl($.trim($('#destCnpj'). val()),0);
	var pagador = nvl($('#pagadorFrete').val(),0);
	var cnpjSolicitante = nvl($.trim($('#cnpjSolicitante').val()),0);
	var tpCarga = nvl($.trim($('#cbTpCarga').val()),0);
	if(cnpjRemet != '0' && cnpjDest != '0' && pagador != '0' && cnpjSolicitante != '0' && tpCarga != '0'){	
		tipoVeiculoService.getListaTpVeic(cnpjSolicitante, cnpjRemet, cnpjDest, pagador, tpCarga)
		.success(function(data) {
			$('#cbTpVeiculo option').remove();
			$.each(data, function(i, t) {
				if($.trim(t.codigo) != $.trim($('#cbTpVeiculo').val()))
					$('#cbTpVeiculo').append('<option value="'+t.codigo+'">'+t.descricao+'</option>'); 
			});	
		})
		.fail(function(data){
			if(configApp.isSessionInvalid(data.responseText) == true) 
				window.location.reload();
			if(data.status == 404) 
				alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
			else{
				   alertify.error("Erro ao buscar tipo de Veiculo");
			} 
		});
	}
	else{
		alertify.alert("Informações insuficientes para procurar o tipo de veículo");
	}
};


var populaCboxTpEnd = function(id){ 
	$('#' + id + 'TpEnd option').remove();
	var cnpj = $.trim($('#' + id + 'Cnpj').val());
	cliendService.getListaPorCnpj(cnpj).success(function(data) {
		var option = '<option></option>';
		if(id == 'remet')
			remetTpEnd = data;
		else
			destTpEnd = data;
		$.each(data, function(i, e) {
			if(e.tipo != $('#' + id + 'Tipo').val())
			option += '<option value="'+ common.replace(e.tipo, '-','') + '"> ' 
			                           + common.replace(e.tipo, '-','') + ' - ' 
			                           + common.replace(e.tipoDescricao, '-','') + ' </option>';
			else
				option += '<option value="'+ common.replace(e.tipo, '-','') + '" selected> ' 
                + common.replace(e.tipo, '-','') + ' - ' 
                + common.replace(e.tipoDescricao, '-','') + ' </option>';
		});
		$('#' + id + 'TpEnd').append(option);
	}).fail(function(data) {
		if(configApp.isSessionInvalid(data.responseText) == true) 
			window.location.reload();
		if(data.status == 404) 
			alertify.alert('404 - A coleta não tem Remetente e/ou Destinatário');
		else 	
			alertify.alert(data.responseText);
	});
};

//Habilita o modo de edição da Coleta
var editFormColeta = function(){
	// O modo de edição da coleta só funciona para coletas que estejam com os códigos de ocorrência 
	//65(Aguardando Autorização) ou Nulo
	if('S' === habilitaEdicao()){
		//Altera status dos botões
		$('#id_cancel').css('color','red');
		$('#id_cancel').css('pointer-events','auto');
		$('#atualiza_coleta').css('color','#2A68A6');
		$('#atualiza_coleta').prop('disabled',false);
		$('#id_edit').css('color','black');
		$('#id_edit').css('pointer-events','none');
		
		$('#cbArmazem').prop('disabled',false);
		populaCboxArmazem();
		
		$('#remetTpEnd').prop('disabled', false);
		populaCboxTpEnd('remet');
		$('#destTpEnd').prop('disabled',false);
		populaCboxTpEnd('dest');
		
		$('#cbTpCarga').prop('disabled',false);
		populaCboxTpCarga();
		$('#cbTpVeiculo').prop('disabled',false);
		populaCboxTpVeiculo();
		
		$('#id_add_parceiro').prop('disabled', false);
		//$('#id_btn_parceiros').css('pointer-events','auto');
		
		$('#id_add_linha').css('pointer-events','auto');
		$('#id_add_linha').css('color','#2A68A6');
		
		$('.editItem').each(
				function() {
					$(this).css('pointer-events','auto');
					$(this).css('color','green');
				});
		
		$('.deleteItem').each(
				function() {
					$(this).css('pointer-events','auto');
					$(this).css('color','red');
				});
		
		$('#taUsuObs').prop('disabled',false);
		
		//Somente pode ser alterado o Remetente em coletas de importação
		if($('#flagImportacao').val() == 'S'){
			$('#buscaCnpj').css('color','#2A68A6');
			$('#buscaCnpj').css('pointer-events','auto');
		}
	}
	else{
		alertify.log('Esta Coleta não pode ser editada!');
	}
};


//Quando o usuário escolhe um Tp de Endereço novo para o Remente, 
//o sistema carrega as informações desse tipo de Endereço e recarrega o ComboBox do Armazem 
$('#remetTpEnd').change(function(){
	var tipo = $('#remetTpEnd').val();
	$.each(remetTpEnd, function(i, e) {
		if(tipo == e.tipo){
			$('#tfEndRem').val(e.endereco);
			$('#tfCompRem').val(e.bairro);
			$('#tfCepRem').val(e.cep);
			$('#tfMunicipioRem').val(e.cidade);
			$('#tfUfRem').val(e.estado);
		}
	});
	$('#cbArmazem option').remove();
	populaCboxArmazem();
	$('#cbArmazem').append('<option></option>');
	$('#cbArmazem').val('');
	$('#remetTipo').val($('#remetTpEnd').val());
});

$('#cbTpCarga').change(function(){
	populaCboxTpVeiculo();
});

$('#destTpEnd').change(function(){
	var tipo = $('#destTpEnd').val();
	$.each(destTpEnd, function(i, e) {
		if(tipo == e.tipo){
			$('#tfEndDest').val(e.endereco);
			$('#tfCompDest').val(e.bairro);
			$('#tfCepDest').val(e.cep);
			$('#tfMunicipioDest').val(e.cidade);
			$('#tfUfDest').val(e.estado);
		}
	});
	$('#destTipo').val($('#destTpEnd').val());
});

$('#cbArmazem').change(function(){
	$('#armazemCodigo').val($('#cbArmazem').val());
});

//Essa função funciona de maneira parecida ao lpad do Sql
var pad = function(str, max) {
	  return str.length < max ? pad("0" + str, max) : str;
	}

//Prepara campos da coleta para efetuar o Submit do Form.
$('#atualiza_coleta').click(function(){
//	if($('#armazemCodigo').val() == null || $('#armazemCodigo').val() == undefined || $('#armazemCodigo').val() == ''){
//		alertify.log('Favor selecionar um armazém.');		
//	}
//	else{
		$('#remetCnpj').val($.trim($('#remetCnpj').val()));
		$('#destCnpj').val($.trim($('#destCnpj').val()));
		$('#observacao').val($('#taColetaObs').val() + $('#taUsuObs').val());
		$('#tfTelefone').val($('#tfTelefone').val().replace('/','-'));
		$('#tfTelefone').val(pad($('#tfTelefone').val(),10));
	    //alert($('#cbTpCarga').val());
		$('atualizaColeta').submit();
//	}
});

var showModalParceiros = function(nCompra, ciclo){
	$('#id_table_parceiros tr').remove();
	ciclo = pad(ciclo, 3);
	this.parceiroService.getParceiroByColeta(nCompra, ciclo).success(function(data){
		parceiros = data;
		$.each(data, function(i,p){
			var chave = "'" +p.clienteEnd.cliente.cnpj.trim() + p.clienteEnd.tipo.trim() + "'";
			var tpParceiro = "'" + p.tipoParceiro.codigo + "'";
			var _body =
				'<tr id="'+ p.clienteEnd.cliente.cnpj.trim() + p.tipoParceiro.codigo +'"onClick="carregaInfoParceiro('+ chave +',' + tpParceiro+')" style="cursor:pointer;">'
					+'<td>'+ nCompra +'</td>'
					+'<td>'+ ciclo +'</td>'
					+'<td>'+ p.clienteEnd.cliente.cnpj +'</td>'
					+'<td>'+ p.clienteEnd.tipo +'</td>'
					+'<td>'+ p.tipoParceiro.codigo +'</td>'
					+'<td>'+ p.tipoParceiro.descricao +'</td>'
					+'<td><a '+ ((p.tipoParceiro.codigo != 'CD' && p.tipoParceiro.codigo != 'CR' && p.tipoParceiro.codigo != 'CS' && $('#id_add_parceiro').prop('disabled') != true) ? '' : 'style="pointer-events: none;color:black;"') 
					+ ' data-toggle="tooltip" data-placement="right" data-original-title="Deletar Parceiro" '
					+'href="javascript:deleteParceiro('+ tpParceiro +')" ><i class="glyphicon glyphicon-minus"></i></a></td>'
				+'</tr>';
			$('#id_table_parceiros').append(_body);
		});
	})
	.fail(function(data){
		if(configApp.isSessionInvalid(data.responseText) == true) 
			window.location.reload();
		if(data.status == 404) 
			alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
		else{
			alertify.error('Erro ao buscar parceiros!');
		} 
	});

	$('#myModalParceiros').modal('show'); 
};

var hideModalParceiros = function(){
	$('#id_razaoSocial_parceiros').val('');
	$('#id_tpEnd_parceiros').val('');
	$('#id_cidade_parceiros').val('');
	$('#id_uf_parceiros').val('');
	$('#id_tpEnd_parceiros').val('');
	$('#id_cep_parceiros').val('');
	$('#id_endereco_parceiros').val('');
	$('#myModalParceiros').modal('hide');
};

var carregaInfoParceiro = function(chave, tpParceiro){
	$('#id_table_parceiros tr').css('background-color','white');
	$('#id_table_parceiros tr').css('color','black');
	var row = '#'+chave.substr(0,chave.length-1) + tpParceiro;
	$(row).css('background-color','#F49B17');
	$(row).css('color','white');
	$.each(parceiros, function(i,p){
		if((p.clienteEnd.cliente.cnpj.trim() + p.clienteEnd.tipo.trim()) == (chave)){
			$('#id_razaoSocial_parceiros').val(p.clienteEnd.cliente.razaoSocial);
			$('#id_tpEnd_parceiros').val(p.clienteEnd.tipo);
			$('#id_cidade_parceiros').val(p.clienteEnd.cidade);
			$('#id_uf_parceiros').val(p.clienteEnd.estado);
			$('#id_tpEnd_parceiros').val(p.clienteEnd.tipo);
			$('#id_cep_parceiros').val(p.clienteEnd.cep);
			$('#id_endereco_parceiros').val(p.clienteEnd.endereco);
		}
	});
};

$('#id_add_parceiro').click(function(){
	$('#id_table_parc_tp_end tr').remove();
	$('#tpEndTable').prop('hidden', true);
	$('#id_table_pesq_cnpj_parc tr').remove();
	$('#campoPesquisaParceiro').val('');
	$('#cbTpParceiro').val('');
	$('#myModalAddParceiro').modal('show');
	hideModalParceiros();
});

var procuraCliente = function(){
	if($('#campoPesquisaParceiro').val() != null && $('#campoPesquisaParceiro').val() != undefined && $('#campoPesquisaParceiro').val() != ''){
		$('#id_table_pesq_cnpj_parc tr').remove();
		if($("input[name='tpPesquisaParceiro']:checked").val() == 'cpf'){
			var cnpj = limpaCnpj($('#campoPesquisaParceiro').val());
			clienteService.getPorCnpj(cnpj).success(function(data) {
				if(nvl(data,0) != 0){
					//if(data.length > 0){
					    var cnpj = "'" + data.cnpj + "'"; 
						var _body =
							'<tr id="'+data.cnpj.trim()+'" onClick="viewTpEnd('+ cnpj +')" style="cursor:pointer;">'
								+'<td>'+ data.cnpj +'</td>'
								+'<td>'+ data.razaoSocial +'</td>'
							+'</tr>';
						$('#id_table_pesq_cnpj_parc').append(_body);
				     //}
				}
				else{
					alertify.log('Cnpj informado não foi encontrado na base de dados!');
				}
			})
			.fail(function(data){
				if(configApp.isSessionInvalid(data.responseText) == true) 
					window.location.reload();
				if(data.status == 404) 
					alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
				else{
					alert("Ocorreu um erro ao procurar o CNPJ, favor verificar se o mesmo está correto!")
				}
			});
		}
		else{
			clienteService.getListaPorRazaoSocial($('#campoPesquisaParceiro').val()).success(function(cliente) {
				if(nvl(cliente,0) != 0){
					if(cliente.length > 0){
						$.each(cliente, function(i,p){
						var cnpj = "'" + p.cnpj.trim() + "'";
						var _body =
							'<tr id="'+ p.cnpj.trim() +'" onClick="viewTpEnd('+ cnpj +')" style="cursor:pointer;">'
								+'<td>'+ p.cnpj +'</td>'
								+'<td>'+ p.razaoSocial +'</td>'
							+'</tr>';
						$('#id_table_pesq_cnpj_parc').append(_body);
						});
					}
				}
				else{
					alertify.log('Não foi encontrado nenhum Cliente contendo essa razão Social!');
				}
			})
			.fail(function(data){
				if(configApp.isSessionInvalid(data.responseText) == true) 
					window.location.reload();
				if(data.status == 404) 
					alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
				else{
					alertify.error("Ocorreu um erro ao procurar o Cliente pela a Razão social, favor verificar se a mesma está correta!");
				}
			});
		}
	}else{
		alertify.error("Favor preencher o campo de pesquisa.");
	}
};
$('#id_modal_pesq_parceiro').click(function(){
	procuraCliente();
});

//Modal Cnpj Remetente
var procuraClienteRemetente = function(){
	if($('#campoPesquisaRemetente').val() != null && $('#campoPesquisaRemetente').val() != undefined && $('#campoPesquisaRemetente').val() != ''){
		$('#id_table_pesq_cnpj_remet tr').remove();
		if($("input[name='tpPesquisaRemetente']:checked").val() == 'cpf'){
			var cnpj = limpaCnpj($('#campoPesquisaRemetente').val());
			clienteService.getPorCnpj(cnpj).success(function(cliente) {
				   var cnpj = "'" + cliente.cnpj.trim() + "'";
				   var razao = "'" + cliente.razaoSocial + "'";
				   var ie = "'" + cliente.ie + "'";
				   var _body =
						'<tr id="'+cliente.cnpj.trim()+'" onClick="selRemetente('+ cnpj +','+ razao +','+ ie +')" style="cursor:pointer;">'
							+'<td>'+ cliente.cnpj +'</td>'
							+'<td>'+ cliente.razaoSocial +'</td>'
						+'</tr>';
					$('#id_table_pesq_cnpj_remet').append(_body);
			})
			.fail(function(data){
				if(configApp.isSessionInvalid(data.responseText) == true) 
					window.location.reload();
				if(data.status == 404) 
					alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
				else{
					alert("Ocorreu um erro ao procurar o CNPJ, favor verificar se o mesmo está correto!")
				}
			});
		}
		else{
			clienteService.getListaPorRazaoSocial($('#campoPesquisaRemetente').val()).success(function(cliente) {
				$.each(cliente, function(i,p){
				var cnpj = p.cnpj.trim();
				var cnpjR = "'" + p.cnpj.trim() + "'";
				var razao = "'" + p.razaoSocial + "'";
				var ie = "'" + p.ie + "'";
				var _body =
					'<tr id="'+ cnpj +'" onClick="selRemetente('+cnpjR +','+razao+','+ ie +')" style="cursor:pointer;">'
						+'<td>'+ p.cnpj +'</td>'
						+'<td>'+ p.razaoSocial +'</td>'
					+'</tr>';
				$('#id_table_pesq_cnpj_remet').append(_body);
				});
			})
			.fail(function(data){
				if(configApp.isSessionInvalid(data.responseText) == true) 
					window.location.reload();
				if(data.status == 404) 
					alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
				else{
					alertify.error("Ocorreu um erro ao procurar o CNPJ, favor verificar se o mesmo está correto!");
				}
			});
		}
	}else{
		alertify.error("Favor preencher o campo de pesquisa.");
	}
};
$('#id_modal_pesq_remetente').click(function(){
	procuraClienteRemetente();
});

var cnpjRemetente = '';
var razaoRemetente = '';
var ieRemetente = '';
var selRemetente = function (cnpj, razao, ie){
	$('#id_table_pesq_cnpj_remet tr').css('background-color','white');
	$('#id_table_pesq_cnpj_remet tr').css('color','black');
	var row = '#'+cnpj;
	$(row).css('background-color','#F49B17');
	$(row).css('color','white');
	cnpjRemetente = cnpj;
	razaoRemetente = razao;
	ieRemetente = ie;
	//$('#remetTpEnd').val('');
};

$('#id_sel_remetente').click(function(){
	if(cnpjRemetente != '' && razaoRemetente != ''){
		$('#remetCnpj').val(cnpjRemetente);
		$('#remetNome').val(razaoRemetente);
		
		$('#tfEndRem').val('');
		$('#tfCompRem').val('');
		$('#tfCepRem').val('');
		$('#tfMunicipioRem').val('');
		$('#tfUfRem').val('');
		$('#cbArmazem option').remove();
		populaCboxArmazem();
		$('#cbArmazem').val('');
		populaCboxTpEnd('remet');
		$('#remetTipo').val('');
		$('#remetTpEnd').val('');
		$('#tfIeRem').val(ieRemetente);
		hideModalCnpjRemetente();
	}
});

var showModalCnpjRemetente = function(){
	$('#myModalCnpjRemetente').modal('show');
};
var hideModalCnpjRemetente = function(){
	$('#myModalCnpjRemetente').modal('hide');
};

var viewTpEnd = function(cnpj){
	$('#id_table_pesq_cnpj_parc tr').css('background-color','white');
	$('#id_table_pesq_cnpj_parc tr').css('color','black');
	this.cliendService.getListaPorCnpj(cnpj).success(function(tpEnd){
		$('#tpEndTable').prop('hidden', false);
		$('#id_table_parc_tp_end tr').remove();
		$.each(tpEnd, function(i,p){
			var _body =
				'<tr>'
					+'<td>'+ p.cliente.cnpj +'</td>'
					+'<td>'+ p.tipo +'</td>'
					+'<td>'+ p.cidade +'</td>'
					+'<td>'+ p.cep +'</td>'
					+'<td>'+ p.endereco +'</td>'
					+'<td>'+ p.bairro  +'</td>'
					+'<td><input type="radio" name="tpEndParceiro" value="'+ p.cliente.cnpj.trim() + p.tipo+'"></td>'
				+'</tr>';
			$('#id_table_parc_tp_end').append(_body);
		});
	})
	.fail(function(data){
		if(configApp.isSessionInvalid(data.responseText) == true) 
			window.location.reload();
		if(data.status == 404) 
			alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
		else{
			alert("Ocorreu um erro ao executar a buscas dos tipos de endereço");
		}
	});
	var row = "#" + cnpj;
	row = row.trim();
	$(row).css('background-color','#F49B17');
	$(row).css('color','white');
};


var hideModalAddParceiros = function(){
	$('#myModalAddParceiro').modal('hide');
	$('#id_table_parc_tp_end tr').remove();
	$('#tpEndTable').prop('hidden', true);
	$('#id_table_pesq_cnpj_parc tr').remove();
	$('#campoPesquisaParceiro').val('');
	$('#cbTpParceiro').val('');
	//$('#campoPesquisaParceiro').prop('disabled',true);
	//$('#id_modal_pesq_parceiro').prop('disabled',true);
};

$('#id_seleciona_parceiro').click(function(){	
	var selTpEnd = $('input[name="tpEndParceiro"]:checked').val();
	var selTpPar = $('#cbTpParceiro').val();
	if('S' === habilitaEdicao()){
		if(selTpEnd != null && selTpEnd != undefined && selTpEnd != '' && selTpPar != null && selTpPar != undefined && selTpPar != '' ){
			var cnpj     = selTpEnd.substr(0,selTpEnd.length -1);
			var tipoEnd  = selTpEnd.substr(selTpEnd.length -1, selTpEnd.length -2);
			var tipoParc = $('#cbTpParceiro').val();
			var coleta   = $('#tfNumColeta').val();
			var ciclo    = $('#tfCiclo').val();
			parceiroService.postParceiroColeta(coleta, cnpj, tipoEnd, tipoParc, ciclo).success(function(data){
				alertify.log(data);
				hideModalAddParceiros();
				showModalParceiros(coleta, ciclo);
			})
			.fail(function(data){
				if(configApp.isSessionInvalid(data.responseText) == true) 
					window.location.reload();
				if(data.status == 404) 
					alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
				else{
					alertify.error(data + data.status);
				}
			});
		}
		else{
			alertify.error("Favor selecionar um tipo de endereço e/ou um tipo de parceiro para o cliente escolhido ")
		}
	}
});

$('#tpPesquisaParceiroC').click(function(){
	$('#campo input').remove();
	var input = '<input type="text" id="campoPesquisaParceiro" class="form-control"'+ 
		'onkeyup="this.value = new Mask().cnpj(this.value);" maxlength="18">';
	$('#campo').append(input);
	$('#id_modal_pesq_parceiro').prop('disabled',false);
	$('#id_table_parc_tp_end tr').remove();
	$('#tpEndTable').prop('hidden', true);
	$('#id_table_pesq_cnpj_parc tr').remove();
});

$('#tpPesquisaParceiroR').click(function(){
	$('#campo input').remove();
	var input = '<input type="text" id="campoPesquisaParceiro" class="form-control">';
	$('#campo').append(input);
	$('#id_modal_pesq_parceiro').prop('disabled',false);
	$('#id_table_parc_tp_end tr').remove();
	$('#tpEndTable').prop('hidden', true);
	$('#id_table_pesq_cnpj_parc tr').remove();
});

//Modal Cnpj Remetente
$('#tpPesquisaRemetenteR').click(function(){
	$('#campoRemetente input').remove();
	var input = '<input type="text" id="campoPesquisaRemetente" class="form-control">';
	$('#campoRemetente').append(input);
	$('#id_modal_pesq_remetente').prop('disabled',false);
	$('#id_table_pesq_cnpj_remet tr').remove();
});

$('#tpPesquisaRemetenteC').click(function(){
	$('#campoRemetente input').remove();
	var input = '<input type="text" id="campoPesquisaRemetente" class="form-control"'+ 
		'onkeyup="this.value = new Mask().cnpj(this.value);" maxlength="18">';
	$('#campoRemetente').append(input);
	$('#id_modal_pesq_remetente').prop('disabled',false);
	$('#id_table_pesq_cnpj_remet tr').remove();
});

var deleteParceiro = function(tpParceiro){
	var coleta   = $('#tfNumColeta').val();
	var ciclo    = $('#tfCiclo').val();
	if('S' === habilitaEdicao()){
		parceiroService.deleteParceiro(coleta, ciclo, tpParceiro).success(function(data){
			alertify.log(data);
			hideModalParceiros();
		})
		.fail(function(data){
			if(configApp.isSessionInvalid(data.responseText) == true) 
				window.location.reload();
			if(data.status == 404) 
				alertify.alert('404 - Rota não encontrada ao buscar lista de endereço');
			else{
				alertify.error(data + data.status);
			}
		});
	}
};

$('#campoRemetente').keypress(function(key){
	if(key.which == 13){
		procuraClienteRemetente();
	}
});

$('#campo').keypress(function(key){
	if(key.which == 13){
		procuraCliente();
	}
});
/*------------------------------------------------------------------------------------*/
