package br.senai.prova.controller;

import br.senai.prova.model.consulta;
import br.senai.prova.service.consultaService;
import br.senai.prova.service.pacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaWebController {
    private final consultaService service;
    private final pacienteService pacienteService;

    public ConsultaWebController(consultaService service, pacienteService pacienteService) {
        this.service = service;
        this.pacienteService = pacienteService;
    }

    private void carregarFormulario(Model model) {
        model.addAttribute("pacientesDisponiveis", pacienteService.listar());
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", service.listar());
        model.addAttribute("consulta", new consulta());
        carregarFormulario(model);
        return "consultas";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("consultas", service.listar());
        model.addAttribute("consulta", service.buscarPorId(id));
        carregarFormulario(model);
        return "consultas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("consulta") consulta item,
                         @RequestParam("pacienteId") Long pacienteId) {
        if (item.getIdConsulta() == null) {
            service.salvar(item, pacienteId);
        } else {
            service.editar(item.getIdConsulta(), item, pacienteId);
        }
        return "redirect:/consultas";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/consultas";
    }
}