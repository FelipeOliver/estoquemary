package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.Pedido;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.repositories.ProdutosPedidoRepository;

@Service
public class ProdutosPedidoService {

	@Autowired
	private ProdutosPedidoRepository produtosPedidoRepository;
	
	public List<ProdutosPedido> addListProdutoPedidos(List<ProdutosPedido> list) {
		return (List<ProdutosPedido>) produtosPedidoRepository.save((Iterable<ProdutosPedido>)list);
	}

	public int countByProduto(Produto produto) {
		return this.produtosPedidoRepository.countByProduto(produto);
	}

	public List<ProdutosPedido> findByPedido(Pedido pedido) {
		return produtosPedidoRepository.findByPedido(pedido);
		
	}

	public void rmvProduto(ProdutosPedido pedidoProdutos) {
		this.produtosPedidoRepository.delete(pedidoProdutos);
	}

}
