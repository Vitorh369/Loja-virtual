package br.com.projeto.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.loja.model.Cidade;

@Controller
public class NegadoController {

	@GetMapping("/negadoAdministrativo")
	public ModelAndView negadoAdministrativo() {
		ModelAndView mv =  new ModelAndView("/negadoAdministrativo");
		
		return mv;
	}
	
	@GetMapping("/negadoCliente")
	public ModelAndView negadoCliente() {
		ModelAndView mv =  new ModelAndView("/negadoCliente");
		
		return mv;
	}
	
}
