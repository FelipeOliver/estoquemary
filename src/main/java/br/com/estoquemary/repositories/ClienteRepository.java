package br.com.estoquemary.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
