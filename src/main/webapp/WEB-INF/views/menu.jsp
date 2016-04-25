<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<nav class="navbar navbar-default navbar-fixed-top" style="background-color:black; color:white;">
  <div class="container">
	<div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Controle MK</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li ${ escolhido == 'Produto' ? 'class="active"': ''}><a href="/estoquemary/">Produtos <span class="sr-only">(current)</span></a></li>
        <li ${ escolhido == 'Pedido' ? 'class="active"': ''}><a href="/estoquemary/pedido/add">Pedidos</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
      </ul>
      </div>
  </div>
</nav>