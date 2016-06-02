<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Parceiros da Coleta-->
<div class="modal fade" id="myModalCliente" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelCliente"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#E7E3E2;">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabelCliente">
					 <span class="coltitleModal" style="color:black;"><i class="glyphicon glyphicon-plus"></i> Consultar Cliente</span> 
				</h4>
			</div>
			<div class="modal-body">
				
				<div class="">
					<div class="row">
						<form name="formPesquisaCliente" class="col-sm-4">
							<input type="text" name="campoPesquisaCliente" ng-model="ctrl.campoPesquisaCliente" class="form-control input-sm"required>
						</form>
						<div class="col-sm-2">
							<button ng-click="ctrl.pesquisaCliente()" class="btn btn-default" ng-disabled="formPesquisaCliente.$invalid"><i class="glyphicon glyphicon-search"></i></button>
						</div>
					</div>
				</div>
				
				<br>
				<div class="panel panel-default">
					<div class="panel-body" style="width:100%; height:150px; overflow: auto;" >
						<table class="table table-bordered table-responsive table-condensed table-hover">
							<thead>
								<tr>
									<th>Cód</th>
									<th>Nome</th>
									<th>Anfitriã</th>
								</tr>
							</thead>
							<tbody> 
								<tr ng-repeat="cliente in ctrl.clientes" ng-click="ctrl.pegaCliente(cliente)">
									<td>{{ cliente.idCliente }}</td>
									<td>{{ cliente.nome }}</td>
									<td>{{ cliente.anfitria }}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
						
			</div>
	 		<div class="modal-footer">
				<a class="btn btn-default btn-sm"  href="javascript:hideModaCliente();"><i class="glyphicon glyphicon-remove"></i> Fechar</a>
			</div> 
		</div>
	</div>
</div>