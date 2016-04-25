package br.com.estoquemary.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EstoqueProdutos {
	
	@Id @GeneratedValue
	private Long id_estoque;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	private int qntd_estoque;
	private Double valor_medio;
	private int qntd_comprada;
	private Double valor_total;
	private int qntd_vendida;
	private Double valor_vendido;
	
	public EstoqueProdutos(){
		
	}
	public EstoqueProdutos(Produto produto){
		this.setProduto(produto);
		this.setQntd_comprada(0);
		this.setQntd_estoque(0);
		this.setQntd_vendida(0);
		this.valor_medio = 0.0;
		this.valor_total = 0.0;
		this.valor_vendido = 0.0;
	}
	
	public Long getId() {
		return id_estoque;
	}
	public void setId(Long id) {
		this.id_estoque = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQntd_estoque() {
		return qntd_estoque;
	}
	public void setQntd_estoque(int qntd_estoque) {
		this.qntd_estoque = qntd_estoque;
	}
	public Double getValor_medio() {
		return valor_medio;
	}
	public void setValor_medio(Double valor_medio) {
		this.valor_medio = valor_medio;
	}
	public int getQntd_comprada() {
		return qntd_comprada;
	}
	public void setQntd_comprada(int qntd_comprada) {
		this.qntd_comprada = qntd_comprada;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public int getQntd_vendida() {
		return qntd_vendida;
	}
	public void setQntd_vendida(int qntd_vendida) {
		this.qntd_vendida = qntd_vendida;
	}
	public Double getValor_vendido() {
		return valor_vendido;
	}
	public void setValor_vendido(Double valor_vendido) {
		this.valor_vendido = valor_vendido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_estoque == null) ? 0 : id_estoque.hashCode());
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
		if (id_estoque == null) {
			if (other.id_estoque != null)
				return false;
		} else if (!id_estoque.equals(other.id_estoque))
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
		return "EstoqueProdutos [id_estoque=" + id_estoque + ", qntd_estoque=" + qntd_estoque
				+ ", valor_medio=" + valor_medio + ", qntd_comprada=" + qntd_comprada + ", valor_total=" + valor_total
				+ ", qntd_vendida=" + qntd_vendida + ", valor_vendido=" + valor_vendido + "]";
	}
	
	
}
