package br.com.estoquemary.controllers;


import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.estoquemary.models.Cliente;
import br.com.estoquemary.services.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/")
	public ModelAndView getHome(){
		ModelAndView mv = new ModelAndView("index");
		List<Cliente> clientes = this.clienteService.findAll();
		mv.addObject("pagina", "cliente/cliente-add.jsp");
		mv.addObject("clientes", new Gson().toJson(clientes));
		mv.addObject("escolhido", "Cliente");
		return mv;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ResponseEntity<String> addCliente(Cliente cliente){
		try{
			System.out.println("Chegou aqui");
			Cliente clienteSalvo = clienteService.save(cliente);
			return new ResponseEntity<String>("Cliente: "+ clienteSalvo.getIdCliente() + " Salvo com Sucesso", HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/findByName/{nome}")
	public ResponseEntity<String> findByName(@PathVariable String nome){
		try{
			System.out.println("Chegou aqui: " + nome);
			List<Cliente> clientes = clienteService.findByName(nome);
			System.out.println(clientes.toString() + ' ' + HttpStatus.OK);
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(clientes), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
