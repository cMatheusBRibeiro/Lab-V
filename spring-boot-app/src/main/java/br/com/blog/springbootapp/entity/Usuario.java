package br.com.blog.springbootapp.entity;



public class Usuario {

    private Integer id;

    private Integer admin;

    private String login;

    private String senha;

    private String nome;

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