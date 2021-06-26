package br.com.blog.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blog.springbootapp.entity.HistoricoSenha;

public interface HistoricoSenhaRepository extends JpaRepository<HistoricoSenha, Integer> {
    
}
