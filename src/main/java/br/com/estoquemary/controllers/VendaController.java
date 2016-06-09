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

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosVenda;
import br.com.estoquemary.models.Venda;
import br.com.estoquemary.services.EstoqueProdutosService;
import br.com.estoquemary.services.ProdutosVendaService;
import br.com.estoquemary.services.VendaService;

@Controller
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private EstoqueProdutosService estoqueProdutosService;
	@Autowired
	private VendaService vendaService;
	@Autowired
	private ProdutosVendaService produtosVendaService;
	
	@RequestMapping("/newVenda")
	public ModelAndView newVenda(){
		ModelAndView mv = new ModelAndView("index");
		List<Produto> lp = estoqueProdutosService.findByQntdEstoqueReturnProduto();
		System.out.println(lp.toString());
		mv.addObject("produtosDisponiveis", new Gson().toJson(lp));
		mv.addObject("pagina", "venda/venda-add.jsp");
		mv.addObject("escolhido", "Venda");
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView getHome(){
		ModelAndView mv = new ModelAndView("index");
		List<Venda> lp = this.vendaService.findAll();
		System.out.println(lp.toString());
		mv.addObject("vendas", new Gson().toJson(lp));
		mv.addObject("pagina", "venda/venda-view.jsp");
		mv.addObject("escolhido", "Venda");
		return mv;
	}
	
	@RequestMapping(value="/listVenda/{idVenda}", method=RequestMethod.GET)
	public ModelAndView listVenda(@PathVariable String idVenda){
		ModelAndView mv = new ModelAndView("index");
		try{
			List<Produto> list = estoqueProdutosService.findByQntdEstoqueReturnProduto();
			Venda venda = this.vendaService.findOne(idVenda);
			List<ProdutosVenda> listAdicionados = this.produtosVendaService.findByVenda(venda);
			mv.addObject("produtosDisponiveis", new Gson().toJson(list));
			mv.addObject("produtosAdicionados", new Gson().toJson(listAdicionados));
			System.out.println(new Gson().toJson(listAdicionados));
			mv.addObject("venda", new Gson().toJson(venda));
			mv.addObject("pagina", "venda/venda-add.jsp");
			mv.addObject("escolhido", "Venda");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="/addVenda", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> addVenda(@RequestBody Venda venda){
		try{
			System.out.println(venda.toString());
			Venda p = this.vendaService.save(venda);
			return new ResponseEntity<String>(p.getIdVenda() + "", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> deleteVenda(@RequestBody Venda venda){
		try{
			List<ProdutosVenda> produtos = this.produtosVendaService.findByVenda(venda);
			for(int i = 0; i < produtos.size(); i++){
				this.produtosVendaService.rmvProduto(produtos.get(i));
				this.estoqueProdutosService.atualizaEstoque(produtos.get(i).getProduto());
			}
			this.vendaService.deleteVenda(venda);
			return new ResponseEntity<String>("Venda: "+ venda.getIdVenda() + " Deletada com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
