package br.senai.prova.repository;

import br.senai.prova.model.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<usuarios, Long> {
}
