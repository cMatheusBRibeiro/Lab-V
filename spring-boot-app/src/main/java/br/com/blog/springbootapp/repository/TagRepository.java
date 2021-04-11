package br.com.blog.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blog.springbootapp.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    
    public Tag findByNome(String nome);
}
