package br.senai.prova.service;

import br.senai.prova.model.consulta;
import br.senai.prova.repository.consultaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class consultaService {
    private final consultaRepository repository;

    public consultaService(consultaRepository repository) {
        this.repository = repository;
    }

    public List<consulta> listar() {
        return repository.findAll();
    }

    public consulta buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrado(a)"));
    }

    public consulta salvar(consulta item) {
        return repository.save(item);
    }

    public consulta cadastrar(consulta item) { return salvar(item); }

    public consulta editar(Long id, consulta item) {
        consulta existente = buscarPorId(id);
        existente.setData(item.getData());
        existente.setHora(item.getHora());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
