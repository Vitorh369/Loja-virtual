package br.com.projeto.loja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.loja.model.Cliente;
import br.com.projeto.loja.repository.CidadeRepository;
import br.com.projeto.loja.repository.ClienteRepository;


@Controller
public class ClienteController{
	
	@Autowired
	private ClienteRepository clienteRpository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	

	@GetMapping("/cliente/cadastrar")
	public ModelAndView cadastrar(Cliente cliente) {
		ModelAndView mv =  new ModelAndView("cliente/cadastrar");
		mv.addObject("cliente",cliente);
		mv.addObject("listaCidade",cidadeRepository.findAll());
		return mv;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = clienteRpository.findById(id);
		return cadastrar(cliente.get());
	}

	
	@PostMapping("/cliente/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(cliente);
		}
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		clienteRpository.saveAndFlush(cliente);
		
		return cadastrar(new Cliente());
	}

}

