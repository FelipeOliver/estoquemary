package br.com.estoquemary.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.estoquemary.models.Pedido;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.services.PedidoService;
import br.com.estoquemary.services.ProdutoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping("/add")
	public ModelAndView pagePedido(){
		ModelAndView mv = new ModelAndView("index");
		List<Produto> list = produtoService.findAll();
		mv.addObject("produtosDisponiveis", new Gson().toJson(list));
		mv.addObject("pagina", "pedido-add.jsp");
		mv.addObject("escolhido", "Pedido");
		return mv;
	}
	
	@RequestMapping(value="/addPedido")
	public ResponseEntity<String> addPedido(String teste){
		try{
			Pedido pedido = new Pedido();
			System.out.println("Chegou aqui "+ teste);
			JSONObject obj = new JSONObject(teste);
			pedido.setCod_pedido(obj.getLong("cod_pedido"));
			pedido.setDt_entrega(obj.getString("dt_entrega"));
			pedido.setDt_solicitacao(obj.getString("dt_solicitacao"));
			pedido.setValor_imposto(obj.isNull("valor_imposto")? 0.0 : obj.getDouble("valor_imposto"));
			pedido.setValor_pago(obj.getDouble("valor_pago"));
			JSONArray array = obj.getJSONArray("produto");
			List<Produto> produtos = new ArrayList<Produto>();
			for(int i = 0; i < array.length(); i++){
				Produto produto = new Produto();
				JSONObject o = array.getJSONObject(i);
				produto.setCod_produto(o.getLong("cod_produto"));
				produto.setDescricao(o.getString("descricao"));
				produto.setPontuacao(o.getInt("pontuacao"));
				produto.setValor_venda(o.getDouble("pontuacao"));
				produtos.add(produto);
			}
			pedido.setProduto(produtos);
			System.out.println(pedido);
			
			Pedido p = pedidoService.addPedido(pedido);
			return new ResponseEntity<String>("Pedido: "+ p.getCod_pedido() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
