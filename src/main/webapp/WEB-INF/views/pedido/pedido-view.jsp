<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class="container" ng-controller="pedidoViewController as ctrl">
	<span ng-init='ctrl.pedido = ${pedido}'></span>
	<span ng-init='ctrl.produtos = ${produtos}'></span>

	<div class="row">
		<div class="col-xs-2">
			<label class="label label-default">Código do Pedido</label>
			<input class="form-control input-sm" type="number" ng-model="ctrl.pedido.codPedido" disabled>
		</div>
		<div class="col-xs-2">
			<label class="label label-default">Data de Entrega</label>
			<input class="form-control input-sm" type="date" ng-model="ctrl.pedido.dtEntrega" disabled>
		</div>
		<div class="col-xs-2">
			<label class="label label-default">Data de Solicitação</label>
			<input class="form-control input-sm" type="date" ng-model="ctrl.pedido.dtSolicitacao" disabled>
		</div>			
		<div class="col-xs-2">
			<label class="label label-default">Valor Pago</label>
			<input class="form-control input-sm" type="text" ng-model="ctrl.pedido.valorPago" disabled>
		</div>
		<div class="col-xs-2">
			<label class="label label-default">Valor do Imposto</label>
			<input class="form-control input-sm" type="text" ng-model="ctrl.pedido.valorImposto" disabled>
		</div>
	</div>

	<br>
	<input class="form-control input-sm" ng-model="ctrl.filtro">
	<br>
	<div class="tbResponsive miniTdv table-responsive">
		<table id="tbItensColeta" class="miniTdv table table-hover table-bordered table-condensed table-striped table-hover">
			<thead>
				<tr>
					<th>Cód. Produto</th>
					<th>Descrição </th>
					<th>Valor Pago</th>
					<th>Quantidade</th>
					<th class="colTarefas text-center"><i class="glyphicon glyphicon-tasks"></i></th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="produto in ctrl.produtos| filter: ctrl.filtro">
					<td><input ng-model="produto.produto.codProduto" disabled></td>
					<td><input ng-model="produto.produto.descricao" disabled></td>
					<td><input ng-model="produto.valorPago" ></td>
					<td><input ng-model="produto.qntdProdutos"></td>
					<td>
						<a ng-click="ctrl.liberaLinha(produto)"><i class="glyphicon glyphicon-ok"></i></a>
					</td> 
	            </tr>
			</tbody>
		</table>
	</div>
</div>