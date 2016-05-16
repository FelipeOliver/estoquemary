package br.com.estoquemary.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Cliente {
	
	@Id @GeneratedValue
	private Long idCliente;
	
	private String nome;
	private String dataAniversario;
	
	@JsonIgnore
	@OneToMany
	private List<Endereco> enderecos;
	
	@JsonIgnore
	@OneToMany
	private List<Telefone> telefones;

	private String email;
	private String anfitria;
	
	private String flagWhatsapp;
	
//	@Enumerated(EnumType.STRING)
	private String tipoPele;
	private String corBase;
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAnfitria() {
		return anfitria;
	}
	public void setAnfitria(String anfitria) {
		this.anfitria = anfitria;
	}
	public String getFlagWhatsapp() {
		return flagWhatsapp;
	}
	public void setFlagWhatsapp(String flagWhatsapp) {
		this.flagWhatsapp = flagWhatsapp;
	}
	public String getTipoPele() {
		return tipoPele;
	}
	public void setTipoPele(String tipoPele) {
		this.tipoPele = tipoPele;
	}
	public String getCorBase() {
		return corBase;
	}
	public void setCorBase(String corBase) {
		this.corBase = corBase;
	}

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
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", dataAniversario=" + dataAniversario
				//+ ", enderecos=" + enderecos + ", telefones=" + telefones 
				+ ", email=" + email + ", anfitria="
				+ anfitria + ", flagWhatsapp=" + flagWhatsapp + ", tipoPele=" + tipoPele + ", corBase=" + corBase + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}
	
	
}	
