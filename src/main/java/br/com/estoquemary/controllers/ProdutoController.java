package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.EstoqueProdutosService;
import br.com.estoquemary.services.ProdutoService;
import br.com.estoquemary.services.ProdutosPedidoService;

@Controller("produtoController")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutosPedidoService produtosPedidoService;

	@Autowired
	private EstoqueProdutosService estoqueProdutoService;
	
	@RequestMapping("/")
	public ModelAndView getHome(){
		ModelAndView mv = new ModelAndView("index");
		List<Produto> list = produtoService.findAll();
		System.out.println(list.toString());
		mv.addObject("produtos", new Gson().toJson(list));
		mv.addObject("pagina", "produto/produto-add.jsp");
		mv.addObject("escolhido", "Produto");
		return mv;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> addPProduto(Produto produto){
		try{
			produto.setValorVenda(produto.getValorVenda() * 10);
			Produto p = produtoService.addProduto(produto);
			return new ResponseEntity<String>("Produto: "+ p.getCodProduto() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public ResponseEntity<String> removeProduto(@PathVariable String id){
		try{
			Long codProduto = Long.parseLong(id);
			Produto produto = this.produtoService.findOne(codProduto);
			if(produto != null){
				int produtosOnPedido = this.produtosPedidoService.countByProduto(produto);
				if(produtosOnPedido <= 0){
					EstoqueProdutos estoque = this.estoqueProdutoService.findByProduto(produto);
					this.estoqueProdutoService.delete(estoque);
					this.produtoService.removeProduto(produto);
					return new ResponseEntity<String>("Produto: "+ id + " Deletado com Sucesso", HttpStatus.CREATED);
				}else{
					return new ResponseEntity<String>("O Produto: "+ id + " Está vinculado a pelo menos um Pedido", HttpStatus.CONFLICT);
				}
			}else{
				return new ResponseEntity<String>("Não existe o Produto: "+ id, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			e.printStackTrace();
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
