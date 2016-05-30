package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"idVenda","codProduto"}))
public class ProdutosVenda {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn(name="idVenda")
	private Venda venda;
	
	@OneToOne
	@JoinColumn(name="codProduto")
	private Produto produto;
	
	private int qntdProdutos;
	private double valorVendido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQntdProdutos() {
		return qntdProdutos;
	}
	public void setQntdProdutos(int qntd_produtos) {
		this.qntdProdutos = qntd_produtos;
	}
	public double getValorVendido() {
		return valorVendido;
	}
	public void setValorVendido(double valor_vendido) {
		this.valorVendido = valor_vendido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ProdutosVenda other = (ProdutosVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ProdutosVenda [id=" + id + ", venda=" + venda + ", produto=" + produto + ", qntdProdutos="
				+ qntdProdutos + ", valorVendido=" + valorVendido + "]";
	}
}
