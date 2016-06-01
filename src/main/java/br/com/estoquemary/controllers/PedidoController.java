package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.estoquemary.models.Pedido;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.services.EstoqueProdutosService;
import br.com.estoquemary.services.PedidoService;
import br.com.estoquemary.services.ProdutoService;
import br.com.estoquemary.services.ProdutosPedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutosPedidoService produtosPedidoService;

	private EstoqueProdutosService estoqueProdutosService;
	
	@RequestMapping("/")
	public ModelAndView pagePedido(){
		ModelAndView mv = new ModelAndView("index");
//		List<Produto> list = produtoService.findAll();
		List<Pedido> list = pedidoService.findAll();
		mv.addObject("pedido", new Gson().toJson(list));
		mv.addObject("pagina", "pedido/pedido-view.jsp");
		mv.addObject("escolhido", "Pedido");
		return mv;
	}

	@RequestMapping(value="/listPedido/{codPedido}", method=RequestMethod.GET, produces="application/json")
	public ModelAndView updPedido(@PathVariable String codPedido){
		ModelAndView mv = new ModelAndView("index");
		try{
			List<Produto> list = produtoService.findAll();
			Pedido pedido = this.pedidoService.findOne(codPedido);;
			List<ProdutosPedido> listAdicionados = this.produtosPedidoService.findByPedido(pedido);
			mv.addObject("produtosDisponiveis", new Gson().toJson(list));
			mv.addObject("produtosAdicionados", new Gson().toJson(listAdicionados));
			System.out.println(new Gson().toJson(listAdicionados));
			mv.addObject("pedido", new Gson().toJson(pedido));
			mv.addObject("pagina", "pedido/pedido-add.jsp");
			mv.addObject("escolhido", "Pedido");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="/addPedido", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> addPedido(@RequestBody Pedido pedido){
		try{
			Pedido p = pedidoService.addPedido(pedido);
//			produtosPedidoService.addListProdutoPedidos(produtos);
			return new ResponseEntity<String>("Pedido: "+ p.getCodPedido() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> deletePedido(@RequestBody Pedido pedido){
		try{
			List<ProdutosPedido> produtos = this.produtosPedidoService.findByPedido(pedido);
			for(int i = 0; i < produtos.size(); i++){
				this.produtosPedidoService.rmvProduto(produtos.get(i));
				this.estoqueProdutosService.atualizaEstoque(produtos.get(i).getProduto());
			}
			this.pedidoService.deletePedido(pedido);
			return new ResponseEntity<String>("Pedido: "+ pedido.getCodPedido() + " Deletado com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/newPedido", method=RequestMethod.GET)
	public ModelAndView newPedido(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pagina", "pedido/pedido-add.jsp");
		mv.addObject("escolhido", "Pedido");
		return mv;
	}

}
