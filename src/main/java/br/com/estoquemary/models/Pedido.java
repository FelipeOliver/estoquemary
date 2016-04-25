package br.com.estoquemary.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Pedido {
	@Id
	private Long cod_pedido;
	@ManyToMany
	@JoinColumn(name = "cod_produto")
	private List<Produto> produto;
	private String dt_entrega;
	private String dt_solicitacao;
	private Double valor_pago;
	private Double valor_imposto;
	public Long getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(Long cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public String getDt_entrega() {
		return dt_entrega;
	}
	
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public void setDt_entrega(String dt_entrega) {
		this.dt_entrega = dt_entrega;
	}
	public String getDt_solicitacao() {
		return dt_solicitacao;
	}
	public void setDt_solicitacao(String dt_solicitacao) {
		this.dt_solicitacao = dt_solicitacao;
	}
	public Double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(Double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public Double getValor_imposto() {
		return valor_imposto;
	}
	public void setValor_imposto(Double valor_imposto) {
		this.valor_imposto = valor_imposto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_pedido == null) ? 0 : cod_pedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (cod_pedido == null) {
			if (other.cod_pedido != null)
				return false;
		} else if (!cod_pedido.equals(other.cod_pedido))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pedido [cod_pedido=" + cod_pedido + ", produto=" + produto + ", dt_entrega=" + dt_entrega
				+ ", dt_solicitacao=" + dt_solicitacao + ", valor_pago=" + valor_pago + ", valor_imposto="
				+ valor_imposto + "]";
	}
	
}
