package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.services.EstoqueProdutosService;

@Controller
@RequestMapping("/estoque")
public class EstoqueProdutosController {

	@Autowired
	private EstoqueProdutosService estoqueProdutosService;
	
	@RequestMapping("/")
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pagina", "produto/estoque-principal.jsp");
		mv.addObject("Escolhido", "Estoque");
		return mv;
	}
	
	@RequestMapping("/emestoque")
	public ModelAndView emEstoque(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pagina", "produto/estoque-consulta.jsp");
		mv.addObject("momento","estoque");
		try{
			List<EstoqueProdutos> list =  estoqueProdutosService.findByQntdEstoqueReturnEstoque();
			mv.addObject("produtos", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.addObject("Escolhido", "Estoque");
		return mv;
	}
	
	@RequestMapping("/emfalta")
	public ModelAndView emFalta(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pagina", "produto/estoque-consulta.jsp");
		mv.addObject("momento","falta");
		try{
			List<EstoqueProdutos> list =  estoqueProdutosService.findByQntdEstoqueAndQntdCompradaReturnEstoque();
			mv.addObject("produtos", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.addObject("Escolhido", "Estoque");
		return mv;
	}
	
	@RequestMapping("/naocomprado")
	public ModelAndView naoComprado(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pagina", "produto/estoque-consulta.jsp");
		mv.addObject("momento","compra");
		try{
			List<EstoqueProdutos> list =  estoqueProdutosService.findByQntdEstoqueNaoComprado();
			mv.addObject("produtos", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.addObject("Escolhido", "Estoque");
		return mv;
	}
}
