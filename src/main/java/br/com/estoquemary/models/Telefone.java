package br.com.estoquemary.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Telefone {

	@Id
	private int numero;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	
	private String whatsapp;
	private String operadora;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	
	@Override
	public String toString() {
		return "Telefone [numero=" + numero + ", whatsapp=" + whatsapp + ", operadora=" + operadora + "]";
	}
	
	
}
