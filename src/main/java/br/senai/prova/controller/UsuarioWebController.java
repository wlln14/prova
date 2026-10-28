package br.senai.prova.controller;

import br.senai.prova.model.usuarios;
import br.senai.prova.service.usuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {
    private final usuarioService service;

    public UsuarioWebController(usuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        model.addAttribute("usuario", new usuarios());
        return "usuarios";
    }

    @GetMapping("/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("usuarios", service.listar());
        model.addAttribute("usuario", service.buscarPorId(id));
        return "usuarios";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("usuario") usuarios item) {
        service.salvar(item);
        return "redirect:/usuarios";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/usuarios";
    }
}
