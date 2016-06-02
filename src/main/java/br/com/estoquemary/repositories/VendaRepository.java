package br.com.estoquemary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>{

}
