package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.ProdutosPedido;
import br.com.estoquemary.repositories.ProdutosPedidoRepository;

@Service
public class ProdutosPedidoService {

	@Autowired
	private ProdutosPedidoRepository produtosPedidoRepository;
	
	public List<ProdutosPedido> addListProdutoPedidos(List<ProdutosPedido> list) {
		return (List<ProdutosPedido>) produtosPedidoRepository.save((Iterable<ProdutosPedido>)list);
	}

}
