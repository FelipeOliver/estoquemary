package br.com.estoquemary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.EstoqueProdutosService;

@Controller
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private EstoqueProdutosService estoqueProdutosService;
	
	@RequestMapping("/")
	public ModelAndView getHome(){
		ModelAndView mv = new ModelAndView("index");
		List<Produto> lp = estoqueProdutosService.findByQntdEstoque();
		System.out.println(lp.toString());
		mv.addObject("produtosDisponiveis", new Gson().toJson(lp));
		mv.addObject("pagina", "venda-add.jsp");
		mv.addObject("escolhido", "Venda");
		return mv;
	}
}
