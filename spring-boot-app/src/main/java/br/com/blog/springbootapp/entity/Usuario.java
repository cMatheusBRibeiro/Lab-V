package br.com.blog.springbootapp.entity;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Integer id;

    @Column(name = "USR_LOGIN")
    private String login;

    @Column(name = "USR_SENHA")
    private String senha;

    @Column(name = "USR_NOME")
    private String nome;

    @Column(name = "USR_ATIVO")
    private Integer ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnore
    private Set<HistoricoSenha> historicoDeSenhas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnore
    private Set<Publicacao> publicacoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnore
    private Set<Tag> tags;

    
    // Getter & Setter ID
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter & Setter Login
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // Getter & Setter Senha
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getter & Setter Nome
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter & Setter Ativo
    public Integer getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    // Getter & Setter Historico de Senha
    public Set<HistoricoSenha> getHistoricoSenha() {
        return this.historicoDeSenhas;
    }

    public void setHistoricoSenha(Set<HistoricoSenha> historicoDeSenhas) {
        this.historicoDeSenhas = historicoDeSenhas;
    }

    // Getter & Setter Publicações
    public Set<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(Set<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    // Getter & Setter Tags
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}