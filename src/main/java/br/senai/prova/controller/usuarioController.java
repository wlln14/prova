package br.senai.prova.controller;

import br.senai.prova.model.usuarios;
import br.senai.prova.service.usuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired
    usuarioService usuarioService;

    @GetMapping("/listar")
    public List<usuarios> listar() {
        return usuarioService.listar();
    }

    @PostMapping("/cadastrar")
    public usuarios cadastrar(@Valid @RequestBody usuarios usuarios) {
        return usuarioService.cadastrar(usuarios);
    }

    @GetMapping("/{id}")
    public usuarios BuscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping("/editar/{id}")
    public usuarios editar(@PathVariable Long id, @Valid @RequestBody usuarios usuarios) {
        return usuarioService.editar(id, usuarios);
    }

    @PostMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
