package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private Long codProduto;
	
	private String descricao;
	private int pontuacao;
	private double valorVenda;
	
	public Long getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(Long cod_produto) {
		this.codProduto = cod_produto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valor_venda) {
		this.valorVenda = valor_venda;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProduto == null) ? 0 : codProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", descricao=" + descricao + ", pontuacao=" + pontuacao
				+ ", valorVenda=" + valorVenda + "]";
	}
}
