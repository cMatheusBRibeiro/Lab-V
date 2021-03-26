package br.com.blog.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blog.springbootapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    public Usuario findByLoginAndSenha(String login, String senha);

}
