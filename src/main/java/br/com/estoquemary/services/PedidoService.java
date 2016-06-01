package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.Pedido;
import br.com.estoquemary.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido addPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido findOne(String codPedido) {
		return this.pedidoRepository.findOne(Long.parseLong(codPedido));
	}

	public List<Pedido> findAll() {
		return (List<Pedido>) this.pedidoRepository.findAll();
	}

	public void deletePedido(Pedido pedido) {
		this.pedidoRepository.delete(pedido);
	}

}
