package br.com.estoquemary.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.ProdutoService;

@Controller
public class HomeController {
	
	@Autowired
	ProdutoService service;
	
	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("index");
		List<Produto> list = service.findAll();
		System.out.println(list.toString());
		mv.addObject("produtos", list);
		mv.addObject("pagina", "produto-add.jsp");
		mv.addObject("escolhido", "Produto");
		return mv;
	}
}
