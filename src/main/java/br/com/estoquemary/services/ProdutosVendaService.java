package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.ProdutosVenda;
import br.com.estoquemary.models.Venda;
import br.com.estoquemary.repositories.ProdutosVendaRepository;

@Service
public class ProdutosVendaService {

	@Autowired
	private ProdutosVendaRepository produtosVendaRepository;

	public List<ProdutosVenda> findByVenda(Venda venda) {
		return this.produtosVendaRepository.findByVenda(venda);
	}

	public List<ProdutosVenda> addListProdutoPedidos(List<ProdutosVenda> list) {
		return (List<ProdutosVenda>) this.produtosVendaRepository.save((Iterable<ProdutosVenda>)list);
	}

	public void rmvProduto(ProdutosVenda produtosVenda) {
		this.produtosVendaRepository.delete(produtosVenda);
	}

}
