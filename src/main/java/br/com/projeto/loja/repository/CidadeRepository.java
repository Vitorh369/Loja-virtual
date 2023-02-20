package br.com.projeto.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.loja.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
}
