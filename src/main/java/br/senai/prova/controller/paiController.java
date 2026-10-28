package br.senai.prova.controller;

import br.senai.prova.model.pai;
import br.senai.prova.service.paiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/pai")
public class paiController {

    @Autowired
    paiService paiService;

    @GetMapping("/listar")
    public List<pai> listar() {
        return paiService.listar();
    }

    @PostMapping("/cadastrar")
    public pai cadastrar(@Valid @RequestBody pai pai) {
        return paiService.cadastrar(pai);
    }

    @GetMapping("/{id}")
    public pai BuscarPorId(@PathVariable Long id) {
        return paiService.buscarPorId(id);
    }

    @PostMapping("/editar/{id}")
    public pai editar(@PathVariable Long id, @Valid @RequestBody pai pai) {
        return paiService.editar(id, pai);
    }

    @PostMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        paiService.deletar(id);
    }

}
