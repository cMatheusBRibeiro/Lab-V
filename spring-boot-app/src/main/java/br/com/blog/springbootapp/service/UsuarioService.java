package br.com.blog.springbootapp.service;

import br.com.blog.springbootapp.entity.*;
import java.util.*;

public interface UsuarioService {

    public Usuario criarUsuario(String nome, String login, String senha);

    public Boolean alterarSenha(Usuario usuario, String novaSenha);

    public Set<Tag> buscarTagsUsadasPeloUsuario(Usuario usuario);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Integer id);

    public Publicacao criarPublicacao(Publicacao publicacao, Usuario usuario);

    public Publicacao buscarPublicacaoPeloId(Integer id);

    public Publicacao atualizarPublicacao(Publicacao publicacao);
    
    public Boolean excluirPublicacao(Publicacao publicacao);

    public Set<Publicacao> buscarPublicacoesPeloUsuario(Integer id);

    public Tag criarTag(String nome, Usuario usuario);

    public Set<Tag> buscarTagsPeloUsuario(Integer id);
}