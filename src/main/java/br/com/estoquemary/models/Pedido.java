package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pedido {
	@Id
	private Long codPedido;
 
	private String dtEntrega;
	private String dtSolicitacao;
	private Double valorPago;
	private Double valorImposto;

	public Long getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}
	public String getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(String dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	public String getDtSolicitacao() {
		return dtSolicitacao;
	}
	public void setDtSolicitacao(String dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public Double getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(Double valorImposto) {
		this.valorImposto = valorImposto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPedido == null) ? 0 : codPedido.hashCode());
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
		if (codPedido == null) {
			if (other.codPedido != null)
				return false;
		} else if (!codPedido.equals(other.codPedido))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", dtEntrega=" + dtEntrega + ", dtSolicitacao=" + dtSolicitacao
				+ ", valorPago=" + valorPago + ", valorImposto=" + valorImposto + "]";
	}
	
}
