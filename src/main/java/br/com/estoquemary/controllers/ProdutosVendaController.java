package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.estoquemary.models.ProdutosVenda;
import br.com.estoquemary.services.EstoqueProdutosService;
import br.com.estoquemary.services.ProdutosVendaService;

@Controller
@RequestMapping("/produtosVenda")
public class ProdutosVendaController {

	@Autowired
	private ProdutosVendaService produtosVendaService;
	@Autowired
	private EstoqueProdutosService estoqueProdutosService;

	@RequestMapping(value="/addList", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> addList(@RequestBody List<ProdutosVenda> list){
		try{
			System.out.println(list.toString());
			List<ProdutosVenda> p = this.produtosVendaService.addListProdutoPedidos(list);
			this.estoqueProdutosService.retiraProdutosEstoque(p);
			return new ResponseEntity<String>("Lista com "+ p.size() +"produtos na venda "+ p.get(0).getVenda().getIdVenda() +" Salva com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
