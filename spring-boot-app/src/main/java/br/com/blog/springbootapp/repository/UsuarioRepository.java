package br.com.blog.springbootapp.repository;

import java.util.Set;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.blog.springbootapp.entity.Tag;
import br.com.blog.springbootapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByLoginAndSenha(String login, String senha);

    public Optional<Usuario> findByLogin(String login);

    @Query("select t from Tag t inner join t.publicacoes p where p.usuario = :usuario")
    public Set<Tag> buscarTagsUsadasPeloUsuario(Usuario usuario);
}
