<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
	<head>
		<title>Estoque MaryKay</title>
		<link rel="stylesheet" type="text/css" href="/estoquemary/resources/bootstrap/css/bootstrap.css">
	</head>
	
	<body ng-app="EstoqueMaryApp">
		<div class="row" style="margin-bottom:80px;">
			<c:import url="menu.jsp"></c:import>
		</div>
		<div class="row">
			<c:import url="${pagina}"></c:import>
		</div>
	</body>
	
	<script type="text/javascript" src="/estoquemary/resources/jquery/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/jquery/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/angularJs/angular.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript" src="/estoquemary/resources/core/js/app.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/services/ProdutoService.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/controllers/produtoController.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/controllers/pedidoController.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/controllers/estoqueController.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/controllers/vendaController.js"></script>
	<script type="text/javascript" src="/estoquemary/resources/core/js/directives/dinheiroMask.js"></script>
</html>