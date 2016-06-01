package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.services.EstoqueProdutosService;
import br.com.estoquemary.services.ProdutosPedidoService;

@Controller
@RequestMapping("/pedidoProdutos")
public class ProdutosPedidoController {
	
	@Autowired
	private ProdutosPedidoService produtosPedidoService;
	
	@Autowired
	private EstoqueProdutosService estoqueProdutosService;
	
	@RequestMapping(value="/addList", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> addList(@RequestBody List<ProdutosPedido> list){
		try{
			System.out.println(list.toString());
			List<ProdutosPedido> p = produtosPedidoService.addListProdutoPedidos(list);
			estoqueProdutosService.insereProdutosEstoque(p);

			return new ResponseEntity<String>("Lista com "+ p.size() +"produtos no pedido"+ p.get(0).getPedido() +"Salva com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/rmvProduto", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> rmvProduto(@RequestBody ProdutosPedido pedidoProdutos){
		try{
			System.out.println(pedidoProdutos.toString());
			String sucesso = "Produto "+ pedidoProdutos.getProduto().getCodProduto() +" deletado com sucesso do Pedido "+ pedidoProdutos.getPedido().getCodPedido();
			produtosPedidoService.rmvProduto(pedidoProdutos);
			estoqueProdutosService.atualizaEstoque(pedidoProdutos.getProduto());
			return new ResponseEntity<String>(sucesso, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
