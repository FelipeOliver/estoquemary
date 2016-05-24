<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading" style="background-color:#E7E3E2;color:black;"><!-- #CCA498 -->
			<h2 style="text-align:center;">Procurar um <span style="color:#CCA498;">Produto</span></h2>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-2">
					<label class="label label-default">Procurar</label>
					<input id="campoPesquisa" class="form-control input-sm" type="number" required>
				</div>
			</div>
			<div class="row">
				<button type="button" onclick="" class="btn btn-primary btn-md" style="margin-top: 10px; margin-left: 15px;">
					<i class="glyphicon glyphicon-plus"></i> Salvar
				</button>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2">
				<label class="label label-default">Código do Produto</label>
				<input class="form-control input-sm" type="number" id="codProduto" ng-numero ng-Maxlength="8" mask="999.999" ng-Minlength="8" required>
			</div>
			<div class="col-xs-4">
				<label class="label label-default">Descrição</label>
				<input class="form-control input-sm" type="text" ng-model="ctrl.produto.descricao" required>
			</div>
			<div class="col-xs-2">
				<label class="label label-default">Pontuação</label>
				<div class="input-group">
					<input class="form-control input-sm" type="number"  ng-model="ctrl.produto.pontuacao"  ng-numero required>
					<div class="input-group-addon">Pontos</div>
				</div>
			</div>
			<div class="col-xs-2">
				<label class="label label-default">Valor de Venda</label>
				<div class="input-group">
					<div class="input-group-addon">R$</div>
					<!-- ng-pattern="^[0-9]*\.[0-9]*$"  -->
					<input class="form-control input-sm" type="text"  ng-model="ctrl.produto.valorVenda" 
					ng-dinheiro required>
					<!-- ng-keyup="dinheiro()" -->
				</div>
			</div>
		</div>
	</div>
</div>