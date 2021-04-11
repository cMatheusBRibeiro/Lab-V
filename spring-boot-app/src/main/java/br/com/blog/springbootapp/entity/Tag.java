package br.com.blog.springbootapp.entity;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "tag")
public class Tag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_ID")
    private Usuario usuario;

    @Column(name = "TAG_NOME")
    private String nome;

    @Column(name = "TAG_ATIVO")
    private Integer ativo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    @JsonIgnore
    private Set<Publicacao> publicacoes;

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setPublicacoes(Set<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public Set<Publicacao> getPublicacoes() {
        return publicacoes;
    }

}