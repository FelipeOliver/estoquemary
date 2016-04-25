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
	
	@Autowired
	private EstoqueProdutosService estoqueProdutosService;
	
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
			JSONObject obj = new JSONObject(teste);
			pedido.setCod_pedido(obj.getLong("cod_pedido"));
			pedido.setDt_entrega(obj.getString("dt_entrega"));
			pedido.setDt_solicitacao(obj.getString("dt_solicitacao"));
			pedido.setValor_imposto(obj.isNull("valor_imposto")? 0.0 : obj.getDouble("valor_imposto"));
			pedido.setValor_pago(obj.getDouble("valor_pago"));
			
			JSONArray array = obj.getJSONArray("produto");
			List<ProdutosPedido> produtos = new ArrayList<ProdutosPedido>();
			for(int i = 0; i < array.length(); i++){
				ProdutosPedido produto = new ProdutosPedido();
				JSONObject o = array.getJSONObject(i);
				Produto prod = new Produto();
				prod.setCod_produto(o.getLong("cod_produto"));
				produto.setProduto(prod);
				System.out.println("Chegou aqui 2: " + prod.getCod_produto());
				Pedido ped = new Pedido();
				ped.setCod_pedido(obj.getLong("cod_pedido"));
				produto.setPedido(ped);
				
				produto.setQntd_produtos(o.getInt("qntd_produtos"));
				produto.setValor_pago(o.getDouble("valor_pago"));
				//produto.setValor_desconto(o.getDouble("valor_desconto"));
				produtos.add(produto);
			}
			estoqueProdutosService.insereProdutosEstoque(produtos);
			Pedido p = pedidoService.addPedido(pedido);
			produtosPedidoService.addListProdutoPedidos(produtos);
			
			return new ResponseEntity<String>("Pedido: "+ p.getCod_pedido() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
