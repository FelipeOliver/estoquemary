package br.com.estoquemary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.Produto;

@Repository
public interface EstoqueProdutoRepository extends CrudRepository<EstoqueProdutos, Long>{

	EstoqueProdutos findByProduto(Produto produto);

}
