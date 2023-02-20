package br.com.projeto.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.loja.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
}
