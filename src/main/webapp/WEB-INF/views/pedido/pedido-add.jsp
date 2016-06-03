<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class="container" ng-controller="pedidoController as ctrl">
	<c:if test="${not empty produtosDisponiveis}">
		<span ng-init='ctrl.disponiveis = ${produtosDisponiveis}'></span>
	</c:if>
	<c:if test="${not empty produtosAdicionados}">
		<span ng-init='ctrl.produtosAdicionados = ${produtosAdicionados}'></span>
	</c:if>
	<c:if test="${not empty pedido}">
		<span ng-init='ctrl.pedido = ${pedido}'></span>
	</c:if>
	<form ng-submit="ctrl.addPedido()" name="formAddPedido" style="margin-top:20px">
		<div class="row">
			<div class="col-xs-2">
				<label class="label label-default">Código do Pedido</label>
				<input class="form-control input-sm" ng-disabled="ctrl.salvo" type="number" ng-model="ctrl.pedido.codPedido" ng-numero required>
			</div>
			<div class="col-xs-2">
				<label class="label label-default">Data de Entrega</label>
				<input class="form-control input-sm" ng-disabled="ctrl.salvo" type="date" ng-model="ctrl.pedido.dtEntrega" required>
			</div>
			<div class="col-xs-2">
				<label class="label label-default">Data de Solicitação</label>
				<input class="form-control input-sm"  ng-disabled="ctrl.salvo" type="date" ng-model="ctrl.pedido.dtSolicitacao" required>
			</div>			
			<div class="col-xs-2">
				<label class="label label-default">Valor Pago</label>
				<input class="form-control input-sm" 
				       type="number" step="0.01" 
				       ng-disabled="ctrl.salvo"
				       name="valorPago" ng-model="ctrl.pedido.valorPago" 
				       ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" required>
				<span class="text-danger" ng-show="ctrl.formAddPedido.valorPago.$invalid">Número inválido</span>
			</div>
			<div class="col-xs-2">
				<label class="label label-default">Valor do Frete</label>
				<input class="form-control input-sm" 
				       type="number" step="0.01" 
				       ng-disabled="ctrl.salvo"
				       name="valorImposto" ng-model="ctrl.pedido.valorImposto" 
				       ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" required>
				<span class="text-danger" ng-show="ctrl.formAddPedido.valorImposto.$invalid">Número inválido</span>
			</div>
		</div>
		<div class="row">
			<button type="submit" ng-disabled="formAddPedido.$invalid" class="btn btn-success btn-md" style="margin-top: 10px; margin-left: 15px;">Salvar</button>
		</div>
	</form>
	
	<div class="row" style="margin-top:20px;">

		<!-- Panel da Esquerda -->
		<div class="panel panel-default col-xs-5" style="height:400px;overflow-y:auto;">
			
			<div class="panel-heading" style="margin-top:5px;margin-bottom:5px">
				<div class="row">
					<div class="col-xs-6" >
						<h3>Produtos</h3>
					</div>	
					<div class="col-xs-6">
						<label class="label label-default">Procure aqui:</label>
						<input class="form-control input-md" ng-model="ctrl.filtro"/>
					</div>
				</div>
			</div>
			
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Código Produto</th>
							<th>Descrição do Produto</th>
							<th>Pontuação</th>
						</tr>
					</thead>
					<tbody >
						<tr ng-repeat="produto in ctrl.produtosDisponiveis | filter: ctrl.filtro" ng-click="ctrl.addProduto(produto)">
							<td>{{ produto.produto.codProduto }}</td>
							<td>{{ produto.produto.descricao }}</td>
							<td>{{ produto.produto.pontuacao }}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			
			<div class="col-xs-1" >
				<a class="btn btn-default" style="margin-top:30px"><i class="glyphicon glyphicon-backward"></i></a>
				
				<a class="btn btn-default" style="margin-top:30px" ><i class="glyphicon glyphicon-forward"></i></a>
			</div>
			
			<!-- Panel da Direita -->
			<div class="panel panel-default col-xs-5" style="height:400px;overflow-y:auto;">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-8">
							<h3>Produtos Adicionados</h3>
						</div>
						<div class="col-xs-4" style="margin-top:20px">
							<a class="btn btn-default btn-md" ng-click="ctrl.addList()" ng-disabled="(ctrl.produtosAdicionados.lenght > 0)||!ctrl.salvo"><i class="glyphicon glyphicon-ok"></i> Confirmar</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-hovered">
						<thead>
							<tr>
								<th><i class="glyphicon glyphicon-tasks"></i></th>
								<th>Código Produto</th>
								<th>Descrição do Produto</th>
<!-- 								<th>Pontuação</th> -->
								<th>Quantidade</th>
								<th>Vlr. Pago</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="produto in ctrl.produtosAdicionados " >
								<td ng-if="produto.id === null"><a ng-click="ctrl.rmvProduto(produto)"><i class="glyphicon glyphicon-remove"></i></a></td>
								<td ng-if="produto.id != null"><a ng-click="ctrl.rmvProdutoDb(produto)"><i class="glyphicon glyphicon-minus"></i></a></td>
								<td >{{ produto.produto.codProduto }}</td>
								<td>{{ produto.produto.descricao }}</td>
<!-- 								<td>{{ produto.pontuacao }}</td> -->
								<td hidden="hidden"><input ng-model="produto.produto.codProduto" value="{{produto.codProduto}}" class="form-control"></td>
								<td hidden="hidden"><input ng-model="produto.pedido.codProduto" value="ctrl.pedido.codPedido" class="form-control"></td>
<!-- 								<input ng-model="produto.qntdProdutos" value="0" class="form-control" ng-numero> -->
								<td><input type="number" step="0.01" 
				       					   ng-model="produto.qntdProdutos" 
				       					   ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" required></td>
								<td><input ng-model="produto.valorPago" value="0.0" class="form-control"></td>
								
							</tr>	
						</tbody>
					</table>
				</div>
			</div>
		</div>
</div>