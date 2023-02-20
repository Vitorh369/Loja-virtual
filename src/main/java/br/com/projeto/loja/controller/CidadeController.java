package br.com.projeto.loja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.loja.model.Cidade;
import br.com.projeto.loja.repository.CidadeRepository;
import br.com.projeto.loja.repository.EstadoRepository;


@Controller
public class CidadeController{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping("/administrativo/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv =  new ModelAndView("administrativo/cidades/cadastro");
		mv.addObject("cidade", cidade);
		mv.addObject("listaEstados", estadoRepository.findAll());	
		return mv;
	}
	
	@GetMapping("/administrativo/cidades/listar")//mostrar todos
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/cidades/lista");
		mv.addObject("listaCidades", cidadeRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/cidades/editar/{id}")//editar
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade>cidade = cidadeRepository.findById(id);
		return cadastrar(cidade.get());
	}
	
	@GetMapping("/administrativo/cidades/remover/{id}")//remove
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade>cidade = cidadeRepository.findById(id);
		cidadeRepository.delete(cidade.get());
		return listar();
	}
	
	@PostMapping("/administrativo/cidades/salvar")//salvar
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {
		
		//System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return cadastrar(cidade);
		}
		
		cidadeRepository.saveAndFlush(cidade);
		return cadastrar(new Cidade());
	}
}

