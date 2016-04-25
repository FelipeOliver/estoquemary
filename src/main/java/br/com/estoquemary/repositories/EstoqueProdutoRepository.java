package br.com.estoquemary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.EstoqueProdutos;

@Repository
public interface EstoqueProdutoRepository extends CrudRepository<EstoqueProdutos, Long>{

}
