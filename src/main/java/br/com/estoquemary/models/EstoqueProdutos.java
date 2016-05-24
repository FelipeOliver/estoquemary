package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EstoqueProdutos {
	
	@Id @GeneratedValue
	private Long idEstoque;
	
	@OneToOne
	private Produto produto;
	
	private int qntdEstoque;
	private Double valorMedio;
	private int qntdComprada;
	private Double valorTotal;
	private int qntdVendida;
	private Double valorVendido;
	private Double valorMedioVendido;
	
	public EstoqueProdutos(){
		
	}
	public EstoqueProdutos(Produto produto){
		this.setProduto(produto);
		this.setQntdComprada(0);
		this.setQntdEstoque(0);
		this.setQntdVendida(0);
		this.valorMedio = 0.0;
		this.valorTotal = 0.0;
		this.valorVendido = 0.0;
		this.valorMedioVendido = 0.0;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Long getId_estoque() {
		return idEstoque;
	}
	public void setId_estoque(Long id_estoque) {
		this.idEstoque = id_estoque;
	}
	public int getQntdEstoque() {
		return qntdEstoque;
	}
	public void setQntdEstoque(int qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}
	public Double getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(Double valorMedio) {
		this.valorMedio = valorMedio;
	}
	public int getQntdComprada() {
		return qntdComprada;
	}
	public void setQntdComprada(int qntdComprada) {
		this.qntdComprada = qntdComprada;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getQntdVendida() {
		return qntdVendida;
	}
	public void setQntdVendida(int qntdVendida) {
		this.qntdVendida = qntdVendida;
	}
	public Double getValorVendido() {
		return valorVendido;
	}
	public void setValorVendido(Double valorVendido) {
		this.valorVendido = valorVendido;
	}
	public Double getValorMedioVendido() {
		return valorMedioVendido;
	}
	public void setValorMedioVendido(Double valorMedioVendido) {
		this.valorMedioVendido = valorMedioVendido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstoque == null) ? 0 : idEstoque.hashCode());
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
		EstoqueProdutos other = (EstoqueProdutos) obj;
		if (idEstoque == null) {
			if (other.idEstoque != null)
				return false;
		} else if (!idEstoque.equals(other.idEstoque))
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
		return "EstoqueProdutos [idEstoque=" + idEstoque + ", produto=" + produto + ", qntdEstoque=" + qntdEstoque
				+ ", valorMedio=" + valorMedio + ", qntdComprada=" + qntdComprada + ", valorTotal=" + valorTotal
				+ ", qntdVendida=" + qntdVendida + ", valorVendido=" + valorVendido + ", valorMedioVendido="
				+ valorMedioVendido + "]";
	}
	
	
}
