package br.senai.prova.service;

import br.senai.prova.model.paciente;
import br.senai.prova.repository.pacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class pacienteService {
    private final pacienteRepository repository;

    public pacienteService(pacienteRepository repository) {
        this.repository = repository;
    }

    public List<paciente> listar() {
        return repository.findAll();
    }

    public paciente buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado(a)"));
    }

    public paciente salvar(paciente item) {
        return repository.save(item);
    }

    public paciente cadastrar(paciente item) { return salvar(item); }

    public paciente editar(Long id, paciente item) {
        paciente existente = buscarPorId(id);
        existente.setNome(item.getNome());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
