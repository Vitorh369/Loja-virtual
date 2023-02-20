package br.com.projeto.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalConrtroller {

	@GetMapping("/administrativo")
	public String AcessarPrincipal() {
		return"administrativo/home";
	}
}
