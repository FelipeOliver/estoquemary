<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class="container" ng-controller="produtoController as ctrl">
	<div class="panel panel-default">
		<div class="panel-heading" style="background-color:#E7E3E2;color:black;"><!-- #CCA498 -->
			<h2 style="text-align:center;">Adicionar um novo <span style="color:#CCA498;">Produto</span></h2>
		</div>
		<div class="panel-body">
			<!-- action="produto/add" -->
			<form ng-submit="ctrl.addProduto()" name="ctrl.formAddProduto">
				<div class="row">
					<div class="col-xs-2">
						<label class="label label-default">Código do Produto</label>
						<input class="form-control input-sm" type="number" ng-model="ctrl.produto.cod_produto" ng-numero ng-Maxlength="8" mask="999.999" ng-Minlength="8" required>
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
							<input class="form-control input-sm" type="text"  ng-model="ctrl.produto.valor_venda" 
							ng-dinheiro required>
							<!-- ng-keyup="dinheiro()" -->
						</div>
					</div>
				</div>
				<div class="row">
					<button type="submit" class="btn btn-primary btn-md" ng-disabled="ctrl.formAddProduto.$invalid" style="margin-top: 10px; margin-left: 15px;"><i class="glyphicon glyphicon-plus"></i> Salvar</button>
				</div>
			</form>
		</div>
		
		<table class="table table-striped table-condensed">
			<thead>
				<tr>
					<th style="text-align:center;">Código Produto</th>
					<th style="text-align:center;">Produto</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty produtos}">
					<c:forEach var="produto" items="${produtos}" >
						<tr>
							<td style="text-align:center;">${produto.cod_produto}</td>
							<td style="text-align:center;">${produto.descricao}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>