package br.com.estoquemary.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosVenda;
import br.com.estoquemary.models.Venda;

@Repository
public interface ProdutosVendaRepository extends CrudRepository<ProdutosVenda, Long>{

	List<ProdutosVenda> findByProduto(Produto produto);

	List<ProdutosVenda> findByVenda(Venda venda);

}
