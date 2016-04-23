package br.com.estoquemary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.ProdutoService;

@Controller("produtoController")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/add")
	public ResponseEntity<String> addPProduto(Produto produto){
		System.out.println("Passou aqui");
		try{
			Produto p = produtoService.addProduto(produto);
			return new ResponseEntity<String>("Produto: "+ p.getCod_produto() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
