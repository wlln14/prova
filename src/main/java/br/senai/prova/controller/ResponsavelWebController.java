package br.senai.prova.controller;

import br.senai.prova.model.pai;
import br.senai.prova.service.paiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/responsaveis")
public class ResponsavelWebController {
    private final paiService service;

    public ResponsavelWebController(paiService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("responsaveis", service.listar());
        model.addAttribute("responsavel", new pai());
        return "responsaveis";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("responsaveis", service.listar());
        model.addAttribute("responsavel", service.buscarPorId(id));
        return "responsaveis";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("responsavel") pai item) {
        service.salvar(item);
        return "redirect:/responsaveis";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/responsaveis";
    }
}
