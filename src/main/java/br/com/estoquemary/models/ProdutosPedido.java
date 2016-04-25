package br.com.estoquemary.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity 
public class ProdutosPedido {

	@Id @GeneratedValue
	private Long id;
	
	@ManyToMany
	@JoinColumn(name = "cod_pedido")
	private List<Pedido> pedido;
	
	private Double valor_pago;
	private int qntd_produtos;
	private Double valor_desconto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public List<Produto> getProduto() {
//		return produto;
//	}
//	public void setProduto(List<Produto> produto) {
//		this.produto = produto;
//	}
	public List<Pedido> getPedido() {
		return pedido;
	}
	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}
	public Double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(Double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public int getQntd_produtos() {
		return qntd_produtos;
	}
	public void setQntd_produtos(int qntd_produtos) {
		this.qntd_produtos = qntd_produtos;
	}
	public Double getValor_desconto() {
		return valor_desconto;
	}
	public void setValor_desconto(Double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}
}