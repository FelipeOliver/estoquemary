package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.Cliente;
import br.com.estoquemary.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findByName(String nome) {
		return clienteRepository.findByNomeContainingIgnoreCase(nome);
	}

	public List<Cliente> findAll() {
		return (List<Cliente>) this.clienteRepository.findAll();
	}

}
