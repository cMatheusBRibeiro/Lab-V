package br.com.blog.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Integer id;

    @Column(name = "ADMIN_ID")
    private Integer admin;

    @Column(name = "USR_LOGIN")
    private String login;

    @Column(name = "USR_SENHA")
    private String senha;

    @Column(name = "USR_NOME")
    private String nome;

    @Column(name = "USR_ATIVO")
    private Integer ativo;

    
    // Getter & Setter ID
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter & Setter Admin
    public Integer getAdmin() {
        return this.admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
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
}