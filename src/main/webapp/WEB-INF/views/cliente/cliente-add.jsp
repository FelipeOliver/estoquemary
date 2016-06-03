<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="container" ng-controller="clienteController as ctrl">
	<span ng-init="ctrl.clientes = ${clientes}"></span>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h2 style="text-align:center;">Adicionar um novo <span style="color:#CCA498;">Cliente</span></h2>		
		</div>
		<div class="panel-body">
			<form ng-submit="ctrl.addCliente()" name="ctrl.fromAddCliente">
				<div class="row">
					<div class="col-xs-4">
						<label class="label label-default">Nome do cliente</label>
						<input class="form-control input-sm" type="text" ng-model="ctrl.cliente.nome">
					</div>
					<div class="col-xs-2">
						<label class="label label-default">Data de aniversário</label>
						<input class="form-control input-sm" type="date" ng-model="ctrl.cliente.dataAniversario" required>
					</div>
					<div class="col-xs-4">
						<label class="label label-default">E-mail</label>
						<input class="form-control input-sm"  type="email" ng-model="ctrl.cliente.email" required>
					</div>			
				</div>
				<br><br>	
				<div class="row">
					<div class="col-xs-2">
						<label class="label label-default">Tipo de Pele</label>
						<select class="form-control input-sm" ng-model="ctrl.cliente.tipoPele">
							<option value=""> </option>
							<option value="Normal">Normal</option> 
							<option value="Mista">Mista</option>
							<option value="Oleosa">Oleosa</option>
							<option value="Normal">Seca</option>
						</select>
					</div>
					<div class="col-xs-2">
						<label class="label label-default">Cor da Base</label>
						<input class="form-control input-sm" type="text" ng-model="ctrl.cliente.corBase" required>
					</div>
					<div class="col-xs-4">
						<label class="label label-default">Anfitriã</label>
						<input class="form-control input-sm" type="text" ng-model="ctrl.cliente.anfitria" required>
					</div>
					<div class="col-xs-4">
						Participa do Grupo do Whatsapp? Sim <input type="checkbox" value="S" ng-model="ctrl.cliente.flagWhatsapp">
					</div>
				</div>
				<div class="row">
					<button style="margin-top: 10px; margin-left: 15px;" type="submit" class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-plus"></i> Salvar</button>
				</div>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<input ng-model="ctrl.campoPesquisa" class="form-control">
			<table class="table table-striped table-default">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Aniversário</th>
						<th>Cor da Base</th>
						<th>Anfitriã</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="cliente in ctrl.clientes | filter: ctrl.campoPesquisa">
						<td>{{ cliente.nome }}</td>
						<td>{{ cliente.dataAniversario }}</td>
						<td>{{ cliente.corBase }}</td>
						<td>{{ cliente.anfitria }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>