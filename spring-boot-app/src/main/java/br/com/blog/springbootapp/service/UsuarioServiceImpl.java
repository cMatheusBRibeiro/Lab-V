package br.com.blog.springbootapp.service;

import java.time.LocalDateTime;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.springbootapp.entity.HistoricoSenha;
import br.com.blog.springbootapp.entity.Tag;
import br.com.blog.springbootapp.entity.Usuario;
import br.com.blog.springbootapp.repository.HistoricoSenhaRepository;
import br.com.blog.springbootapp.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private HistoricoSenhaRepository historicoSenhaRepo;

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
}