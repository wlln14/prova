package br.senai.prova.controller;

import br.senai.prova.service.pacienteService;
import br.senai.prova.service.paiService;
import br.senai.prova.service.usuarioService;
import br.senai.prova.service.consultaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final pacienteService pacientes;
    private final paiService responsaveis;
    private final usuarioService usuarios;
    private final consultaService consultas;

    public HomeController(pacienteService pacientes, paiService responsaveis,
                          usuarioService usuarios, consultaService consultas) {
        this.pacientes = pacientes;
        this.responsaveis = responsaveis;
        this.usuarios = usuarios;
        this.consultas = consultas;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("totalPacientes", pacientes.listar().size());
        model.addAttribute("totalResponsaveis", responsaveis.listar().size());
        model.addAttribute("totalUsuarios", usuarios.listar().size());
        model.addAttribute("totalConsultas", consultas.listar().size());
        return "index";
    }
}
