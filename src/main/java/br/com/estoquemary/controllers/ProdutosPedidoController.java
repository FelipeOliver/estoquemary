package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.services.ProdutosPedidoService;

@Controller
@RequestMapping("/pedidoProdutos")
public class ProdutosPedidoController {
	
	@Autowired
	private ProdutosPedidoService produtosPedidoService;
	
	@RequestMapping(value="/addList", method=RequestMethod.POST)
	public ResponseEntity<String> addList(List<ProdutosPedido> list){
		try{
			List<ProdutosPedido> p = produtosPedidoService.addListProdutoPedidos(list);
			return new ResponseEntity<String>("Lista com "+ p.size() +"produtos no pedido"+ p.get(0).getPedido() +"Salva com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
