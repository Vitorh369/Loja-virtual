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

import br.com.projeto.loja.model.Funcionario;
import br.com.projeto.loja.repository.CidadeRepository;
import br.com.projeto.loja.repository.FuncionarioRepository;


@Controller
public class FuncionarioController{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping("/administrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv =  new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario",funcionario);
		mv.addObject("listaCidades", cidadeRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionarios/listar")//mostrar todos
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionarios/editar/{id}")//editar
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario>funcionario = funcionarioRepository.findById(id);
		return cadastrar(funcionario.get());
	}
	
	@GetMapping("/administrativo/funcionarios/remover/{id}")//remove
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario>funcionario = funcionarioRepository.findById(id);
		funcionarioRepository.delete(funcionario.get());
		return listar();
	}
	
	@PostMapping("/administrativo/funcionarios/salvar")//salvar
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {
		
		//System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}
		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
		funcionarioRepository.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());
	}
}

