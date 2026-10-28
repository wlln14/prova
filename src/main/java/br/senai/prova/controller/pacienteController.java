package br.senai.prova.controller;

import br.senai.prova.model.paciente;
import br.senai.prova.service.pacienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class pacienteController {

    @Autowired
    pacienteService pacienteService;

    @GetMapping("/listar")
    public List<paciente> listar() {
        return pacienteService.listar();
    }

    @GetMapping("/{id}")
    public paciente BuscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping("/cadastrar")
    public paciente cadastrar(@Valid @RequestBody paciente paciente) {
        return pacienteService.cadastrar(paciente);
    }

    @PostMapping("/editar/{id}")
    public paciente editar(@PathVariable Long id, @Valid @RequestBody paciente paciente) {
        return pacienteService.editar(id, paciente);
    }

    @PostMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}
