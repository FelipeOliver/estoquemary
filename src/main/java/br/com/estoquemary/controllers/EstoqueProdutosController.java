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
		mv.addObject("pagina", "produto/estoque-consulta.jsp");
		try{
			List<EstoqueProdutos> list =  estoqueProdutosService.findAll();
			mv.addObject("produtos", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.addObject("Escolhido", "Estoque");
		return mv;
	}
}
