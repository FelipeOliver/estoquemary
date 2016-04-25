package br.com.estoquemary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
