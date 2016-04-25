package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Produto {
	
	@Id
	private Long cod_produto;
	
	private String descricao;
	private int pontuacao;
	private double valor_venda;
	
//	@OneToOne @JoinColumn(name="id_estoque")
//	private EstoqueProdutos estoque;
//	private double valor_compra;
//	private double valor_desconto;
	
//	public EstoqueProdutos getEstoque() {
//		return estoque;
//	}
//	public void setEstoque(EstoqueProdutos estoque) {
//		this.estoque = estoque;
//	}
	public Long getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(Long cod_produto) {
		this.cod_produto = cod_produto;
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
	public double getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(double valor_venda) {
		this.valor_venda = valor_venda;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_produto == null) ? 0 : cod_produto.hashCode());
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
		if (cod_produto == null) {
			if (other.cod_produto != null)
				return false;
		} else if (!cod_produto.equals(other.cod_produto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Produto [cod_produto=" + cod_produto + ", descricao=" + descricao + ", pontuacao=" + pontuacao
				+ ", valor_venda=" + valor_venda + "]";
	}

}
