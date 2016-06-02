package br.com.estoquemary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoquemary.models.Venda;
import br.com.estoquemary.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	public List<Venda> findAll() {
		return (List<Venda>) this.vendaRepository.findAll();
	}

	public Venda save(Venda venda) {
		return this.vendaRepository.save(venda); 
	}

	public Venda findOne(String idVenda) {
		return this.vendaRepository.findOne(Long.parseLong(idVenda));
	}
	
	
}
