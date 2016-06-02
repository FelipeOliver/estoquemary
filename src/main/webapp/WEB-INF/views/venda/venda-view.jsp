<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class="container" ng-controller="vendaViewController as ctrl">
	<span ng-init='ctrl.vendas = ${vendas}'></span>
<%-- 	<span ng-init='ctrl.produtos = ${produtos}'></span> --%>

	<div class="tbResponsive miniTdv table-responsive">
		<input class="form-control input-md" ng-model="ctrl.filtro"/>
		<table id="tbItensColeta" class="miniTdv table table-hover table-bordered table-condensed table-striped table-hover">
			<thead>
				<tr>
					<th>Cód Venda</th>
					<th>Cliente</th>
					<th>Data de Entrega</th>
					<th>Valor Pago</th>
					<th class="colTarefas text-center"><i class="glyphicon glyphicon-tasks"></i></th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="venda in ctrl.vendas| filter: ctrl.filtro">
					<td>{{venda.idVenda}}</td>
					<td>{{venda.cliente.nome}}</td>
					<td>{{venda.dataVenda}}</td>
					<td>{{venda.valorTotalVenda}}</td>
					<td>
						<a href="/estoquemary/venda/listVenda/{{venda.idVenda}}"><i class="glyphicon glyphicon-eye-open"></i></a>
						<a ng-click="ctrl.rmvVenda(venda)"><i class="glyphicon glyphicon-minus"></i></a>
					</td> 
	            </tr>
			</tbody>
		</table>
	</div>
	<a href="/estoquemary/venda/newVenda" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-plus"> Nova Venda</i></a>
</div>