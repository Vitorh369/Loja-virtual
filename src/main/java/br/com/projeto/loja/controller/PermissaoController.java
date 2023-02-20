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

import br.com.projeto.loja.model.Permissao;
import br.com.projeto.loja.repository.FuncionarioRepository;
import br.com.projeto.loja.repository.PapelRepository;
import br.com.projeto.loja.repository.PermissaoRepository;


@Controller
public class PermissaoController{
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv =  new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionario", funcionarioRepository.findAll());
		mv.addObject("listaPapel", papelRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/permissoes/listar")//mostrar todos
	public ModelAndView listar() {
		ModelAndView mv=new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/permissoes/editar/{id}")//editar
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao>permissao = permissaoRepository.findById(id);
		return cadastrar(permissao.get());
	}
	
	@GetMapping("/administrativo/permissoes/remover/{id}")//remove
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao>permissao = permissaoRepository.findById(id);
		permissaoRepository.delete(permissao.get());
		return listar();
	}
	
	@PostMapping("/administrativo/permissoes/salvar")//salvar
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {
		
		//System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return cadastrar(permissao);
		}
		
		permissaoRepository.saveAndFlush(permissao);
		return cadastrar(new Permissao());
	}
}

