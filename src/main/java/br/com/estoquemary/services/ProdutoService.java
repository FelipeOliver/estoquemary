package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.repositories.EstoqueProdutoRepository;
import br.com.estoquemary.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstoqueProdutoRepository estoqueProdutoRepository;
	
	public Produto addProduto(Produto produto) {
		Produto produto3 = produtoRepository.findOne(produto.getCod_produto());
		System.out.println("Aqui");
		if(produto3 != null)
			return produtoRepository.save(produto);
		else{
			EstoqueProdutos estoque = new EstoqueProdutos(produto);
			estoque = estoqueProdutoRepository.save(estoque);
			
			Produto produto2 = produtoRepository.save(produto);
				
			return produto2;
		}
    }

	public List<Produto> findAll() {
		return (List<Produto>) produtoRepository.findAll();
	}
}
