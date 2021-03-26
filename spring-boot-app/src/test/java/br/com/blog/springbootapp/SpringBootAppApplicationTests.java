package br.com.blog.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.blog.springbootapp.entity.HistoricoSenha;
import br.com.blog.springbootapp.entity.Publicacao;
import br.com.blog.springbootapp.entity.Tag;
import br.com.blog.springbootapp.entity.Usuario;
import br.com.blog.springbootapp.repository.HistoricoSenhaRepository;
import br.com.blog.springbootapp.repository.PublicacaoRepository;
import br.com.blog.springbootapp.repository.TagRepository;
import br.com.blog.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private HistoricoSenhaRepository historicoSenhaRepo;

    @Autowired
    private PublicacaoRepository publicacaoRepo;

    @Autowired
    private TagRepository tagRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void testeInsercaoUsuario() {

        // Instanciando a classe usuário
        Usuario usuario = new Usuario();

        // Setando valores no objeto
        usuario.setNome("Jaiminho");
        usuario.setLogin("jaime");
        usuario.setSenha("SenhaF0rte");
        usuario.setAtivo(1);

        // Salvando no banco de dados
        usuarioRepo.save(usuario);

        // Validando se o ID não é nulo
        assertNotNull(usuario.getId());
    }

    @Test
    void testeInsercaoHistoricoSenha() {

        // Instanciando a classe usuário
        Usuario usuario = new Usuario();

        // Setando valores no objeto
        usuario.setNome("Jaiminho");
        usuario.setLogin("jaime");
        usuario.setSenha("SenhaF0rte");
        usuario.setAtivo(1);

        // Salvando no banco de dados
        usuarioRepo.save(usuario);

        // Instanciando a classe histórico de senha
        HistoricoSenha historicoSenha = new HistoricoSenha();

        // Setando valores no objeto
        historicoSenha.setUsuario(usuario);
        historicoSenha.setData(LocalDateTime.now());
        historicoSenha.setSenha(usuario.getSenha());

        // Salvando no banco de dados
        historicoSenhaRepo.save(historicoSenha);

        // Alterando senha do usuario
        usuario.setSenha("OutraSenhaF0rte");

        // Salvando usuario com nova senha
        usuarioRepo.save(usuario);

        // Validando se o ID não é nulo
        assertNotNull(historicoSenha.getId());

    }

    @Test
    void testarInsercaoPublicacao() {

        // Instanciando a classe usuário
        Usuario usuario = new Usuario();

        // Setando valores no objeto
        usuario.setNome("Jaiminho");
        usuario.setLogin("jaime");
        usuario.setSenha("SenhaF0rte");
        usuario.setAtivo(1);
        
        // Salvando no banco de dados
        usuarioRepo.save(usuario);

        Publicacao publicacao = new Publicacao();

        publicacao.setTitulo("Os Incríveis");
        publicacao.setConteudo("Apenas");
        publicacao.setAtivo(1);
        publicacao.setUsuario(usuario);

        publicacaoRepo.save(publicacao);

        assertNotNull(publicacao.getId());
    }

    @Test
    void testarInsercaoTag() {

        // Instanciando a classe usuário
        Usuario usuario = new Usuario();

        // Setando valores no objeto
        usuario.setNome("Jaiminho");
        usuario.setLogin("jaime");
        usuario.setSenha("SenhaF0rte");
        usuario.setAtivo(1);
        
        // Salvando no banco de dados
        usuarioRepo.save(usuario);

        Tag tag = new Tag();

        tag.setNome("Doutor Estranho");
        tag.setAtivo(1);
        tag.setUsuario(usuario);

        tagRepo.save(tag);

        assertNotNull(tag.getId());
    }

    @Test
    void testarInsercaoPublicacaoETag() {

        // Instanciando a classe usuário
        Usuario usuario = new Usuario();

        // Setando valores no objeto
        usuario.setNome("Jaiminho");
        usuario.setLogin("jaime");
        usuario.setSenha("SenhaF0rte");
        usuario.setAtivo(1);
        
        // Salvando no banco de dados
        usuarioRepo.save(usuario);

        Tag tag = new Tag();

        tag.setNome("Doutor Estranho");
        tag.setAtivo(1);
        tag.setUsuario(usuario);

        Publicacao publicacao = new Publicacao();

        publicacao.setTitulo("Os Incríveis");
        publicacao.setConteudo("Apenas");
        publicacao.setAtivo(1);
        publicacao.setUsuario(usuario);
        publicacao.setTags(new HashSet<Tag>());
        publicacao.getTags().add(tag);

        publicacaoRepo.save(publicacao);

        assertNotNull(publicacao.getId());
    }

}
