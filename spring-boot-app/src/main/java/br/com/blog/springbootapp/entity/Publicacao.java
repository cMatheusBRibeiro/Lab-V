package br.com.blog.springbootapp.entity;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import br.com.blog.springbootapp.controller.*;

@Entity
@Table(name = "publicacao")
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUB_ID")
    @JsonView({View.CadastroPublicacao.class, View.PublicacoesUsuario.class})
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_ID")
    private Usuario usuario;

    @Column(name = "PUB_TITULO")
    @JsonView({View.CadastroPublicacao.class, View.PublicacoesUsuario.class})
    private String titulo;

    @Column(name = "PUB_CONTEUDO", columnDefinition = "TEXT")
    @JsonView({View.CadastroPublicacao.class, View.PublicacoesUsuario.class})
    private String conteudo;

    @Column(name = "PUB_ATIVO")
    private Integer ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "tagpub",
        joinColumns = { @JoinColumn(name = "PUB_ID") },
        inverseJoinColumns = { @JoinColumn(name = "TAG_ID") }
    )
    @JsonView({View.CadastroPublicacao.class, View.PublicacoesUsuario.class})
    private Set<Tag> tags;


    // Getter & Setter ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter & Setter Usuário
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getter & Setter Titulo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter & Setter Conteudo
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    // Getter & Setter Ativo
    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }

}
