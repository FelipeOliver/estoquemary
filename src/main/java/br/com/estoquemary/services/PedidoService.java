package br.com.estoquemary.services;

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

}
