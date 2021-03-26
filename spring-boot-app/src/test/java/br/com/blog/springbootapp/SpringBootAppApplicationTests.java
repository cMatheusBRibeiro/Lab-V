package br.com.blog.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.blog.springbootapp.entity.Usuario;
import br.com.blog.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void testeInsercao() {

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

}
