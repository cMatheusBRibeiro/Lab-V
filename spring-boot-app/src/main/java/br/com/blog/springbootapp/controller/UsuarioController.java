package br.com.blog.springbootapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import br.com.blog.springbootapp.entity.*;
import br.com.blog.springbootapp.repository.*;
import br.com.blog.springbootapp.service.*;


@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> buscarTodos() {
        
        return this.usuarioService.buscarTodosUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Integer id) {

        return this.usuarioService.buscarUsuarioPorId(id);
    }

    @PostMapping
    public Usuario cadastrarNovoUsuario(@RequestBody Usuario usuario) {

        return this.usuarioService.criarUsuario(usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }

}