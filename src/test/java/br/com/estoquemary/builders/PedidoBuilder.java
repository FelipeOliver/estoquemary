package br.com.estoquemary.builders;

import br.com.estoquemary.models.Pedido;

public class PedidoBuilder {
	
	private Long codPedido;
	private String dtEntrega;
	private String dtSolicitacao;
	private double valorImposto;
	private double valorPago;
	
	public PedidoBuilder(){
		this.codPedido = Math.round(Math.random());
		this.dtEntrega = "01/01/2001";
		this.dtSolicitacao = "01/01/2001";
		this.valorImposto = 0.0;
		this.valorPago = 169.9;
	}
	
	public Pedido buid(){
		Pedido pedido = new Pedido();
		pedido.setCodPedido(this.codPedido);
		pedido.setDtEntrega(this.dtEntrega);
		pedido.setDtSolicitacao(this.dtSolicitacao);
		pedido.setValorImposto(this.valorImposto);
		pedido.setValorPago(this.valorPago);
		return pedido;
	}
	
	public PedidoBuilder withCodPedido(Long codPedido){
		this.codPedido = codPedido;
		return this;
	}
	
	public PedidoBuilder withDtEntrega(String dtEntrega){
		this.dtEntrega = dtEntrega;
		return this;
	}
	
	public PedidoBuilder withDtSolicitacao(String dtSolicitacao){
		this.dtSolicitacao = dtSolicitacao;
		return this;
	}
	
	public PedidoBuilder withValorImposto(double valorImposto){
		this.valorImposto = valorImposto;
		return this;
	}
	
	public PedidoBuilder withValorPago(double valorPago){
		this.valorPago = valorPago;
		return this;
	}
}
