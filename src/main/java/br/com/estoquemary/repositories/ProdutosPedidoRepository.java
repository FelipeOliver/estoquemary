package br.com.estoquemary.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoquemary.models.Pedido;
import br.com.estoquemary.models.Produto;
import br.com.estoquemary.models.ProdutosPedido;

@Repository
public interface ProdutosPedidoRepository extends CrudRepository<ProdutosPedido, Long>{

	public int countByProduto(Produto produto);

	public List<ProdutosPedido> findByPedido(Pedido codPedido);

}
