package br.senai.prova.service;

import br.senai.prova.model.consulta;
import br.senai.prova.repository.consultaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class consultaService {
    private final consultaRepository repository;
    private final pacienteService pacienteService;

    public consultaService(consultaRepository repository, pacienteService pacienteService) {
        this.repository = repository;
        this.pacienteService = pacienteService;
    }

    public List<consulta> listar() {
        return repository.findAll();
    }

    public consulta buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
    }

    public consulta salvar(consulta item) {
        if (item.getPaciente() != null && item.getPaciente().getIdPaciente() != null) {
            item.setPaciente(pacienteService.buscarPorId(item.getPaciente().getIdPaciente()));
        }
        return repository.save(item);
    }

    public consulta salvar(consulta item, Long pacienteId) {
        item.setPaciente(pacienteService.buscarPorId(pacienteId));
        return repository.save(item);
    }

    public consulta cadastrar(consulta item) { return salvar(item); }

    public consulta editar(Long id, consulta item) {
        consulta existente = buscarPorId(id);
        existente.setData(item.getData());
        existente.setHora(item.getHora());
        if (item.getPaciente() != null && item.getPaciente().getIdPaciente() != null) {
            existente.setPaciente(pacienteService.buscarPorId(item.getPaciente().getIdPaciente()));
        }
        return repository.save(existente);
    }

    public consulta editar(Long id, consulta item, Long pacienteId) {
        consulta existente = buscarPorId(id);
        existente.setData(item.getData());
        existente.setHora(item.getHora());
        existente.setPaciente(pacienteService.buscarPorId(pacienteId));
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}