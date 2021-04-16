package br.com.blog.springbootapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import br.com.blog.springbootapp.entity.*;
import br.com.blog.springbootapp.repository.*;
import br.com.blog.springbootapp.service.*;

import com.fasterxml.jackson.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> buscarTodos() {
        
        return this.usuarioService.buscarTodosUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Integer id) {

        return this.usuarioService.buscarUsuarioPorId(id);
    }

    @PostMapping
    public Usuario cadastrarNovoUsuario(@RequestBody Usuario usuario) {

        return this.usuarioService.criarUsuario(usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }

    @JsonView(View.PublicacoesUsuario.class)
    @GetMapping(value = "/{usuario}/publicacao")
    public Set<Publicacao> buscarPublicacaoPeloUsuario(@PathVariable("usuario") Integer id) {

        return usuarioService.buscarPublicacoesPeloUsuario(id);
    }

    @JsonView(View.CadastroPublicacao.class)
    @PostMapping(value = "/publicacao")
    public Publicacao cadastrarNovaPublicacao(@RequestBody Publicacao publicacao) {

        return this.usuarioService.criarPublicacao(publicacao, this.usuarioService.buscarUsuarioPorId(publicacao.getUsuario().getId()));
    }

    @JsonView(View.PublicacoesUsuario.class)
    @PutMapping(value = "/publicacao")
    public Publicacao atualizarPublicacao(@RequestBody Publicacao publicacaoUpdate) {
        Publicacao publicacao = usuarioService.buscarPublicacaoPeloId(publicacaoUpdate.getId());
        
        if(publicacaoUpdate.getTitulo() != null) {
            publicacao.setTitulo(publicacaoUpdate.getTitulo());
        }

        if(publicacaoUpdate.getConteudo() != null) {
            publicacao.setConteudo(publicacaoUpdate.getConteudo());
        }

        return usuarioService.atualizarPublicacao(publicacao);
    }

    @DeleteMapping(value = "/publicacao")
    public Boolean excluirPublicacao(@RequestBody Publicacao publicacao) {

        return usuarioService.excluirPublicacao(usuarioService.buscarPublicacaoPeloId(publicacao.getId()));
    }

    @GetMapping(value = "/{usuario}/tag")
    public Set<Tag> buscarTagsPeloUsuario(@PathVariable("usuario") Integer id) {

        return usuarioService.buscarTagsPeloUsuario(id);
    }

    @PostMapping(value = "tag")
    public Tag cadastrarNovaTag(@RequestBody Tag tag) {

        return this.usuarioService.criarTag(tag.getNome(), this.usuarioService.buscarUsuarioPorId(tag.getUsuario().getId()));
    }
}