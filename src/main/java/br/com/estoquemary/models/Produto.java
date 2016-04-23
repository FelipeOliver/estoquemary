package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private Long cod_produto;
	private String descricao;
	private int pontuacao;
	private double valor_venda;
	private double valor_compra;
	private double valor_desconto;
	
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
	public double getValor_compra() {
		return valor_compra;
	}
	public void setValor_compra(double valor_compra) {
		this.valor_compra = valor_compra;
	}
	public double getValor_desconto() {
		return valor_desconto;
	}
	public void setValor_desconto(double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}
	
}
