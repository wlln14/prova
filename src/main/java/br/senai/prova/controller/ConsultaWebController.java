package br.senai.prova.controller;

import br.senai.prova.model.consulta;
import br.senai.prova.service.consultaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaWebController {
    private final consultaService service;

    public ConsultaWebController(consultaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", service.listar());
        model.addAttribute("consulta", new consulta());
        return "consultas";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("consultas", service.listar());
        model.addAttribute("consulta", service.buscarPorId(id));
        return "consultas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("consulta") consulta item) {
        service.salvar(item);
        return "redirect:/consultas";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/consultas";
    }
}
