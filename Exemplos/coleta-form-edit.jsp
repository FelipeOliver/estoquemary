<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <link href="${pageContext.request.contextPath}/static/tdv/img/favicon.png" rel="shortcut icon" >

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/static/tdv/css/common.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/static/bootstrap/css/jquery-ui.css" rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/static/bootstrap/css/sticky-footer-navbar.css" rel="stylesheet">
	
	<!-- alert customizado -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/alertify.js-0.3.11/themes/alertify.core.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/alertify.js-0.3.11/themes/alertify.default.css" id="toggleCSS" />
	
	<script src="${pageContext.request.contextPath}/static/bootstrap/alertify.js-0.3.11/lib/alertify.min.js"></script>
	<script type="text/javascript">
		function reset (labelOK) {
			//console.log(labelOK);
			if(labelOK == undefined) labelOK = "OK";
			//$("#toggleCSS").attr("href", "${pageContext.request.contextPath}/static/bootstrap/alertify.js-0.3.11/themes/alertify.default.css");
			alertify.set({
				labels : {
					ok     : labelOK,
					cancel : "Cancelar"
				},
				delay : 10000,
				buttonReverse : false,
				buttonFocus   : "cancel"
			});
		}
	</script>
	<!-- fim Alert customizado  -->
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/ie-emulation-modes-warning.js"></script>

	<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/static/jquery.min.1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/window-common.js" type="text/javascript" ></script>	
	<script src="${pageContext.request.contextPath}/static/bootstrap/js/jquery-ui.js"></script>
	<script>
		$(function() {
			$( ".datepicker" ).datepicker({dateFormat: 'dd/mm/yy'});
			$.ajaxSetup({ cache: false });
		});
	</script>		
	


<!-- start: PAGE CONTENT -->
	  <div class="miniTdv">	
		
		<div class="col-lg-12">

			<form name="atualizaColeta" action="/coleta/post/edit"  method="post" accept-charset="UTF-8">
				<!-- Cabeçalho-->
				<div class="miniTdv panel panel-default row">
					<div class="miniTdv row">
						<label class="miniTdv label label-default">Coleta</label>
						<c:if test="${empty  modal}">
							<a id="id_edit"href="javascript:editFormColeta();" class="miniTdv btn btn-link btn-sm" ><i class="miniTdv glyphicon glyphicon-pencil"></i></a>
							<button id="atualiza_coleta"  class="miniTdv btn btn-link btn-sm" disabled><i class="miniTdv glyphicon glyphicon-ok"></i></button>
							<a id="id_cancel" href="javascript:location.reload();" class="desativaAncora miniTdv btn btn-link btn-sm"><i class="miniTdv glyphicon glyphicon-remove"></i></a>
						</c:if>
<!--   						<label class="miniTdv label label-default" style="margin-left:15px;">Solicitada por:</label> -->
<%-- 						<label class="text-danger" style="font-size:11px" > ${coleta.contato.nome} (${coleta.contato.login})</label> --%>
						<c:if test="${not empty coleta.ocorrencia.codigo}">
							<label class="ocor text-danger pull-right">Ocorrência: ${coleta.ocorrencia.codigo } - ${coleta.ocorrencia.descricao }</label>
						</c:if>
					</div>
					<br>
					<div class="miniTdv row">
						<div class="miniTdv col-sm-10">
							<div class="miniTdv row">
								<div class="miniTdv col-sm-1">
									<label  >Número</label>
								</div>
								<div class="miniTdv col-sm-2">
									<div class="miniTdv col-sm-3">
										<input id="tfCiclo" type="text" name="ciclo" value="${coleta.ciclo}" class="miniTdv form-control" readonly />
									</div>
									<div class="miniTdv col-sm-9">
										<input id="tfNumColeta" type="text" name="nCompra" value="${coleta.nCompra}" class="miniTdv form-control" readonly />
									</div>
								</div>
								<div class="miniTdv text-right col-sm-2">
									<label  >Armazém</label>
								</div>
								<div class="miniTdv col-sm-4">
									<select id="cbArmazem" size="1" name="cbArmazem" class="miniTdv form-control" disabled required>
										<option selected value="${coleta.armazem.codigo}">${coleta.armazem.codigo} - ${coleta.armazem.descricao}</option>
									</select>
									<input id="armazemCodigo" type="hidden" name="armazem.codigo" value="${coleta.armazem.codigo}">
								</div>
								<div class="miniTdv text-right col-sm-1">
									<label>Pedido</label>
								</div>
								<div class="miniTdv col-sm-2">
									<input id="tfPedido" type="text" name="pedido" value="${coleta.pedido}" class="miniTdv form-control" readonly />
								</div>
							</div>
							
							<div class="miniTdv row">
									<div class="miniTdv col-sm-1">
										<label>Solicita.</label>
									</div>
									<div class="miniTdv col-sm-2">
										<input type="text" value="${coleta.dataSolicitacao}" class="miniTdv form-control" readonly />
									</div>
									<div class="miniTdv text-right col-sm-2">
										<label data-toggle="tooltip" data-placement="left" data-original-title="Data/Hora da Programação" >Programação</label><!-- .:/ Hora -->
									</div>
									<div class="miniTdv col-sm-2">
										<input type="text" name="txtDataProgramacao" value="${coleta.dataProgramacao} ${coleta.horaProgramacao}:00" class="miniTdv form-control" readonly />
										<input name="dataProgramacao" type="hidden" value="${coleta.dataProgramacao}" />
										<input name="horaProgramacao" value="${coleta.horaProgramacao}" type="hidden"/>
									</div>
									<div class="miniTdv text-right col-sm-2">
										<label data-toggle="tooltip" data-placement="left" data-original-title="Data/Hora da Autorização">
											Aut. Dt/Hora
										</label>
									</div>
									<div class="miniTdv col-sm-3">
										<input type="text" value="${coleta.dataAutorizacao}" class="miniTdv form-control" readonly />
									</div>
							</div>
							
							<div class="miniTdv row">
									<div class="miniTdv col-sm-1">	
										<label  >CFOP</label>
									</div>
									<div class="miniTdv col-sm-2">
										<input type="text" value="${coleta.cfop}" class="miniTdv form-control" readonly />
									</div>	
									<div class="miniTdv text-right col-sm-2">
										<label>Centro de Custo</label>
									</div>
									<div class="miniTdv col-sm-7">
										<input type="text" value="${coleta.centroCusto}" class="miniTdv form-control" readonly />
									</div>
										<!-- <input id="tfDescCfop" type="text" value="" class="miniTdv form-control" readonly /> -->
							</div>
						</div>	
						<div class="miniTdv col-sm-2">
							<div class="miniTdv col-sm-4"></div>
							<a id="id_btn_parceiros" href="javascript:showModalParceiros('${coleta.nCompra}', '${coleta.ciclo}');" class="miniTdv btn btn-default"><i class="miniTdv glyphicon glyphicon-user"></i><br>Parceiros</a>
						</div>
					</div>
			<!-- 		<div class="miniTdv row">
							<div class="miniTdv col-sm-2">
								<label>Centro de Custo</label>
							</div>
							<div class="miniTdv col-sm-4">
								<input type="text" value="${coleta.centroCusto}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-6">
								<input id="tfDescCC"type="text" value="" class="miniTdv form-control" readonly />			
							</div>
					</div> -->
				</div>			
				<!-- Remetente-->
				<div class="miniTdv panel panel-default row">
					<label  class="miniTdv label label-default">Remetente</label>
					<br><br>
					<div class="miniTdv row">
							<div class="miniTdv col-sm-1">
								<label>CNPJ</label>
							</div>
							<div class="miniTdv col-sm-2">
								<div class="miniTdv col-sm-11">	
									<input id="remetCnpj" type="text" name="clienteEndColeta.cliente.cnpj" value="${coleta.clienteEndColeta.cliente.cnpj}" class="miniTdv form-control" readonly />
								</div>
								<div class="miniTdv col-sm-1"> 
									<a class="desativaAncora miniTdv btn btn-xs" href="javascript:showModalCnpjRemetente();" id="buscaCnpj"><i class="miniTdv glyphicon glyphicon-search"></i></a>
								</div>
							</div>
							<div class="miniTdv col-sm-6">
								<div class="miniTdv text-right col-sm-2">
									<label  >Nome</label>
								</div>
								<div class="miniTdv col-sm-10">
									<input id="remetNome" type="text" name="clienteEndColeta.cliente.razaoSocial" value="${coleta.clienteEndColeta.cliente.razaoSocial}" class="miniTdv form-control" readonly />
								</div>
								<input  id="grupoEconomicoRemetente" name="clienteEndColeta.cliente.grupoEconomico.codigo" value="${coleta.clienteEndColeta.cliente.grupoEconomico.codigo}" type="hidden"/>
							</div>
							<div class="miniTdv col-sm-3">
								<div class="miniTdv text-right col-sm-3">
									<label>Tipo End</label>
								</div>
								<div class="miniTdv col-sm-9">
									<select id="remetTpEnd" size="1" name="cbClienteEndColetaTipo" class="miniTdv form-control" disabled required>
										<option selected value="${coleta.clienteEndColeta.tipo}">${coleta.clienteEndColeta.tipo} - ${coleta.clienteEndColeta.tipoDescricao }</option>
									</select>
									<input id="remetTipo" name="clienteEndColeta.tipo" value="${coleta.clienteEndColeta.tipo}" type="hidden">
								</div>	
							</div>
					</div>
					<div class="miniTdv row">	
							<div class="miniTdv col-sm-1">
								<label  >Endereço</label>
							</div>
							<div class="miniTdv col-sm-4">
								<input id="tfEndRem"type="text" name="clienteEndColeta.endereco" value="${coleta.clienteEndColeta.endereco}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv text-right col-sm-2">
								<label  class="text-right">Complemento</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfCompRem"type="text" name="clienteEndColeta.complemento" value="${coleta.clienteEndColeta.complemento}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<div class="miniTdv col-sm-8"></div>
								<label  >CEP</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfCepRem"type="text" name="clienteEndColeta.cep" value="${coleta.clienteEndColeta.cep }" class="miniTdv form-control" readonly />
							</div>
					</div>	
					<div class="miniTdv row">				
							<div class="miniTdv col-sm-1">
								<label  >Município</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfMunicipioRem"type="text" name="clienteEndColeta.cidade" value="${coleta.clienteEndColeta.cidade }" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-2">
								<div class="miniTdv text-right col-sm-6">
									<label >UF</label>
								</div>
								<div class="miniTdv col-sm-6">
									<input id="tfUfRem" type="text" name="clienteEndColeta.estado" value="${coleta.clienteEndColeta.estado }" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-3">
								<div class="miniTdv text-right col-sm-2">
									<label  >ALX</label>
								</div>
								<div class="miniTdv col-sm-10">
									<input id="tfAlxRem"type="text" value="${coleta.clienteEndColeta.cliente.almoxarifado}" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-4">	
								<div class="miniTdv text-right col-sm-1">
									<label  >IE</label>
								</div>
								<div class="miniTdv col-sm-5">
									<input id="tfIeRem" type="text" value="${coleta.clienteEndColeta.ie}" class="miniTdv form-control" readonly />
								</div>
								<div class="miniTdv text-right col-sm-4">
									<label  >Grupo Econômico</label>
								</div>
								<div class="miniTdv col-sm-2">
									<input id="tfIeRem" type="text" value="${coleta.clienteEndColeta.cliente.grupoEconomico.codigo}" class="miniTdv form-control" readonly />
								</div>
							</div>
					</div>
				</div>			
				<!-- Destinatário-->
				<div class="miniTdv panel panel-default row">
					<label  class="miniTdv label label-default">Destinatário</label>
					<br><br>
					<div class="miniTdv row">
							<div class="miniTdv col-sm-1">
								<label  >CNPJ</label>
							</div>
							<div class="miniTdv col-sm-2">
								<div class="miniTdv col-sm-11">	
									<input id="destCnpj" name="clienteEndEntrega.cliente.cnpj" type="text" value="${coleta.clienteEndEntrega.cliente.cnpj}" class="miniTdv form-control" readonly />
								</div>
								<div class="miniTdv col-sm-1"> 
									<!-- <a href="javascript:modalCnpj('D');" hidden class="miniTdv buscaCnpj"><i class="miniTdv glyphicon glyphicon-search"></i></a> -->
								</div>
							</div>
							<div class="miniTdv col-sm-6">
								<div class="miniTdv text-right col-sm-2">
									<label  >Nome</label>
								</div>
								<div class="miniTdv col-sm-10">
 									<input id="destNome" type="text" name="clienteEndEntrega.cliente.razaoSocial" value="${ coleta.clienteEndEntrega.cliente.razaoSocial}" class="miniTdv form-control" readonly />
								</div>
								<input id="grupoEconomicoDestinatario" name="clienteEndEntrega.cliente.grupoEconomico.codigo" value="${coleta.clienteEndEntrega.cliente.grupoEconomico.codigo}" type="hidden"/>
							</div>
							<div class="miniTdv col-sm-3">
								<div class="miniTdv text-right col-sm-3">
									<label  >Tipo End</label>
								</div>
								<div class="miniTdv col-sm-9">
									<select id="destTpEnd" size="1" name="clienteEndEntregaTipo" class="miniTdv form-control" disabled required>
										<option selected  value="${coleta.clienteEndEntrega.tipo}">${coleta.clienteEndEntrega.tipo} - ${coleta.clienteEndEntrega.tipoDescricao}</option>
									</select>
									<input id="destTipo" name="clienteEndEntrega.tipo" value="${coleta.clienteEndEntrega.tipo}" type="hidden">
								</div>	
							</div>
					</div>
					<div class="miniTdv row">	
							<div class="miniTdv col-sm-1">
								<label  >Endereço</label>
							</div>
							<div class="miniTdv col-sm-4">
								<input id="tfEndDest" type="text" name="clienteEndEntrega.endereco" value="${coleta.clienteEndEntrega.endereco}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv text-right col-sm-2">
								<label>Complemento</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfCompDest" type="text" name="clienteEndEntrega.complemento" value="${coleta.clienteEndEntrega.complemento }" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv text-right col-sm-1">
								<label>CEP</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfCepDest" type="text" name="clienteEndEntrega.cep" value="${coleta.clienteEndEntrega.cep}" class="miniTdv form-control" readonly />
							</div>
					</div>	
					<div class="miniTdv row">				
							<div class="miniTdv col-sm-1">
								<label  >Município</label>
							</div>
							<div class="miniTdv col-sm-2">
								<input id="tfMunicipioDest" type="text" name="clienteEndEntrega.cidade" value="${coleta.clienteEndEntrega.cidade}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-2">
								<div class="miniTdv text-right col-sm-6">
									<label>UF</label>
								</div>
								<div class="miniTdv col-sm-6">
									<input id="tfUfDest" type="text" name="clienteEndEntrega.estado" value="${coleta.clienteEndEntrega.estado}" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-3">
								<div class="miniTdv text-right col-sm-2">
									<label  >ALX</label>
								</div>
								<div class="miniTdv col-sm-10">
									<input id="tfAlxDest" type="text" value="${coleta.clienteEndEntrega.cliente.almoxarifado}" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-4">	
								<div class="miniTdv text-right col-sm-1">
									<label>IE</label>
								</div>
								<div class="miniTdv col-sm-5">
									<input id="tfIeDest" type="text" value="${coleta.clienteEndEntrega.ie}" class="miniTdv form-control" readonly />
								</div>
								<div class="miniTdv text-right col-sm-4">
									<label  >Grupo Econômico</label>
								</div>
								<div class="miniTdv col-sm-2">
									<input id="tfIeRem" type="text" value="${coleta.clienteEndEntrega.cliente.grupoEconomico.codigo}" class="miniTdv form-control" readonly />
								</div>
							</div>
					</div>
				</div>
				<!-- Dados Gerais -->
				<div class="panel panel-default row">
					<!-- Dados Gerais - Lado esquerdo -->
					<div class="miniTdv col-sm-6">
							<label class="miniTdv label label-default" >Dados Gerais</label>
							<label class="origem text-danger">Origem: ${coleta.origem.descricaoMenor}</label>
							<br><br>
							<!-- RadioBox -->
							<div class="miniTdv row">
								<div class="miniTdv col-sm-3">
									<label >Modalidade</label>
									<div class="miniTdv radio">
										<label><input name="rModalidade" type="radio" value="C" ${coleta.modalidade=='C'?'checked':''} disabled /> Coletar</label>
									</div>
									<div class="miniTdv radio">
										<label><input name="rModalidade" type="radio" value="E" ${coleta.modalidade=='E'?'checked':''} disabled /> Entregar</label>
									</div>
									<input name="modalidade" value="${coleta.modalidade}" type="hidden">
								</div>
								<div class="miniTdv col-sm-3">
									<label >Prioridade</label><br>
									<div class="miniTdv radio">
										<label><input name="rColeta" type="radio" value="N" ${(coleta.tpColeta=='C'||coleta.tpColeta=='N')?'checked':''} disabled /> Normal</label>								</div>
									<div class="miniTdv radio">		
										<label><input name="rColeta" type="radio" value="E" ${coleta.tpColeta=='E'?'checked':''} disabled /> Expressa</label>
									</div>
									<input id="tpColeta" name="tpColeta" value="${coleta.tpColeta}" type="hidden"/>
								</div>
								<div class="miniTdv col-sm-5">
									<input id="pagadorFrete" name="pagadorFrete" value="${coleta.pagadorFrete == null ? 'O': coleta.pagadorFrete}" type="hidden"/>
									<label  > Pagador Frete: ${coleta.cnpjPagadorFrete }</label><br>
									<div class="miniTdv col-sm-6">
										<div class="miniTdv radio">
											<label>
												<input name="rPagadorFrete" type="radio" value="R"  ${coleta.pagadorFrete=='R'?'checked':''} disabled /> 
												Remetente
											</label>
										</div>
										<div class="miniTdv radio">
											<label><input name="rPagadorFrete" type="radio" value="D" ${coleta.pagadorFrete=='D'?'checked':''} disabled /> Destinatário</label>
										</div>
									</div>
									<div class="miniTdv col-sm-6">
										<div class="miniTdv radio">
											<label><input name="rPagadorFrete" type="radio" value="S" ${coleta.pagadorFrete=='S'?'checked':''} disabled /> Solicitante</label>
										</div>
										<div class="miniTdv radio">
											<label><input name="rPagadorFrete" type="radio" value="O" ${coleta.pagadorFrete=='O'?'checked':''} disabled /> Outros</label>
										</div>
									</div>
								</div>
							</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-2">
								<label  >Tp. Carga</label>
							</div>
							<div class="miniTdv col-sm-5">
								<div class="miniTdv col-sm-12">
									<select id="cbTpCarga" name="tipoCarga.codigo" class="miniTdv form-control" disabled>
										<option selected value="${coleta.tipoCarga.codigo}">${coleta.tipoCarga.descricao}</option>
									</select>
									<input id="tipoCargaDescricao" name="tipoCarga.descricao" value="${coleta.tipoCarga.descricao}" type="hidden"/>
								</div>
							</div>
							<div class="miniTdv col-sm-5">
								<div class="miniTdv col-sm-5">
									<label  >Tp. Veículo</label>
								</div>
								<div class="miniTdv col-sm-7">
									<select id="cbTpVeiculo" size="1" name="tipoVeiculo.codigo" class="miniTdv form-control" disabled>
										<option selected value="${coleta.tipoVeiculo.codigo}">${coleta.tipoVeiculo.descricao}</option>
									</select>
									<input id="tipoVeiculoDescricao" name="tipoVeiculo.descricao" value="${coleta.tipoVeiculo.descricao}" type="hidden"/>
								</div>
							</div>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-2">
								<label  >Químico</label>
							</div>
							<div class="miniTdv col-sm-2">
								<div class="miniTdv col-sm-12">
										<input id="tfQuimico" type="text" value="${coleta.quimico}" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-8">
								<div class="miniTdv col-sm-3">
									<label  >Arq. Exp.</label>
								</div>
								<div class="miniTdv col-sm-9">
									<input id="tfArqExp" type="text" value="${coleta.numeroFormulario}" class="miniTdv form-control" readonly />
								</div>
							</div>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-2">
									<label   data-toggle="tooltip" data-placement="left" data-original-title="Valor da Mercadoria">Valor Merc.</label>
							</div>
							<div class="miniTdv col-sm-5">
								<div class="miniTdv col-sm-12">
									<input id="tfValMerc" type="text" name="valorMercadoria" value="${coleta.valorMercadoria}" class="miniTdv form-control" readonly />
								</div>
							</div>
							<div class="miniTdv col-sm-5">
								<div class="miniTdv col-sm-5">
									<label  data-toggle="tooltip" data-placement="left" data-original-title="Valor do seguro">Val. Seguro</label>
								</div>
								<div class="miniTdv col-sm-7">
									<input id="tfValSeg" type="text" name="valorSeguro" value="${coleta.valorSeguro}" class="miniTdv form-control" readonly />
								</div>
							</div>
						</div>
						<div class="miniTdv row">
								<div class="miniTdv col-sm-2">
									<label>Ajudantes</label>
								</div>
								<div class="miniTdv col-sm-10">
									<div class="miniTdv col-sm-12">
										<input id="tfAjudantes"type="text" value="" class="miniTdv form-control" readonly />
									</div>
								</div>
						</div>
					</div>
					
					<!-- Dados da Coleta -->
					<div class="miniTdv col-sm-3">
						<label class="miniTdv label label-default" >Dados do Solicitante</label>
						<c:if test="${coleta.flagAutorizacao == 'E' }">
							<label class="tpAutorizacao text-danger pull-right">Autorizada na efetivação  </label>							
						</c:if>
						<br>					
						<div class="miniTdv row">
							<div class="miniTdv col-sm-5">
								<label style="color:#A94442;">Solicitado por: </label>
							</div>
						</div>
						<div class="miniTdv row">
							<input id="tfContato" type="text" name="contato.nome" value="${coleta.contato.nome == null ? ' ': coleta.contato.nome}" title="${coleta.contato.nome == null ? ' ': coleta.contato.nome}" class="miniTdv form-control" readonly/>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-3">
								<label  >Login</label>
							</div>
							<div class="miniTdv col-sm-9">	
								<input id="tfOrdem" type="text" name="contato.login" value="${coleta.contato.login == null ? ' ': coleta.contato.login}" name="contato.nome" class="miniTdv form-control" readonly />
							</div>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-3">
								<label  >E-mail</label>
							</div>
							<div class="miniTdv col-sm-9">	
								<input id="tfCidade" type="text" name="contato.email" value="${coleta.contato.email}" class="miniTdv form-control" readonly />
							</div>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-3">
								<label  >Telefone</label>
							</div>
							<div class="miniTdv col-sm-9">
								<input id="tfTelefone" type="text" name="contato.telefone" value="${coleta.contato.telefone}" class="miniTdv form-control" readonly />
							</div>
							<input name="contato.cnpj" value="${coleta.contato.cnpj == null ? '00000000000000' : coleta.contato.cnpj}" type="hidden" />
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-5">
								<label style="color:#A94442;">Autorizado por:</label>
							</div>
						</div>
						<div class="miniTdv row">
							<input id="tfContato" type="text" name="usuarioAutorizacao.nome" value='${coleta.usuarioAutorizacao.nome } (${coleta.usuarioAutorizacao.codigo != null ? coleta.usuarioAutorizacao.codigo : ""})' title="${coleta.usuarioAutorizacao.nome } ${coleta.usuarioAutorizacao.codigo != null ? (coleta.usuarioAutorizacao.codigo) : ''}" class="miniTdv form-control" readonly/>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-3">
								<label  title="Data da Autorização">Dt Aut:</label>
							</div>
							<div class="miniTdv col-sm-9">
								<input id="tfTelefone" type="text" name="dataAutorizacao" value="${coleta.dataAutorizacao}" class="miniTdv form-control" readonly />
							</div>
						</div>
						<div class="miniTdv row">
							<div class="miniTdv col-sm-3">
								<label  >E-mail: </label>
							</div>
							<div class="miniTdv col-sm-9">
								<input id="tfTelefone" type="text" name="usuarioAutorizacao.email" value="${coleta.usuarioAutorizacao.email}" class="miniTdv form-control" readonly />
							</div>
						</div>
					</div>
					<div class="col-sm-3">
							<label class="miniTdv label label-default" >Observação</label>
							<br></br>
							<textarea id="taColetaObs" type="text" rows=3 class="textAreaMiniTDV" readonly>${coleta.observacao}</textarea>
							<textarea id="taUsuObs" type="text" rows=4 class="textAreaMiniTDV" disabled></textarea>
							<input id="observacao" name="observacao" value="" type="hidden"/>
					</div>
				</div>	
				<!-- Itens da Coleta -->
				<div class="miniTdv panel panel-default row">
					<label class="miniTdv label label-default" >Itens da Coleta</label>
                    <a id="id_add_linha"href="javascript:adicionaLinha();" class="desativaAncora miniTdv btn btn-link btn-sm pull-right"><i class="miniTdv glyphicon glyphicon-plus"></i></a>
					<br><br>
					<div class="miniTdv col-sm-12">
						<div class="tbResponsive miniTdv table-responsive">
								<table id="tbItensColeta" class="miniTdv table table-hover table-bordered table-condensed table-striped table-hover">
									<thead>
										<tr>
											<th>Mercadoria</th>
											<th>Peso</th>
											<th>Larg.</th>
											<th>Alt.</th>
											<th>Comp.</th>
											<th>Cubagem</th>
											<th>Vol.</th>
											<th>Qtde. Emp.</th>
											<th>Cód Mercadoria</th>
											<th>Cód Emb.</th>
											<th>Remont.</th>
											<th>Cód ONU</th>
											<th >Grp Emb</th>
											<th class="colTarefas text-center"><i class="glyphicon glyphicon-tasks"></i></th>
										</tr>
									</thead>
									<tbody>
									 <c:forEach var="item" items="${coletaItems}">
									 	<tr id="linhaItem${item.sequencia}">
									 		<td id="item${item.sequencia}coletaNCompra" class="hidden">${coleta.nCompra}</td>
									 		<td id="item${item.sequencia}coletaCiclo" class="hidden">${coleta.ciclo}</td>
									 		<td id="item${item.sequencia}sequencia" class="hidden">${item.sequencia}</td>
	            							<td id="item${item.sequencia}mercadoriaDescricao" class="miniTdv ${item.sequencia}">${item.mercadoria.descricao}</td>
											<td class="${item.sequencia}" id="item${item.sequencia}peso">${item.peso}</td>
											<td class="${item.sequencia}" id="item${item.sequencia}largura">${item.largura}</td>
											<td class="${item.sequencia}" id="item${item.sequencia}altura">${item.altura }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}comprimento">${item.comprimento }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}cubagem">${item.cubagem }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}volume">${item.volume }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}quantidadeEmp">${item.quantidadeEmp }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}mercadoriaCodigo">${item.mercadoria.codigo }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}embalagemCodigo">${item.embalagem.codigo }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}remont">${item.remont }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}onuCodigo">${item.onuCodigo }</td>
											<td class="${item.sequencia}" id="item${item.sequencia}onuGrpEmb">${item.onuGrpEmb }</td>
											<td><a id="edit${item.sequencia}" class="desativaAncora editItem" href="javascript:habilitaLinha(${item.sequencia});"><i class="miniTdv glyphicon glyphicon-pencil"></i></a>
											<a id="confirm${item.sequencia}" class="desativaAncora confirmItem" href="javascript:saveItem(${item.sequencia});"><i class="miniTdv glyphicon glyphicon-ok"></i></a>
											<a id="delete${item.sequencia}" class="desativaAncora deleteItem" href="javascript:deletaLinha(${item.sequencia});"><i class="miniTdv glyphicon glyphicon-minus"></i></a>
											</td>
										</tr>
	        						 </c:forEach>
									</tbody>
								</table>
						</div>
					</div>
					<br><br><br><br><br>
					<div class="miniTdv row">
						<div class="miniTdv col-sm-12">
							<div class="miniTdv col-sm-1">
								<label  >Tipo</label>
								<input id="tfTipo" type="text" value="${coleta.tipo }" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Peso</label>
								<input id="tfPeso" type="text" name="peso" value="${coleta.peso}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Peso Cubado</label>
								<input id="tfPesoCubado" type="text" name="pesoCobrado" value="${coleta.pesoCobrado}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Volumes</label>
								<input id="tfVolumes" type="text" name="volumes" value="${coleta.volumes }" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Altura</label>
								<input id="tfAltura"type="text" value="${coleta.altura}" name="altura" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Largura</label>
								<input id="tfLargura" type="text" name="largura" value="${coleta.largura}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Comprimento</label>
								<input id="tfComprimento" type="text" name="comprimento" value="${coleta.comprimento}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >ASN</label>
								<input id="tfAsn"type="text" value="${coleta.xmlColetaNumero}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Placa</label>
								<input id="tfPlaca"type="text" value="${coleta.placa}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-1">
								<label  >Saque</label>
								<input id="tfSaque" type="text" value="${coleta.placaSaque}" class="miniTdv form-control" readonly />
							</div>
							<div class="miniTdv col-sm-2">
								<label  >Motorista</label>
								<input id="tfMotorista"type="text" value="${coleta.carreteiro }" class="miniTdv form-control" readonly />
							</div>
						</div>
					</div>
					
				</div>
				<!-- Veiculo -->
<!-- 				<div class="miniTdv panel panel-default row"> -->
<!-- 					<div class="miniTdv row"> -->
<!-- 						<div class="miniTdv col-sm-4"> -->
<!-- 							<label class="miniTdv label label-default" >Veículo</label> -->
<!-- 							<br/><br/> -->
<!-- 							<div class="miniTdv row"> -->
<!-- <!-- 								<div class="miniTdv col-sm-2"> --> 
<!-- 								<div class="miniTdv col-sm-1"> -->
<!-- 									<label  >Placa</label> -->
<!-- 								</div> -->
<!-- <!-- 								<div class="miniTdv col-sm-3"> --> 
<!-- 								<div class="miniTdv col-sm-2"> -->
<%-- 									<input id="tfPlaca"type="text" value="${coleta.placa}" class="miniTdv form-control" readonly /> --%>
<!-- 								</div> -->
<!-- 								<div class="miniTdv col-sm-2"> -->
<!-- 									<label  >Saque</label> -->
<!-- 								</div> -->
<!-- 								<div class="miniTdv col-sm-3"> -->
<%-- 									<input id="tfSaque" type="text" value="${coleta.placaSaque}" class="miniTdv form-control" readonly /> --%>
<!-- 								</div> -->
<!-- <!-- 							</div> --> 
<!-- <!-- 							<div class="miniTdv row"> --> 
<!-- 								<div class="miniTdv col-sm-2"> -->
<!-- 									<label  >Motorista</label> -->
<!-- 								</div> -->
<!-- <!-- 								<div class="miniTdv col-sm-8"> --> 
<!-- 								<div class="miniTdv col-sm-2"> -->
<%-- 									<input id="tfMotorista"type="text" value="${coleta.carreteiro }" class="miniTdv form-control" readonly /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
						<!-- Dadis do contato -->
<!-- 						<div class="miniTdv col-sm-8"> -->
<!-- 							<label class="miniTdv label label-default" >Dados da Autorização</label> -->
<%-- 							<c:if test="${coleta.flagAutorizacao == 'E' }"> --%>
<!-- 								<label class="tpAutorizacao text-danger pull-right">Autorizada na efetivação  </label>							 -->
<%-- 							</c:if> --%>
<!-- 							<br/><br/> -->
<!-- 							<div class="miniTdv row"> -->
<!-- 								<div class="miniTdv col-sm-2"> -->
<!-- 									<label  >Autorizado por:</label> -->
<!-- 								</div> -->
<!-- 								<div class="miniTdv col-sm-5"> -->
<%-- 									<label >${coleta.usuarioAutorizacao.nome }</label> --%>
<%-- <%-- 									<input id="tfPlaca"type="text" value="${coleta.usuarioAutorizacao.nome }" class="miniTdv form-control" readonly /> --%> 
<!-- 								</div> -->
<!-- 								<div class="miniTdv text-right col-sm-2"> -->
<!-- 									<label  >Dt Autorização: </label> -->
<!-- 								</div> -->
<!-- 								<div class="miniTdv col-sm-3"> -->
<%-- 									<label >${coleta.dataAutorizacao}</label> --%>
<%-- <%-- 									<input id="tfSaque" type="text" value="${coleta.dataAutorizacao}" class="miniTdv form-control" readonly /> --%> 
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="miniTdv row"> -->
<!-- 								<div class="miniTdv col-sm-2"> -->
<!-- 									<label  >E-mail: </label> -->
<!-- 								</div> -->
<!-- 								<div class="miniTdv col-sm-8"> -->
<%-- 									<label >${coleta.usuarioAutorizacao.email} (${coleta.usuarioAutorizacao.codigo})</label> --%>
<%-- <%-- 									<input id="tfMotorista"type="text" value="${coleta.usuarioAutorizacao.email} (${coleta.usuarioAutorizacao.codigo})" class="miniTdv form-control" readonly /> --%> 
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				
				<!-- Campos para Completar o xml da coleta no momento do Update -->
				<input id="cnpjSolicitante" name="cnpjSolicitante" value="${coleta.cnpjSolicitante}" type="hidden"/>
				<input id="coletaTpFrete" name="coletaTpFrete.id" value="${coleta.coletaTpFrete.id == null ? 0 : coleta.coletaTpFrete.id}" type="hidden"/>
				<input id="descricaoNFs" name="descricaoNFs" value="${coleta.descricaoNFs}" type="hidden"/>
				<input id="ocorrenciaCodigo" name="ocorrencia.codigo" value="${coleta.ocorrencia.codigo}" type="hidden"/>
				<input id="pesoBalanca" name="pesoBalanca" value="${coleta.pesoBalanca}" type="hidden"/>
				<input id="prioridade" name="prioridade" value="${coleta.prioridade}" type="hidden"/>
				<input id="quantidadeNfs" name="quantidadeNfs" value="${coleta.quantidadeNfs}" type="hidden"/>
				<input id="usuarioCadastro" name="usuarioCadastro.codigo" value="${coleta.usuarioCadastro.codigo}" type="hidden"/>
				<input id="valorColeta" name="valorColeta" value="${coleta.valorColeta < 0.1 ? 0.1 : coleta.valorColeta}" type="hidden"/>
				<input id="coletaOrigem" name="coletaOrigem.codigo" value="${coleta.coletaOrigem.codigo == null ? 4 : coleta.coletaOrigem.codigo}" type="hidden"/>
				<input id="cnpjPagadorFrete" name="cnpjPagadorFrete" value="${coleta.cnpjPagadorFrete}" type="hidden"/>
				<input id="consolidaArmazem" name="consolidaArmazem" value="${coleta.consolidaArmazem}" type="hidden"/>
				<input id="flagImportacao" value="${flagImportacao}" type="hidden"/>		
			 </form>
	    </div>
			
 
	  </div> <!--/ container -->
			  <input id="message" name="message" value="${message}" type="hidden">
	  
	  <c:import url="modal-edit-parceiros.jsp"></c:import>
	  <c:import url="modal-edit-add-parceiro.jsp"></c:import>
	  <c:import url="modal-cnpj-remetente.jsp"></c:import>
	  <script>
		  $(document).ready(function(){
				$('[data-toggle="tooltip"]').tooltip();
		  });
		  $(document).ready(function(){
				if($('#message').val() != null && $('#message').val() != undefined && $('#message').val() != ''){
					alertify.log($('#message').val());
				}
		  });
	  </script>
	  
	  
	      <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
	
	 
	<!--# Lib TDV #-->
	
	<!-- Validacao do CNPJ -->
	<script src="${pageContext.request.contextPath}/static/tdv/js/ValidaCNPJ.js"></script>

	<!-- Commum -->
	<script src="${pageContext.request.contextPath}/static/tdv/js/Common.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/common/mask-1.0.4.js"></script>
	
	<!-- Config App --> 
	<script src="${pageContext.request.contextPath}/static/tdv/js/ConfigApp.js"></script>

	<!-- Services --> 
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/ColetaService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/ColetaItemService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/TipoVeiculoService-1.0.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/TipoCargaService-1.0.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/CliendService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/ClienteService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/ArmazemService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/PreColetaService.js"></script> 
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/CommonService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/UsuarioService.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/service/ParceiroService.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/tdv/js/ColetaSimulador.js"></script>
	
	<!-- Controllers -->	
	<script src="${pageContext.request.contextPath}/static/tdv/js/controller/coleta-home-controller-1.0.2.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/controller/coleta-form-wizard-controller-1.1.6.js"></script>
	<script src="${pageContext.request.contextPath}/static/tdv/js/controller/coleta-consulta-controller-1.1.1.js"></script>  
	<script src="${pageContext.request.contextPath}/static/tdv/js/controller/coleta-form-edit-controller-1.0.0.js"></script>
	
	<!-- end: CORE JAVASCRIPTS  -->
	<script type="text/javascript">
		jQuery(document).ready(function() {
			// Diego --> Loading 
			$("#spinner").bind("ajaxSend", function() {
				$(this).show();
			}).bind("ajaxStop", function() {
				$(this).hide();
			}).bind("ajaxError", function() {
				$(this).hide();
			});
			// Diego --> Loading Ajax
			$(document).ajaxStart(function(){
				$('#spinner').show();
				$( "button" ).addClass('disabled');
			 }).ajaxStop(function(){
				 $('#spinner').hide();
				 $( "button" ).removeClass( "disabled" );
			 });					
		});
		
		// encerra sessao
		function logout() {
			var usuarioService = new UsuarioService();
			usuarioService.logout().success(function() {
				window.close();
			}).fail(function(error) {
				if(error.status == 200) { 
					window.close();
					window.location.href = "http://extranet.dellavolpe.com.br";
				} 
				else alert("Erro ao encerrar sessão\n"+JSON.stringify(error));
			});
		}
		
	</script>    
    <!--# fim Lib TDV #-->

