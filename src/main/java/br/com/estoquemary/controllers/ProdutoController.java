package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.ProdutoService;

@Controller("produtoController")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> addPProduto(Produto produto){
		System.out.println("Passou aqui");
		try{
			produto.setValor_venda(produto.getValor_venda() * 10);
			Produto p = produtoService.addProduto(produto);
			return new ResponseEntity<String>("Produto: "+ p.getCod_produto() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/findAll", produces="application/json")
	public ResponseEntity<String> findAllProdutos(){
		try{
			List<Produto> list = produtoService.findAll();
			System.out.println(list.toString());
			return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
