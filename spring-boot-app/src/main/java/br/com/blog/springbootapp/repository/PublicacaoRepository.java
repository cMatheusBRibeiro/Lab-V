package br.com.blog.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blog.springbootapp.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
    
}
