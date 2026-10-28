package br.senai.prova.service;

import br.senai.prova.model.pai;
import br.senai.prova.repository.paiRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class paiService {
    private final paiRepository repository;

    public paiService(paiRepository repository) {
        this.repository = repository;
    }

    public List<pai> listar() {
        return repository.findAll();
    }

    public pai buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado(a)"));
    }

    public pai salvar(pai item) {
        return repository.save(item);
    }

    public pai cadastrar(pai item) { return salvar(item); }

    public pai editar(Long id, pai item) {
        pai existente = buscarPorId(id);
        existente.setNome(item.getNome());
        existente.setCpf(item.getCpf());
        existente.setTelefone(item.getTelefone());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
