package br.senai.prova.controller;

import br.senai.prova.model.consulta;
import br.senai.prova.service.consultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")
public class consultaController {

    @Autowired
    consultaService consultaService;

    @GetMapping("/listar")
    public List<consulta> listar () {
        return consultaService.listar();
    }

    @PostMapping("/cadastrar")
    public consulta cadastrar (@Valid @RequestBody consulta consulta){
        return consultaService.cadastrar(consulta);
    }

    @GetMapping("/{id}")
    public consulta BuscarPorId (@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PostMapping("/editar/{id}")
    public consulta editar (@PathVariable Long id, @Valid @RequestBody consulta consulta) {
        return consultaService.editar(id, consulta);
    }

    @PostMapping("/deletar/{id}")
    public void deletar (@PathVariable Long id) {
        consultaService.deletar(id);
    }
}
