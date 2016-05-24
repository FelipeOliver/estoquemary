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
		Produto produto3 = produtoRepository.findOne(produto.getCodProduto());
		if(produto3 != null){
			EstoqueProdutos estoque2 = this.estoqueProdutoRepository.findByProduto(produto3);
			if(estoque2 == null){
				estoque2 = new EstoqueProdutos(produto3);
				estoque2 = estoqueProdutoRepository.save(estoque2);
			}
			return produtoRepository.save(produto3);
		}
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

	public void removeProduto(Produto Produto) {
		produtoRepository.delete(Produto);
	}

	public Produto findOne(Long id) {
		return this.produtoRepository.findOne(id);
	}
}
