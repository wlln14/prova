package br.senai.prova.controller;

import br.senai.prova.model.paciente;
import br.senai.prova.service.pacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteWebController {
    private final pacienteService service;

    public PacienteWebController(pacienteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", service.listar());
        model.addAttribute("paciente", new paciente());
        return "pacientes";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("pacientes", service.listar());
        model.addAttribute("paciente", service.buscarPorId(id));
        return "pacientes";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("paciente") paciente item) {
        service.salvar(item);
        return "redirect:/pacientes";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/pacientes";
    }
}
