package br.senai.prova.service;

import br.senai.prova.model.paciente;
import br.senai.prova.model.pai;
import br.senai.prova.repository.pacienteRepository;
import br.senai.prova.service.paiService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class pacienteService {
    private final pacienteRepository repository;
    private final paiService paiService;

    public pacienteService(pacienteRepository repository, paiService paiService) {
        this.repository = repository;
        this.paiService = paiService;
    }

    public List<paciente> listar() {
        return repository.findAll();
    }

    public paciente buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado(a)"));
    }

    public paciente salvar(paciente item) {
        if (item.getPai() != null && item.getPai().getIdPai() != null) {
            item.setPai(paiService.buscarPorId(item.getPai().getIdPai()));
        }
        return repository.save(item);
    }

    public paciente salvar(paciente item, Long paiId) {
        item.setPai(paiService.buscarPorId(paiId));
        return repository.save(item);
    }

    public paciente cadastrar(paciente item) { return salvar(item); }

    public paciente editar(Long id, paciente item) {
        paciente existente = buscarPorId(id);
        existente.setNome(item.getNome());
        if (item.getPai() != null && item.getPai().getIdPai() != null) {
            existente.setPai(paiService.buscarPorId(item.getPai().getIdPai()));
        }
        return repository.save(existente);
    }

    public paciente editar(Long id, paciente item, Long paiId) {
        paciente existente = buscarPorId(id);
        existente.setNome(item.getNome());
        existente.setPai(paiService.buscarPorId(paiId));
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}