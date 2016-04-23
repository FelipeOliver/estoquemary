package br.com.estoquemary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
	
}
