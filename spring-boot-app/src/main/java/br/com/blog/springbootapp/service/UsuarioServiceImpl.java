package br.com.blog.springbootapp.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blog.springbootapp.entity.*;
import br.com.blog.springbootapp.repository.*;

import org.springframework.security.access.prepost.*;

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

    @Autowired
    private PermissaoRepository permRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    public Usuario criarUsuario(String nome, String login, String senha) {
        Usuario novoUsuario = new Usuario();

        Set<Permissao> permissoes = new HashSet<Permissao>();
        permissoes.add(permRepo.findByTitulo("ROLE_USER"));

        novoUsuario.setNome(nome);
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(passEncoder.encode(senha));
        novoUsuario.setAtivo(1);
        novoUsuario.setPermissoes(permissoes);

        this.usuarioRepo.save(novoUsuario);

        return novoUsuario;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOp = usuarioRepo.findByLogin(username);

        if(!usuarioOp.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }

        Usuario usuario = usuarioOp.get();

        return User.builder().username(username).password(usuario.getSenha())
                    .authorities(usuario.getPermissoes().stream().map(Permissao::getTitulo).collect(Collectors.toList()).toArray(new String[usuario.getPermissoes().size()]))
                    .build();
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    public Usuario buscarUsuarioPeloLogin(String login) {

        Optional<Usuario> usuarioOp = usuarioRepo.findByLogin(login);

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
    @PreAuthorize("hasRole('ROLE_USER')")
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