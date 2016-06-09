<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="container panel panel-default" ng-controller="estoqueController as ctrl">

	<div class="panel-heading" style="background-color:#E7E3E2;color:black;"><!-- #CCA498 -->
		<h2 style="text-align:center;">Estoque de <span style="color:#CCA498;">Produtos</span></h2>
	</div>

	<div class="panel-body">
		<ul class="nav nav-pills">
  			<li role="presentation" ${ momento == 'estoque' ? 'class="active"': ''}><a href="/estoquemary/estoque/emestoque">Em Estoque</a></li>
  			<li role="presentation" ${ momento == 'falta' ? 'class="active"': ''}><a href="/estoquemary/estoque/emfalta">Em Falta</a></li>
  			<li role="presentation" ${ momento == 'compra' ? 'class="active"': ''}><a href="/estoquemary/estoque/naocomprado">Não comprado</a></li>
		</ul>
		
		<br><br>
		<input ng-model="ctrl.campoBusca" ng-change="ctrl.busca()" class="form-control"/> 
		<table class="table table-striped table-condensed table-bordered">
			<thead>
				<tr>
					<th style="text-align:center;">Cód</th>
					<th style="text-align:center;">Descrição</th>
					<th style="text-align:center;">Em Estoque</th>
					<th style="text-align:center;">Valor</th>
					<th style="text-align:center;">Médio Vendido</th>
					<th style="text-align:center;">Qntd Vendida</th>
					<th style="text-align:center;">Total Vendido</th>
					<th style="text-align:center;">Custo Médio</th>
					<th style="text-align:center;">Qntd Comprada</th>
					<th style="text-align:center;">Total Pago</th>
				</tr>
			</thead>
			<tbody  class="busca">
				<c:if test="${not empty produtos}">
					<c:forEach var="produto" items="${produtos}" >
						<tr>
							<td style="text-align:center;">${produto.produto.codProduto}</td>
							<td style="text-align:center;">${produto.produto.descricao.toUpperCase().trim() }</td>
							<td style="text-align:center;">${produto.qntdEstoque}</td>
							<td style="text-align:center;">R$ ${produto.produto.valorVenda}</td>
							<td style="text-align:center;">${produto.valorMedioVendido}</td>
							<td style="text-align:center;">${produto.qntdVendida}</td>
							<td style="text-align:center;">R$ ${produto.valorVendido}</td>
							<td style="text-align:center;">R$ ${produto.valorMedio}</td>
							<td style="text-align:center;">${produto.qntdComprada}</td>
							<td style="text-align:center;">R$ ${produto.valorTotal}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>