package br.senai.prova.controller;

import br.senai.prova.model.paciente;
import br.senai.prova.service.pacienteService;
import br.senai.prova.service.paiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteWebController {
    private final pacienteService service;
    private final paiService paiService;

    public PacienteWebController(pacienteService service, paiService paiService) {
        this.service = service;
        this.paiService = paiService;
    }

    private void carregarFormulario(Model model) {
        model.addAttribute("responsaveis", paiService.listar());
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", service.listar());
        model.addAttribute("paciente", new paciente());
        carregarFormulario(model);
        return "pacientes";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("pacientes", service.listar());
        model.addAttribute("paciente", service.buscarPorId(id));
        carregarFormulario(model);
        return "pacientes";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("paciente") paciente item,
                         @RequestParam("paiId") Long paiId) {
        if (item.getIdPaciente() == null) {
            service.salvar(item, paiId);
        } else {
            service.editar(item.getIdPaciente(), item, paiId);
        }
        return "redirect:/pacientes";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/pacientes";
    }
}