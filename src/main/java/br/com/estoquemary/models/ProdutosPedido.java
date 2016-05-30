package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"codPedido", "codProduto"})})
public class ProdutosPedido {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn(name="codPedido")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name="codProduto")
	private Produto produto;
	
	private Double valorPago;
	private int qntdProdutos;
	private Double valorDesconto;
	
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
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valor_pago) {
		this.valorPago = valor_pago;
	}
	public int getQntdProdutos() {
		return qntdProdutos;
	}
	public void setQntdProdutos(int qntd_produtos) {
		this.qntdProdutos = qntd_produtos;
	}
	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valor_desconto) {
		this.valorDesconto = valor_desconto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		return "ProdutosPedido [id=" + id + ", pedido=" + pedido + ", produto=" + produto + ", valorPago=" + valorPago
				+ ", qntdProdutos=" + qntdProdutos + ", valorDesconto=" + valorDesconto + "]";
	}
}