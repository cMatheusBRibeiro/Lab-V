package br.com.blog.springbootapp.service;

import java.time.LocalDateTime;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.springbootapp.entity.*;
import br.com.blog.springbootapp.repository.*;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private HistoricoSenhaRepository historicoSenhaRepo;

    @Autowired
    private PublicacaoRepository publicacaoRepo;

    @Autowired
    private TagRepository tagRepo;

    public Usuario criarUsuario(String nome, String login, String senha) {
        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(nome);
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senha);
        novoUsuario.setAtivo(1);

        this.usuarioRepo.save(novoUsuario);

        return novoUsuario;
    }

    @Transactional
    public Boolean alterarSenha(Usuario usuario, String novaSenha) {

        Boolean sucesso = false;

        String senhaAntiga = usuario.getSenha();

        usuario.setSenha(novaSenha);
        this.usuarioRepo.save(usuario);

        HistoricoSenha historicoSenha = new HistoricoSenha();

        historicoSenha.setSenha(senhaAntiga);
        historicoSenha.setUsuario(usuario);
        historicoSenha.setData(LocalDateTime.now());

        historicoSenhaRepo.save(historicoSenha);

        if (historicoSenha.getId() != null) {
            sucesso = true;
        }

        return sucesso;
    }

    public Set<Tag> buscarTagsUsadasPeloUsuario(Usuario usuario) {

        return usuarioRepo.buscarTagsUsadasPeloUsuario(usuario);
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {

        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id) {

        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);

        if(usuarioOp.isPresent()) {

            return usuarioOp.get();
        }
        throw new RuntimeException("Usuário não encontrado!");
    }

    @Override
    public Publicacao criarPublicacao(Publicacao publicacao, Usuario usuario) {

        Set<Tag> tagsCompletas = new HashSet<Tag>();

        for(Tag tag : publicacao.getTags()) {

            tagsCompletas.add(this.criarTag(tag.getNome(), usuario));
        }

        publicacao.setTags(tagsCompletas);
        publicacao.setUsuario(usuario);
        publicacao.setAtivo(1);

        publicacaoRepo.save(publicacao);

        return publicacao;
    }

    @Override
    public Publicacao buscarPublicacaoPeloId(Integer id) {

        Optional<Publicacao> publicacaoOp = publicacaoRepo.findById(id);

        if(publicacaoOp.isPresent()) {

            return publicacaoOp.get();
        }
        throw new RuntimeException("Publicacao não encontrada!");
    }

    @Override
    public Publicacao atualizarPublicacao(Publicacao publicacao) {

        return publicacaoRepo.save(publicacao);
    }

    @Override
    public Boolean excluirPublicacao(Publicacao publicacao) {

        publicacao.setAtivo(0);
        return publicacaoRepo.save(publicacao).getAtivo() == 0;
    }

    @Override
    public Set<Publicacao> buscarPublicacoesPeloUsuario(Integer id) {

        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);

        if(usuarioOp.isPresent()) {

            Set<Publicacao> publicacoesAtivas = new HashSet<Publicacao>();
            
            for(Publicacao publicacao : usuarioOp.get().getPublicacoes()) {

                if(publicacao.getAtivo() == 1) {
                    publicacoesAtivas.add(publicacao);
                }
            }

            return publicacoesAtivas;
        }
        throw new RuntimeException("Usuário não encontrado!");
    }

    @Override
    public Tag criarTag(String nome, Usuario usuario) {

        Tag tag = tagRepo.findByNome(nome);

        if(tag == null) {
            tag = new Tag();

            tag.setNome(nome);
            tag.setUsuario(usuario);
            tag.setAtivo(1);

            tagRepo.save(tag);
        }

        return tag;
    }

    @Override
    public Set<Tag> buscarTagsPeloUsuario(Integer id) {

        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);

        if(usuarioOp.isPresent()) {

            Set<Tag> tagsAtivas = new HashSet<Tag>();
            
            for(Tag tag : usuarioOp.get().getTags()) {

                if(tag.getAtivo() == 1) {
                    tagsAtivas.add(tag);
                }
            }

            return tagsAtivas;
        }
        throw new RuntimeException("Usuário não encontrado!");
    }
}