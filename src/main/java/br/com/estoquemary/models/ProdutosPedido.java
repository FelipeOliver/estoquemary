package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity 
public class ProdutosPedido {

	@Id @GeneratedValue
	private Long id;
	
//	@ManyToMany
//	@JoinColumn(name = "cod_pedido")
//	private List<Pedido> pedido;
	
	@OneToOne
	private Pedido pedido;
	
	@OneToOne
	private Produto produto;
	
	private Double valor_pago;
	private int qntd_produtos;
	private Double valor_desconto;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Pedido getPedido() {
		return this.pedido;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutosPedido other = (ProdutosPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProdutosPedido [id=" + id + ", pedido=" + pedido + ", produto=" + produto + ", valor_pago=" + valor_pago
				+ ", qntd_produtos=" + qntd_produtos + ", valor_desconto=" + valor_desconto + "]";
	}
}