package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Venda {

	@Id @GeneratedValue
	private Long idVenda;
	
	@OneToOne
	private Cliente cliente;
	
	private String dataVenda;
	private String formaPagamento;
	private double valorTotalVenda;
	private String status;
	public Long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Long id_venda) {
		this.idVenda = id_venda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String data_venda) {
		this.dataVenda = data_venda;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String forma_pagamento) {
		this.formaPagamento = forma_pagamento;
	}
	public double getValorTotalVenda() {
		return valorTotalVenda;
	}
	public void setValorTotalVenda(double valor_total_venda) {
		this.valorTotalVenda = valor_total_venda;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenda == null) ? 0 : idVenda.hashCode());
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
		Venda other = (Venda) obj;
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", cliente=" + cliente + ", dataVenda=" + dataVenda + ", formaPagamento="
				+ formaPagamento + ", valorTotalVenda=" + valorTotalVenda + ", status=" + status + "]";
	}
}
