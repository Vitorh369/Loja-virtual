package br.com.projeto.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.loja.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
}
