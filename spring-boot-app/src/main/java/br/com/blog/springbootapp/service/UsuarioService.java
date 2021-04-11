package br.com.blog.springbootapp.service;

import java.util.Set;

import br.com.blog.springbootapp.entity.Tag;
import br.com.blog.springbootapp.entity.Usuario;
import java.util.List;

public interface UsuarioService {

    public Usuario criarUsuario(String nome, String login, String senha);

    public Boolean alterarSenha(Usuario usuario, String novaSenha);

    public Set<Tag> buscarTagsUsadasPeloUsuario(Usuario usuario);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Integer id);
}