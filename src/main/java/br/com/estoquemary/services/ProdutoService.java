package br.com.estoquemary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto addProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	
}
