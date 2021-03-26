package br.com.blog.springbootapp.service;

import br.com.blog.springbootapp.entity.Usuario;

public interface UsuarioService {

    public Usuario criarUsuario(String nome, String login, String senha);

    public Boolean alterarSenha(Usuario usuario, String novaSenha);
}