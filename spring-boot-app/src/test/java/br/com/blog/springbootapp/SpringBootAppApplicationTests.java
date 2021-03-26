package br.com.blog.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.blog.springbootapp.entity.Usuario;
import br.com.blog.springbootapp.repository.UsuarioRepository;

@SpringBootTest
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

	@Test
	void contextLoads() {
    }
    
    @Test
    void testeInsercao() {

        Usuario usuario = new Usuario();
        usuario.setNome("Matheus");
        usuario.setSenha("SenhaF0rte");
        usuario.setLogin("math");
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getId());
    }

}
