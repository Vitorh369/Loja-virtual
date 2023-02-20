package br.com.projeto.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.loja.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
