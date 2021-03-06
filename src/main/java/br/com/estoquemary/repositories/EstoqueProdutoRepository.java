package br.com.estoquemary.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.EstoqueProdutos;
import br.com.estoquemary.models.Produto;

@Repository
public interface EstoqueProdutoRepository extends CrudRepository<EstoqueProdutos, Long>{

	
	EstoqueProdutos findByProduto(Produto produto);
	
	List<EstoqueProdutos> findByQntdEstoqueIsGreaterThan(int qntd_estoque);

	List<EstoqueProdutos> deleteByProduto(Produto produto);

	List<EstoqueProdutos> findByQntdEstoqueIsLessThanEqualAndQntdCompradaIsGreaterThan(int i, int j);

	List<EstoqueProdutos> findByQntdEstoqueEquals(int i);

}
