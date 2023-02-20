package br.com.projeto.loja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.loja.model.EntradaItens;
import br.com.projeto.loja.model.EntradaProduto;
import br.com.projeto.loja.model.Produto;
import br.com.projeto.loja.repository.EntradaItensRepository;
import br.com.projeto.loja.repository.EntradaProdutoRepository;
import br.com.projeto.loja.repository.FuncionarioRepository;
import br.com.projeto.loja.repository.ProdutoRepository;

@Controller
public class EntradaProdutoController {

	@Controller
	public class EntradaProdutoControle {

		private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();

		@Autowired
		private EntradaItensRepository entradaItensRepository;
		
		@Autowired
		private EntradaProdutoRepository entradaProdutoRepository;

		@Autowired
		private FuncionarioRepository funcionarioRepository;

		@Autowired
		private ProdutoRepository produtoRepository;

		@GetMapping("/administrativo/entrada/cadastrar")
		public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
			ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
			mv.addObject("entrada", entrada);
			mv.addObject("listaEntradaItens", this.listaEntrada);
			mv.addObject("entradaItens", entradaItens);
			mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
			mv.addObject("listaProdutos", produtoRepository.findAll());
			return mv;
		}

//	@GetMapping("/administrativo/estados/listar")//mostrar todos
//	public ModelAndView listar() {
//		ModelAndView mv=new ModelAndView("administrativo/estados/lista");
//		mv.addObject("listaEstados", entradaRepository.findAll());
//		return mv;
//	}

//	@GetMapping("/administrativo/estados/editar/{id}")//editar
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<Estado>estado = entradaRepository.findById(id);
//		return cadastrar(estado.get());
//	}

//	@GetMapping("/administrativo/estados/remover/{id}")//remove
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Estado>estado = entradaRepository.findById(id);
//		entradaRepository.delete(estado.get());
//		return listar();
//	}

		@PostMapping("/administrativo/entrada/salvar")
		public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {

			if (acao.equals("itens")) {
				this.listaEntrada.add(entradaItens);
				}else if(acao.equals("salvar")) {
					entradaProdutoRepository.saveAndFlush(entrada);
					for(EntradaItens it : listaEntrada) {
						it.setEntrada(entrada);
						entradaItensRepository.saveAndFlush(it);
						Optional<Produto>prod = produtoRepository.findById(it.getProduto().getId());
						Produto produto = prod.get();
						produto.setQuantidadeEstoque(it.getValorVenda());
						produto.setValorVenda(it.getValorProduto());
						this.listaEntrada = new ArrayList<>();
					}
					return cadastrar( new EntradaProduto(), new EntradaItens());
				}
			

			return cadastrar(entrada, new EntradaItens());
		}

	}
}









