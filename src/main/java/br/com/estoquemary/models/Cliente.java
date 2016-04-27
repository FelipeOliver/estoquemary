package br.com.estoquemary.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id @GeneratedValue
	private Long idCliente;
	
	private String nome;
	private String dataAniversario;

	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long id_cliente) {
		this.idCliente = id_cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(String data_aniversario) {
		this.dataAniversario = data_aniversario;
	}
	
	
}	
