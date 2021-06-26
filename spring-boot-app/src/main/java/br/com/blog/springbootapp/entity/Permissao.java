package br.com.blog.springbootapp.entity;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERM_ID")
    private Integer id;

    @Column(name = "PERM_TITULO")
    private String titulo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissoes")
    @JsonIgnore
    private Set<Usuario> usuarios;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
}