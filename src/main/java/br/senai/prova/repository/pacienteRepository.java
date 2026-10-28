package br.senai.prova.repository;

import br.senai.prova.model.paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pacienteRepository extends JpaRepository<paciente, Long> {
}
