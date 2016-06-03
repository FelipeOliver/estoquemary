package br.com.estoquemary.repositories;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.estoquemary.builders.PedidoBuilder;
import br.com.estoquemary.models.Pedido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
@Transactional
public class PedidoRepositoryTest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Test
	public void testSavePedido(){
		Pedido pedido = new PedidoBuilder().buid();
		Pedido pedidoRetorno = this.pedidoRepository.save(pedido);
		Assert.assertNotNull("",pedidoRetorno);
	}
}