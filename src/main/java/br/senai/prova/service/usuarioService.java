package br.senai.prova.service;

import br.senai.prova.model.usuarios;
import br.senai.prova.repository.usuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class usuarioService {
    private final usuarioRepository repository;

    public usuarioService(usuarioRepository repository) {
        this.repository = repository;
    }

    public List<usuarios> listar() {
        return repository.findAll();
    }

    public usuarios buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado(a)"));
    }

    public usuarios salvar(usuarios item) {
        return repository.save(item);
    }

    public usuarios cadastrar(usuarios item) { return salvar(item); }

    public usuarios editar(Long id, usuarios item) {
        usuarios existente = buscarPorId(id);
        existente.setNome(item.getNome());
        existente.setLogin(item.getLogin());
        existente.setSenha(item.getSenha());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
