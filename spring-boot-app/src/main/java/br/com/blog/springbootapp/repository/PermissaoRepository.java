package br.com.blog.springbootapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blog.springbootapp.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
    
    public Permissao findByTitulo(String titulo);
}
