package br.com.projeto.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.loja.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
}
